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
    FROM SAE_Relevé
    WHERE Id_Compteur = p_Id_Compteur
    ORDER BY date_relevé DESC
    FETCH FIRST 1 ROWS ONLY;

    DBMS_OUTPUT.PUT_LINE('Nouvel indice récupéré : ' || v_NouvelIndice);

    -- Récupérer l'indice immédiatement précédent
    SELECT indexCompteur INTO v_AncienIndice
    FROM SAE_Relevé
    WHERE Id_Compteur = p_Id_Compteur
      AND date_relevé < (SELECT MAX(date_relevé) FROM SAE_Relevé WHERE Id_Compteur = p_Id_Compteur)
    ORDER BY date_relevé DESC
    FETCH FIRST 1 ROWS ONLY;

    DBMS_OUTPUT.PUT_LINE('Ancien indice récupéré : ' || v_AncienIndice);

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
        DBMS_OUTPUT.PUT_LINE('Aucun relevé trouvé pour le compteur : ' || p_Id_Compteur);
        RETURN 0; -- On en a besoin car cette fonction est appelée dans les fonctions suivantes
    WHEN TOO_MANY_ROWS THEN
        -- Gérer le cas où plusieurs relevés sont trouvés avec une erreur personnalisée
        RAISE_APPLICATION_ERROR(-20003, 'Plusieurs relevés trouvés pour le Compteur donné.');
    WHEN OTHERS THEN
        -- Gérer toutes les autres exceptions en affichant le message d'erreur d'origine
        DBMS_OUTPUT.PUT_LINE('Erreur : ' || SQLERRM);
        RETURN NULL;
END;
/

set serveroutput on
DECLARE
    v_Consommation NUMBER;
BEGIN 
    -- Test avec un compteur existant
    v_Consommation := CalculerConsommation('COMP001'); -- Exemple d'ID de compteur

    IF v_Consommation IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('CalculerConsommation: ' || v_Consommation);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Erreur');
    END IF;
END;
/


CREATE OR REPLACE FUNCTION CalculerPartieVariableConso(
    p_Id_Compteur IN SAE_Compteur.Id_Compteur%TYPE
) RETURN NUMBER
IS
    -- Déclaration des variables locales
    v_Consommation NUMBER := 0;
    v_PartieVariable NUMBER := 0;
    v_TypeCompteur VARCHAR2(50);
    v_PrixConsommation NUMBER := 0;
BEGIN
    -- Vérifier le type de compteur
    SELECT TypeComp
    INTO v_TypeCompteur
    FROM SAE_Compteur
    WHERE Id_Compteur = p_Id_Compteur;

    -- Afficher le type de compteur
    DBMS_OUTPUT.PUT_LINE('Type du compteur : ' || v_TypeCompteur);

    -- Récupérer la consommation via la fonction CalculerConsommation
    v_Consommation := CalculerConsommation(p_Id_Compteur);

    -- Calculer le prix de la consommation en fonction du type de compteur
    CASE v_TypeCompteur
        WHEN 'Electricité' THEN
            v_PrixConsommation := v_Consommation * 0.2516; -- Prix par kWh d'électricité
        WHEN 'Eau' THEN
            v_PrixConsommation := v_Consommation * 4.34;   -- Prix par m3 d'eau
        WHEN 'Gaz' THEN
            v_PrixConsommation := v_Consommation * 1.025;
        ELSE
            -- Si le type de compteur n'est pas pris en charge, générer une erreur personnalisée
            RAISE_APPLICATION_ERROR(-20004, 'Type de compteur non pris en charge.');
    END CASE;

    -- Assignation du prix de consommation calculé
    v_PartieVariable := v_PrixConsommation;

    -- Retourner la partie variable calculée
    RETURN v_PartieVariable;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Aucune donnée de consommation trouvée pour le compteur : ' || p_Id_Compteur);
        RETURN 0;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erreur inattendue : ' || SQLERRM);
        RETURN NULL;
END;
/

set serveroutput on
DECLARE
    v_Resultat NUMBER;
