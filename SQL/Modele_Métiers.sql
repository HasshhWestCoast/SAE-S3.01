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

--------------------------------------------------------------------------------------
------------- CALCULE DU PRIX TOTAL DE LA CONSOMMATION D’UN BIEN DONNÉ ---------------
--------------------------------------------------------------------------------------
-- Fonction pour calculer le prix total de la consommation pour un logement
-- Somme de la partie variable et partie fixe (prix abonement)
CREATE OR REPLACE FUNCTION PrixConsoLogement(p_Id_Compteur IN SAE_Compteur.Id_Compteur%TYPE)
RETURN NUMBER
IS
    -- Déclaration des variables
    v_PrixAbonnement NUMBER(15, 2);
    v_nb_mois_utilisation NUMBER;
    v_derniere_date_releve DATE;
    v_Av_derniere_date_releve DATE;
    v_bien Compteur.Id_Bien%TYPE;
    v_date_derniere_regularisation DATE;
BEGIN
    -- Récupérer le prix de l'abonnement à partir de la table Compteur
    SELECT prix_abonnement INTO v_PrixAbonnement
    FROM SAE_Compteur
    WHERE id_compteur = p_Id_Compteur;

    -- Vérifier si le prix de l'abonnement est nul
    IF v_PrixAbonnement IS NULL THEN
        -- Si oui, générer une erreur personnalisée
        RAISE_APPLICATION_ERROR(-20006, 'Prix de l''abonnement non trouvé pour le compteur ' || p_Id_Compteur);
    END IF;
    
    -- Récupérer l'identifiant du bien associé au compteur
    SELECT id_bien INTO v_bien
    FROM SAE_Compteur
    WHERE id_compteur = p_Id_Compteur;
    
    -- Récupérer la date de la dernière régularisation pour le bien
    BEGIN
        SELECT date_derniere_reg INTO v_date_derniere_regularisation
        FROM SAE_Louer
        WHERE Id_Bien = v_bien;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20009, 'Aucune date de dernière régularisation trouvée pour le bien ' || v_bien);
    END;
    
    -- Récupérer la date actuelle (la plus récente)
    BEGIN
        SELECT date_relevé INTO v_derniere_date_releve
        FROM SAE_Compteur
        WHERE Id_Compteur = p_Id_Compteur
            AND date_relevé > v_date_derniere_regularisation
        ORDER BY date_relevé DESC
        FETCH FIRST 1 ROWS ONLY;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20010, 'Aucune date de relevé trouvée pour le compteur ' || p_Id_Compteur);
    END;
    
    -- Récupérer la date immédiatement précédente
    BEGIN
        SELECT date_relevé INTO v_Av_derniere_date_releve
        FROM SAE_Compteur
        WHERE Id_Compteur = p_Id_Compteur 
        AND date_relevé > v_date_derniere_regularisation
        AND date_relevé < (SELECT MAX(date_relevé) FROM Relevé WHERE Id_Compteur = p_Id_Compteur)
        ORDER BY date_relevé DESC
        FETCH FIRST 1 ROWS ONLY;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0; -- Consommation 0, car cette fonction est appellée prochainement
              DBMS_OUTPUT.PUT_LINE('Aucune date de relevé précédente trouvée pour le compteur ' || p_Id_Compteur);
    END;
    
    -- Récupérer le nombre de mois d'occupation
    -- Utilisation de la différence entre les dates pour obtenir le nombre de mois
    v_nb_mois_utilisation := MONTHS_BETWEEN(v_derniere_date_releve, v_Av_derniere_date_releve);

    -- Gérer le cas où la différence entre les dates est nulle ou négative
    IF v_nb_mois_utilisation <= 0 THEN
        RAISE_APPLICATION_ERROR(-20012, 'La période d''occupation du bien est invalide.');
    END IF;

    -- Retourner le prix total de la consommation (partie variable + partie fixe)
    -- en prenant compte du nombre de mois d'utilisation du compteur
    RETURN v_PrixAbonnement * v_nb_mois_utilisation + PartieVariableConso(p_Id_Compteur);

EXCEPTION
    WHEN OTHERS THEN
        RETURN 0; -- On en a besoin car cette fonction est appelée dans les fontions prochaines
        -- Gérer les autres exceptions
        RAISE_APPLICATION_ERROR(-20013, 'Une erreur s''est produite : ' || SQLERRM);
        RETURN NULL;
END PrixConsoLogement;
/


----------------------- Bloc anonyme pour tester la fonction—---------------------------
DECLARE
    v_PrixTotal NUMBER(15, 2);
BEGIN
    -- Appeler la fonction PrixConsoLogement avec un ID de compteur spécifique
    v_PrixTotal := PrixConsoLogement('COMP001');

    -- Vérifier si le résultat est non nul
    IF v_PrixTotal IS NOT NULL THEN
        -- Afficher le résultat
        DBMS_OUTPUT.PUT_LINE('PrixConsoLogement : ' || v_PrixTotal || ' €');
    ELSE
        -- Afficher un message d'erreur en cas d'échec
        DBMS_OUTPUT.PUT_LINE('Une erreur s''est produite lors du calcul du prix total de la consommation.');
    END IF;
END;
/

