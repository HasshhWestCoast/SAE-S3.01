SET SERVEROUTPUT ON;

---------------------------------------------------------------------------
------------- CONSOMMATION D'UN COMPTEUR POUR UN LOGEMENT DONNÉ ---------------
---------------------------------------------------------------------------
-- Fonction pour calculer la consommation entre deux relevés pour un compteur donné
CREATE OR REPLACE FUNCTION CalculerConsommation(
    p_Id_Compteur IN SAE_Compteur.Id_Compteur%TYPE
) RETURN NUMBER
IS
    -- Déclaration des variables
    v_Consommation NUMBER := 0;  -- Initialiser à 0 en cas d'absence de relevé
    v_NouvelIndice NUMBER;
    v_AncienIndice NUMBER;
BEGIN
    -- Récupérer l'indice actuel (le plus récent)
    SELECT indexCompteur INTO v_NouvelIndice
    FROM SAE_Compteur
    WHERE Id_Compteur = p_Id_Compteur
    ORDER BY date_releve DESC
    FETCH FIRST 1 ROWS ONLY;

    -- Récupérer l'indice immédiatement précédent
    SELECT indexCompteur INTO v_AncienIndice
    FROM SAE_Compteur
    WHERE Id_Compteur = p_Id_Compteur AND date_releve < (SELECT MAX(date_releve) FROM SAE_Compteur WHERE Id_Compteur = p_Id_Compteur)
    ORDER BY date_releve DESC
    FETCH FIRST 1 ROWS ONLY;

    -- Vérifier si l'indice précédent a été trouvé
    IF v_AncienIndice IS NULL THEN
        -- Si non, retourner 0 (pas d'erreur générée)
        RETURN 0;
    END IF;

    -- Calculer la consommation
    v_Consommation := v_NouvelIndice - v_AncienIndice;

    -- Retourner la consommation calculée
    RETURN v_Consommation;

EXCEPTION
    -- Si on ne trouve aucun relevé, on renvoie 0
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Aucun relevé ! ');
        RETURN 0; -- On en a besoin car cette fonction est appelée dans les fonctions prochaines
    WHEN TOO_MANY_ROWS THEN
        -- Gérer le cas où plusieurs relevés sont trouvés avec une erreur personnalisée
        RAISE_APPLICATION_ERROR(-20003, 'Plusieurs relevés trouvés pour le Compteur donné.');
    WHEN OTHERS THEN
        -- Gérer toutes les autres exceptions en affichant le message d'erreur d'origine
        DBMS_OUTPUT.PUT_LINE('Erreur : ' || SQLERRM);
        RETURN NULL;
END;
/

----------------------- Bloc anonyme pour tester la fonction ---------------------------
DECLARE
    v_Consommation INT;
BEGIN 
    v_Consommation := CalculerConsommation('ComptMaisonSTMichel_Eau');

    IF v_Consommation IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('CalculerConsommation: ' || v_Consommation);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Erreur');
    END IF;
END;
/SET SERVEROUTPUT ON;

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


----------------------- Bloc anonyme pour tester la fonction ---------------------------
DECLARE
    v_Consommation INT;
BEGIN 
    v_Consommation := CalculerConsommation('ComptMaisonSTMichel_Eau');

    IF v_Consommation IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('CalculerConsommation: ' || v_Consommation);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Erreur');
    END IF;
END;
/