BEGIN
    -- Exemple de test avec un compteur existant
    v_Resultat := CalculerPartieVariableConso('COMP009');

    IF v_Resultat IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('Partie variable de la consommation : ' || v_Resultat);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Erreur lors du calcul.');
    END IF;
END;
/


CREATE OR REPLACE FUNCTION CalculerPrixConsoLogement(
    p_Id_Logement IN SAE_Compteur.Id_Logement%TYPE,
    p_Id_Compteur IN SAE_Compteur.Id_Compteur%TYPE
) RETURN NUMBER
IS
    -- Variable pour stocker le prix total de la consommation
    v_PrixTotal NUMBER := 0;
    -- Variable pour stocker la partie variable de la consommation
    v_PartieVariable NUMBER := 0;
BEGIN
    -- Vérifier les entrées
    IF p_Id_Logement IS NULL OR p_Id_Compteur IS NULL THEN
        RAISE_APPLICATION_ERROR(-20001, 'ID du logement ou du compteur non fourni.');
    END IF;

    -- Afficher les paramètres pour le débogage
    DBMS_OUTPUT.PUT_LINE('ID Logement : ' || p_Id_Logement);
    DBMS_OUTPUT.PUT_LINE('ID Compteur : ' || p_Id_Compteur);

    -- Récupérer la partie variable de la consommation via CalculerPartieVariableConso
    v_PartieVariable := CalculerPartieVariableConso(p_Id_Compteur);

    -- Vérifier si la partie variable est valide
    IF v_PartieVariable IS NULL THEN
        v_PartieVariable := 0; -- Par défaut, 0 si non calculé
    END IF;

    -- Assigner la partie variable calculée au prix total
    v_PrixTotal := v_PartieVariable;

    -- Afficher le prix total calculé
    DBMS_OUTPUT.PUT_LINE('Prix total de la consommation : ' || v_PrixTotal);

    -- Retourner le prix total
    RETURN v_PrixTotal;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée pour le compteur : ' || p_Id_Compteur);
        RETURN 0;
    WHEN VALUE_ERROR THEN
        DBMS_OUTPUT.PUT_LINE('Erreur de type ou valeur invalide.');
        RETURN NULL;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erreur inattendue : ' || SQLERRM);
        RETURN NULL;
END;
/


set serveroutput on 
DECLARE
    v_PrixTotal NUMBER;
BEGIN
    -- Exemple de test avec un logement et un compteur existants
    v_PrixTotal := CalculerPrixConsoLogement('LOG002', 'COMP002');

    -- Afficher le résultat
    IF v_PrixTotal IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('Prix total de la consommation pour le logement : ' || v_PrixTotal);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Erreur lors du calcul.');
    END IF;
END;
/

CREATE OR REPLACE FUNCTION PrixConsoLogementQuotite(
    p_Id_Bien IN SAE_Bien.Id_Bien%TYPE,
    p_Id_Compteur IN SAE_Compteur.Id_Compteur%TYPE
) RETURN NUMBER
IS
    -- Déclaration des variables
    v_ConsommationLogement NUMBER := 0;
    v_Quotite NUMBER(20, 10);
    v_TypeQuotite VARCHAR2(50);
    v_TypeCompteur SAE_Compteur.TypeComp%TYPE;
    v_PrixConsommationTotale NUMBER := 0;

