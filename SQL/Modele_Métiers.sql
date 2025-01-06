SET SERVEROUTPUT ON;

---------------------------------------------------------------------------
------------- CONSOMMATION D'UN COMPTEUR POUR UN LOGEMENT DONNÉ ---------------
---------------------------------------------------------------------------
-- Fonction pour calculer la consommation entre deux relevés pour un compteur donné
DECLARE
    v_Id_Compteur SAE_Compteur.Id_Compteur%TYPE;
    v_Consommation NUMBER;
BEGIN
    -- Sélectionner un identifiant aléatoire parmi les compteurs existants
    SELECT Id_Compteur
    INTO v_Id_Compteur
    FROM (SELECT Id_Compteur FROM SAE_Compteur ORDER BY DBMS_RANDOM.VALUE)
    WHERE ROWNUM = 1;

    -- Appeler la fonction CalculerConsommation avec l'identifiant sélectionné
    v_Consommation := CalculerConsommation(v_Id_Compteur);

    -- Afficher les résultats
    DBMS_OUTPUT.PUT_LINE('Identifiant du compteur testé (aléatoire) : ' || v_Id_Compteur);
    IF v_Consommation IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('Consommation calculée : ' || v_Consommation);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Erreur dans le calcul de la consommation.');
    END IF;
END;
/

-----------------------------------------------------------------------------------------------
------- CALCULE DU PRIX DE LA PARTIE VARIABLE D'UN COMPTEUR EN FONCTION DE SON TYPE -----------
-----------------------------------------------------------------------------------------------
-- Fonction pour calculer le coût variable de la consommation en fonction du type de compteur
-- La consommation multipliée par le prix du m3 
-- Il faut plus de 2 relevée pour qu'il marche 
CREATE OR REPLACE FUNCTION PartieVariableConso(
    p_Id_Compteur IN SAE_Compteur.Id_Compteur%TYPE
) RETURN NUMBER
IS
    v_Consommation NUMBER;          -- Consommation en m3
    v_PrixConsommation NUMBER;      -- Prix total de la consommation
    v_TypeComp SAE_Compteur.TypeComp%TYPE; -- Type de compteur
BEGIN
    -- Appeler la fonction CalculerConsommation pour obtenir la consommation
    v_Consommation := CalculerConsommation(p_Id_Compteur);

    -- Vérifier si la consommation est valide
    IF v_Consommation IS NULL THEN
        DBMS_OUTPUT.PUT_LINE('Erreur : Consommation non calculable.');
        RETURN 0;
    ELSIF v_Consommation = 0 THEN
        RETURN 0; -- Pas de consommation
    ELSIF v_Consommation < 0 THEN
        -- Consommation invalide
        RAISE_APPLICATION_ERROR(-20003, 'Consommation invalide (négative).');
    END IF;

    -- Récupérer le type de compteur
    SELECT TypeComp INTO v_TypeComp
    FROM SAE_Compteur
    WHERE Id_Compteur = p_Id_Compteur;

    -- Calculer le prix de la consommation en fonction du type de compteur
    CASE v_TypeComp
        WHEN 'Electricité' THEN
            v_PrixConsommation := v_Consommation * 0.2276; -- Prix par m3 pour l'électricité
        WHEN 'Eau' THEN
            v_PrixConsommation := v_Consommation * 3.34;    -- Prix par m3 pour l'eau
        WHEN 'Gaz' THEN
            v_PrixConsommation := v_Consommation * 1.21;    -- Prix par m3 pour le gaz
        ELSE
            -- Type de compteur non pris en charge
            RAISE_APPLICATION_ERROR(-20004, 'Type de compteur non pris en charge.');
    END CASE;

    -- Retourner le prix de la consommation
    RETURN v_PrixConsommation;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Compteur ou type de compteur introuvable
        DBMS_OUTPUT.PUT_LINE('Erreur : Compteur ou type introuvable.');
        RETURN 0;
    WHEN OTHERS THEN
        -- Gérer les autres erreurs
        DBMS_OUTPUT.PUT_LINE('Erreur inconnue : ' || SQLERRM);
        RETURN NULL;
END;
/

DECLARE
    v_PrixConsommation NUMBER;
BEGIN
    -- Appeler la fonction avec un identifiant de compteur valide
    v_PrixConsommation := PartieVariableConso('COMP001'); -- Test avec 'COMP001'

    -- Afficher le résultat
    IF v_PrixConsommation IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('Prix de la consommation variable : ' || v_PrixConsommation || ' EUR');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Erreur dans le calcul du prix.');
    END IF;
END;
/