BEGIN
    -- Récupérer le type de compteur
    SELECT TypeComp
    INTO v_TypeCompteur
    FROM SAE_Compteur
    WHERE Id_Compteur = p_Id_Compteur;

    DBMS_OUTPUT.PUT_LINE('Type de compteur : ' || v_TypeCompteur);

    -- Récupérer la quotité du bien
    BEGIN
        SELECT pourcentage, type_quotite
        INTO v_Quotite, v_TypeQuotite
        FROM SAE_Quotter
        WHERE Id_Bien = p_Id_Bien
          AND type_quotite = v_TypeCompteur;

        DBMS_OUTPUT.PUT_LINE('Quotité trouvée : ' || v_Quotite || ', Type : ' || v_TypeQuotite);

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Aucune quotité trouvée pour le bien et le type de compteur.');
            RETURN 0;
    END;

    -- Récupérer le montant de la dernière facture liée au bien
    BEGIN
        SELECT NVL(SUM(montant), 0)
        INTO v_PrixConsommationTotale
        FROM SAE_Facture
        WHERE Id_Bien = p_Id_Bien
          AND designation = v_TypeCompteur
          AND date_emission > (SELECT NVL(MAX(date_derniere_reg), TO_DATE('1900-01-01', 'YYYY-MM-DD'))
                               FROM SAE_Louer
                               WHERE Id_Bien = p_Id_Bien);

        DBMS_OUTPUT.PUT_LINE('Montant total des factures : ' || v_PrixConsommationTotale);

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Aucune facture trouvée pour le bien.');
            RETURN 0;
    END;

    -- Vérifier si le montant total est valide
    IF v_PrixConsommationTotale < 0 THEN
        RAISE_APPLICATION_ERROR(-20005, 'Montant de la facture invalide.');
    END IF;

    -- Calculer la consommation pour le logement en prenant en compte la quotité
    v_ConsommationLogement := v_PrixConsommationTotale * (v_Quotite / 100);

    DBMS_OUTPUT.PUT_LINE('Consommation calculée pour le logement : ' || v_ConsommationLogement);

    -- Retourner la consommation calculée
    RETURN v_ConsommationLogement;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée pour la consommation.');
        RETURN 0;
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20006, 'Une erreur s''est produite : ' || SQLERRM);
        RETURN NULL;
END;
/


set serveroutput on
DECLARE
    v_PrixConsommation NUMBER;
BEGIN
    -- Test de la fonction avec des valeurs valides
    v_PrixConsommation := PrixConsoLogementQuotite('BIEN001', 'COMP0010');

    IF v_PrixConsommation IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('PrixConsoLogementQuotite : ' || v_PrixConsommation);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Erreur');
    END IF;
END;
/

CREATE OR REPLACE FUNCTION CalculerConsoBien(
    p_Id_Bien IN SAE_Compteur.Id_Bien%TYPE,
    p_Id_Compteur IN SAE_Compteur.Id_Compteur%TYPE
) RETURN NUMBER
IS
    -- Déclaration des variables
    v_Id_Bien_compteur SAE_Compteur.Id_Bien%TYPE;
    v_Id_Logement_compteur SAE_Compteur.Id_Logement%TYPE;
BEGIN
    -- Vérifier si le compteur est lié à un bien spécifique
    SELECT Id_Bien
    INTO v_Id_Bien_compteur
    FROM SAE_Compteur
    WHERE Id_Compteur = p_Id_Compteur;

    -- Vérifier si le compteur est lié à un logement
    SELECT Id_Logement
    INTO v_Id_Logement_compteur
    FROM SAE_Compteur
    WHERE Id_Compteur = p_Id_Compteur;

    -- Gérer le cas où le compteur n'est lié ni à un bien ni à un logement
    IF v_Id_Bien_compteur IS NULL AND v_Id_Logement_compteur IS NULL THEN
        RAISE_APPLICATION_ERROR(-20012, 'Le compteur spécifié n''est lié ni à un bien ni à un logement.');
    END IF;

    -- Si le compteur est propre (lié à un bien spécifique)
    IF v_Id_Bien_compteur IS NOT NULL THEN
        -- Appeler la fonction PrixConsoLogement
        RETURN CalculerPrixConsoLogement(p_Id_Bien, p_Id_Compteur);

    -- Si le compteur est général (lié à un logement ou immeuble)
    ELSIF v_Id_Logement_compteur IS NOT NULL THEN
        -- Appeler la fonction PrixConsoLogementQuotite
        RETURN PrixConsoLogementQuotite(p_Id_Bien, p_Id_Compteur);
    END IF;

    -- Gérer les autres exceptions
    RAISE_APPLICATION_ERROR(-20007, 'Erreur lors du calcul de la consommation.');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Gérer le cas où aucune donnée trouvée pour le compteur spécifié
        RETURN 0; -- Retourner 0 si aucune donnée trouvée
    WHEN OTHERS THEN
        -- Gérer les autres exceptions en affichant le message d'erreur d'origine
        RAISE_APPLICATION_ERROR(-20009, 'Une erreur s''est produite : ' || SQLERRM);
        RETURN NULL;
END;
/

DECLARE
    v_PrixConsommation NUMBER(15, 2);
BEGIN
    -- Appeler la fonction CalculerConsoBien
    v_PrixConsommation := CalculerConsoBien('BIEN001', 'COMP001');

    IF v_PrixConsommation IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('CalculerConsoBien : ' || v_PrixConsommation);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Erreur');
    END IF;
END;
/

CREATE OR REPLACE FUNCTION CalculChargesReellesTotal(
    p_Id_Bien IN SAE_Bien.Id_Bien%TYPE DEFAULT NULL
) RETURN NUMBER
IS
    -- Variable pour stocker la somme totale des consommations
    v_TotalConsommation NUMBER := 0;

    -- Curseur pour récupérer les compteurs liés au bien
    CURSOR cur_Compteurs IS
        SELECT Id_Compteur
        FROM SAE_Compteur
        WHERE Id_Bien = p_Id_Bien;

    -- Variable pour stocker la consommation d'un compteur
    v_ConsommationCompteur NUMBER;
BEGIN
    -- Vérification : si aucun bien n'est fourni, lever une erreur
    IF p_Id_Bien IS NULL THEN
        RAISE_APPLICATION_ERROR(-20010, 'L''identifiant du bien doit être spécifié.');
    END IF;

    -- Parcourir les compteurs liés au bien
    FOR rec_Compteur IN cur_Compteurs LOOP
        -- Calculer la consommation pour chaque compteur via la fonction CalculerConsommation
        v_ConsommationCompteur := CalculerPartieVariableConso(rec_Compteur.Id_Compteur);

        -- Ajouter la consommation du compteur à la somme totale
        v_TotalConsommation := v_TotalConsommation + COALESCE(v_ConsommationCompteur, 0);
    END LOOP;

    -- Retourner la somme totale des consommations
    RETURN v_TotalConsommation;

EXCEPTION
    WHEN OTHERS THEN
        -- Gérer les autres exceptions
        DBMS_OUTPUT.PUT_LINE('Une erreur s''est produite : ' || SQLERRM);
        RETURN NULL;
END CalculChargesReellesTotal;
/

set serveroutput on
DECLARE
    v_TotalConsommation NUMBER;
BEGIN
    -- Appel de la fonction pour un bien spécifique
    v_TotalConsommation := CalculChargesReellesTotal('BIEN001');

    -- Affichage du résultat
    DBMS_OUTPUT.PUT_LINE('Le total des charges réelles pour un bien : ' || v_TotalConsommation);
END;
/

CREATE OR REPLACE FUNCTION CalculerTotalChargesCompletes(
    p_Id_Bien IN SAE_Bien.Id_Bien%TYPE
) RETURN NUMBER
IS
    -- Variables pour stocker les totaux
    v_ChargesCompteurs NUMBER := 0;   -- Charges liées aux compteurs
    v_ChargesHorsCompteurs NUMBER := 0;   -- Charges issues de la table SAE_Charge
    v_TotalCharges NUMBER := 0;      -- Total des charges complètes

BEGIN
    -- Calculer les charges des compteurs (eau, électricité, etc.)
    v_ChargesCompteurs := CalculChargesReellesTotal(p_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('Total des charges liées aux compteurs : ' || v_ChargesCompteurs);

    -- Calculer les charges hors consommations de compteurs (issues de SAE_Charge)
    SELECT COALESCE(SUM(c.montant_reel), 0)
    INTO v_ChargesHorsCompteurs
    FROM SAE_Charge c
    WHERE c.Id_Bien = p_Id_Bien
      AND c.nom NOT IN ('Eau', 'Electricité', 'Gaz'); -- Exclure les consommations liées aux compteurs

    DBMS_OUTPUT.PUT_LINE('Total des charges hors compteurs (issues de SAE_Charge) : ' || v_ChargesHorsCompteurs);

    -- Calculer le total des charges complètes
    v_TotalCharges := v_ChargesCompteurs + v_ChargesHorsCompteurs;

    -- Retourner le total des charges complètes
    RETURN v_TotalCharges;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Gérer l'absence de données
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée pour les charges.');
        RETURN 0;
    WHEN OTHERS THEN
        -- Gérer les autres exceptions
        RAISE_APPLICATION_ERROR(-20001, 'Erreur inattendue : ' || SQLERRM);
END CalculerTotalChargesCompletes;
/

-- Bloc de test
set serveroutput on
DECLARE
    v_TotalCharges NUMBER;
BEGIN
    -- Appel de la fonction pour un bien spécifique
    v_TotalCharges := CalculerTotalChargesCompletes('BIEN001');

    -- Afficher le total des charges
    DBMS_OUTPUT.PUT_LINE('Le total des charges complètes pour le bien est : ' || v_TotalCharges);
END;
/


CREATE OR REPLACE FUNCTION CalculTotalProvisions (
    p_Id_Bien IN SAE_Louer.Id_Bien%TYPE
) RETURN NUMBER
IS
    -- Déclaration de la variable pour stocker le total des provisions
    v_total_provisions NUMBER(10, 2) := 0;
BEGIN
    -- Calcul du total des provisions pour le bien spécifié
    SELECT NVL(SUM(MONTHS_BETWEEN(SYSDATE, NVL(date_derniere_reg, SYSDATE)) * provisions_chargesMois_TTC), 0)
    INTO v_total_provisions
    FROM SAE_Louer
    WHERE Id_Bien = p_Id_Bien;

    -- Retourner le total des provisions calculé
    RETURN v_total_provisions;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Gérer le cas où aucune donnée n'est trouvée
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée pour le bien spécifié.');
        RETURN 0;
    WHEN OTHERS THEN
        -- Gérer les autres exceptions
        RAISE_APPLICATION_ERROR(-20001, 'Une erreur s''est produite : ' || SQLERRM);
        RETURN NULL;
END CalculTotalProvisions;
/

set serveroutput on 
DECLARE
    v_result NUMBER(10, 2);
BEGIN
    -- Appel de la fonction totalProvisions avec un ID de bien spécifique
    v_result := CalculTotalProvisions('BIEN001');

    -- Affichage du résultat
    DBMS_OUTPUT.PUT_LINE('Le total pour les provisions est : ' || v_result);
END;
/

CREATE OR REPLACE FUNCTION CalcultotalOrduresMenageres (
    p_Id_Bien IN SAE_Louer.Id_Bien%TYPE
) RETURN NUMBER
IS
    v_total_charges NUMBER := 0; -- Variable pour stocker le total des charges réelles
BEGIN
    -- Calcul du total des charges réelles pour le bien spécifié
    SELECT NVL(SUM(c.montant_reel), 0)
    INTO v_total_charges
    FROM SAE_Charge c
    WHERE c.Id_Bien = p_Id_Bien
      AND c.nom = 'Ordure menagere'
      AND c.date_charge BETWEEN (SELECT NVL(MAX(date_derniere_reg), TO_DATE('1900-01-01', 'YYYY-MM-DD'))
                                 FROM SAE_Louer
                                 WHERE Id_Bien = p_Id_Bien)
                             AND SYSDATE;

    -- Retourner le total des charges réelles calculé
    RETURN v_total_charges;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée pour le bien spécifié.');
        RETURN 0;
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Une erreur s''est produite : ' || SQLERRM);
        RETURN NULL;
END CalcultotalOrduresMenageres;
/


set serveroutput on
DECLARE
    v_result NUMBER;
BEGIN
    -- Appel de la fonction totalOrduresMenageres avec un ID de bien spécifique
    v_result := CalcultotalOrduresMenageres('BIEN001');
    
    -- Affichage du résultat
    DBMS_OUTPUT.PUT_LINE('Le total des charges pour les ordures ménagères : ' || v_result);
END;
/

CREATE OR REPLACE FUNCTION CalculDuLoyer (
    p_Id_Bien IN SAE_Louer.Id_Bien%TYPE
) RETURN NUMBER
IS
    v_totalLoyerReels NUMBER := 0;
    v_totalLoyerPayes NUMBER := 0;
BEGIN
    -- Calcul du total des loyers réels pour le bien spécifié
    SELECT NVL(SUM(f.montant), 0)
    INTO v_totalLoyerReels
    FROM SAE_Facture f
    INNER JOIN SAE_Louer l ON f.Id_Bien = l.Id_Bien
    WHERE l.Id_Bien = p_Id_Bien
      AND f.date_emission BETWEEN l.date_derniere_reg AND SYSDATE
      AND f.designation = 'Loyer';

    -- Calcul du total des loyers payés pour le bien spécifié
    SELECT NVL(SUM(f.montant_reel_verse), 0)
    INTO v_totalLoyerPayes
    FROM SAE_Facture f
    INNER JOIN SAE_Louer l ON f.Id_Bien = l.Id_Bien
    WHERE l.Id_Bien = p_Id_Bien
      AND f.date_emission BETWEEN l.date_derniere_reg AND SYSDATE
      AND f.designation = 'Loyer';

    -- Calcul et retour du montant restant dû
    RETURN v_totalLoyerReels - v_totalLoyerPayes;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Gérer le cas où aucune donnée n'est trouvée
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée pour le bien spécifié.');
        RETURN 0;
    WHEN OTHERS THEN
        -- Gérer les autres exceptions
        RAISE_APPLICATION_ERROR(-20001, 'Une erreur s''est produite : ' || SQLERRM);
        RETURN NULL;
END CalculDuLoyer;
/

set serveroutput on
DECLARE
    v_result NUMBER;
BEGIN
    -- Appel de la fonction restantDuLoyers avec un ID de bien spécifique
    v_result := CalculDuLoyer('BIEN001');
    
    -- Affichage du résultat
    DBMS_OUTPUT.PUT_LINE('Il reste à payer : ' || v_result);
END;
/

set serveroutput on
CREATE OR REPLACE FUNCTION CalculTravauxBienImmeuble(
    p_Id_Bien IN SAE_Bien.Id_Bien%TYPE
) RETURN NUMBER 
IS 
    v_total_bien NUMBER := 0;       -- Initialisation de la somme des travaux pour le bien
    v_total_immeuble NUMBER := 0;   -- Initialisation de la somme des travaux pour l'immeuble
BEGIN 
    -- Calculer la somme des travaux pour le bien (excluant les logements)
    SELECT COALESCE(SUM(f.montant), 0)
    INTO v_total_bien
    FROM SAE_Facture f
    WHERE f.Id_Bien = p_Id_Bien
      AND f.Id_Logement IS NULL  -- Exclure les logements
      AND f.designation = 'Travaux';

    DBMS_OUTPUT.PUT_LINE('Somme des travaux pour le bien (hors logements) : ' || v_total_bien);

    -- Calculer la somme des travaux pour les logements liés au bien
    SELECT COALESCE(SUM(f.montant), 0)
    INTO v_total_immeuble
    FROM SAE_Facture f
    WHERE f.Id_Bien = p_Id_Bien
      AND f.Id_Logement IS NOT NULL  -- Inclure uniquement les logements
      AND f.designation = 'Travaux';

    DBMS_OUTPUT.PUT_LINE('Somme des travaux pour les logements : ' || v_total_immeuble);

    -- Retourner la somme totale des travaux
    RETURN v_total_bien + v_total_immeuble;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée pour le bien spécifié.');
        RETURN 0;
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Une erreur s''est produite : ' || SQLERRM);
END;
/

DECLARE
    v_result NUMBER;
BEGIN
    -- Test de la fonction avec un ID de bien spécifique
    v_result := CalculTravauxBienImmeuble('BIEN001');
    DBMS_OUTPUT.PUT_LINE('Le total des travaux est : ' || TO_CHAR(v_result));
END;
/

CREATE OR REPLACE FUNCTION CalculTravauxImputableLoca(
    p_Id_Bien IN SAE_Bien.Id_Bien%TYPE
) RETURN NUMBER 
IS 
    v_total_bien NUMBER := 0;       
BEGIN 
    -- Calculer la somme des travaux imputables au locataire pour le bien
    SELECT COALESCE(SUM(f.montant), 0)
    INTO v_total_bien
    FROM SAE_Facture f
    WHERE f.Id_Bien = p_Id_Bien
      AND f.imputable_locataire = 1
      AND f.designation = 'Travaux';
    
    -- Retourner la somme totale des travaux imputables au locataire pour le bien
    RETURN v_total_bien;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Gérer le cas où aucune donnée n'est trouvée
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée pour le bien spécifié.');
        RETURN 0;
    WHEN OTHERS THEN
        -- Gérer les autres exceptions
        RAISE_APPLICATION_ERROR(-20001, 'Une erreur s''est produite : ' || SQLERRM);
END;
/

set serveroutput on
DECLARE
    v_result NUMBER;
    v_id_bien SAE_Bien.Id_Bien%TYPE := 'BIEN001'; -- Remplacez '8-RC' par un ID existant dans votre base
BEGIN
    v_result := CalculTravauxImputableLoca(p_Id_Bien => v_id_bien);
    DBMS_OUTPUT.PUT_LINE('Le total des travaux imputables au locataire est : ' || TO_CHAR(v_result));
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erreur : ' || SQLERRM);
END;
/


CREATE OR REPLACE FUNCTION CalculerRegularisationCharges (
    p_Id_Bien IN SAE_Louer.Id_Bien%TYPE,
    p_Id_Locataire IN SAE_Louer.Id_Locataire%TYPE,
    p_Date_Debut IN SAE_Louer.Date_Debut%TYPE
) RETURN NUMBER
IS
    -- Déclaration des variables locales
    v_charges_reelles NUMBER := 0;       -- Variable pour stocker le total des charges réelles
    v_provisions NUMBER := 0;            -- Variable pour stocker le total des provisions
    v_solde_apres NUMBER := 0;           -- Variable pour stocker le solde après régularisation
    v_restantLoyer NUMBER := 0;       -- Variable pour stocker le total des dettes de loyers

BEGIN
    -- Calculer les charges réelles pour le bien
    v_charges_reelles := CalcultotalOrduresMenageres(p_Id_Bien) + CalculerTotalChargesCompletes(p_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_charges_reelles : ' || v_charges_reelles);

    -- Calculer les provisions pour le bien
    v_provisions := CalculTotalProvisions(p_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_provisions : ' || v_provisions);

    -- Calculer les dettes de loyers pour le locataire
    v_restantLoyer := CalculDuLoyer(p_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_restantLoyer : ' || v_restantLoyer);

    -- Calculer le solde après régularisation
    v_solde_apres := v_charges_reelles + v_restantLoyer - v_provisions;

    -- Mettre à jour la date de dernière régularisation dans Louer
    UPDATE SAE_Louer
    SET date_derniere_reg = SYSDATE
    WHERE Id_Bien = p_Id_Bien
      AND Date_Debut = p_Date_Debut
      AND Id_Locataire = p_Id_Locataire;

    -- Retourner le solde après régularisation
    RETURN v_solde_apres;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Gérer l'absence de données
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée.');
        RETURN 0;
    WHEN OTHERS THEN
        -- Gérer toutes les autres exceptions
        RAISE_APPLICATION_ERROR(-20003, 'Erreur inattendue : ' || SQLERRM);
END CalculerRegularisationCharges;
/

set serveroutput on
DECLARE
    v_result NUMBER;
BEGIN
    -- Appel de la fonction avec un ID de bien spécifique, un locataire et une date de début
    v_result := CalculerRegularisationCharges('BIEN001', 'LOC001', TO_DATE('2024-03-14', 'YYYY-MM-DD'));

    -- Affichage du résultat
    DBMS_OUTPUT.PUT_LINE('Total des charges régularisées : ' || v_result);
END;
/


SET SERVEROUTPUT ON
CREATE OR REPLACE FUNCTION CalculerSoldeDeToutCompte (
    p_Id_Bien IN SAE_Louer.Id_Bien%TYPE,
    p_Id_Locataire IN SAE_Louer.Id_Locataire%TYPE,
    p_Date_Debut IN SAE_Louer.Date_Debut%TYPE
) RETURN NUMBER
IS
    -- Déclaration des variables locales
    v_chargesReelles NUMBER := 0;        -- Variable pour stocker le total des charges réelles
    v_provisions NUMBER := 0;            -- Variable pour stocker le total des provisions
    v_travauxImputables NUMBER := 0;     -- Variable pour stocker le total des travaux imputables
    v_soldeFinal NUMBER := 0;            -- Variable pour stocker le solde après régularisation
    v_caution NUMBER := 0;               -- Variable pour stocker la caution associée au bien
    v_restantLoyer NUMBER := 0;          -- Variable pour stocker le total des dettes de loyers
    v_OrduresMenageres NUMBER := 0;      -- Variable pour stocker le total des charges 'Ordures ménagères'
    v_totalCharges NUMBER := 0;          -- Variable pour stocker le total des charges réelles

BEGIN
    -- Récupérer la caution associée au bien
    SELECT caution_TTC 
    INTO v_caution
    FROM SAE_Louer
    WHERE Id_Bien = p_Id_Bien
      AND Date_Debut = p_Date_Debut
      AND Id_Locataire = p_Id_Locataire;

    DBMS_OUTPUT.PUT_LINE('v_caution : ' || v_caution);

    -- Calculer les charges réelles pour le locataire
    v_OrduresMenageres := CalculTotalOrduresMenageres(p_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_OrduresMenageres : ' || v_OrduresMenageres);

    v_totalCharges := CalculerTotalChargesCompletes(p_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_totalCharges : ' || v_totalCharges);

    v_chargesReelles := v_OrduresMenageres + v_totalCharges;
    DBMS_OUTPUT.PUT_LINE('v_chargesReelles : ' || v_chargesReelles);

    -- Calculer les provisions pour le locataire
    v_provisions := CalculTotalProvisions(p_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_provisions : ' || v_provisions);

    -- Calculer les travaux imputables au locataire
    v_travauxImputables := CalculTravauxImputableLoca(p_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_travauxImputables : ' || v_travauxImputables);

    -- Calculer les dettes de loyers pour le locataire
    v_restantLoyer := CalculDuLoyer(p_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_restantLoyer : ' || v_restantLoyer);

    -- Calculer le solde final après régularisation
    v_soldeFinal := v_provisions - v_chargesReelles + v_caution - v_travauxImputables - v_restantLoyer;
    DBMS_OUTPUT.PUT_LINE('v_soldeFinal : ' || v_soldeFinal);
    
    -- Mettre à jour la date de dernière régularisation dans Louer
    UPDATE SAE_Louer
    SET date_derniere_reg = SYSDATE
    WHERE Id_Bien = p_Id_Bien
      AND Date_Debut = p_Date_Debut
      AND Id_Locataire = p_Id_Locataire;

    -- Retourner le solde final après régularisation
    RETURN v_soldeFinal;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Gérer l'absence de données
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée');
        RETURN 0;
    WHEN OTHERS THEN
        -- Gérer toutes les autres exceptions
        RAISE_APPLICATION_ERROR(-20003, 'Erreur inattendue : ' || SQLERRM);
END CalculerSoldeDeToutCompte;
/


DECLARE
    v_result NUMBER;
BEGIN
    -- Appel de la fonction avec un ID de bien spécifique, un locataire et une date de début
    v_result := CalculerSoldeDeToutCompte('BIEN001', 'LOC001', TO_DATE('2023-01-1', 'YYYY-MM-DD'));

    -- Affichage du résultat
    DBMS_OUTPUT.PUT_LINE('CalculerSoldeDeToutCompte : ' || v_result);
END;
/