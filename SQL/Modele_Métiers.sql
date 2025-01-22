------------- CONSOMMATION D'UN COMPTEUR POUR UN BIEN DONNÉ ---------------
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


------- CALCULE DU PRIX DE LA PARTIE VARIABLE D'UN COMPTEUR EN FONCTION DE SON TYPE -----------
-- Fonction pour calculer le coût variable de la consommation en fonction du type de compteur
-- La consommation multipliée par le prix du m3 

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

-- Fonction CalculerPrixConsoLogement_SP
-- Cette fonction calcule le prix total de la consommation d'un logement spécifique en fonction du compteur associé.
-- Elle utilise une chaîne d'entrée contenant l'ID du logement et du compteur.
CREATE OR REPLACE FUNCTION CalculerPrixConsoLogement_SP(
    p_Input IN VARCHAR2
) RETURN NUMBER
IS
    -- Variables pour stocker les paramètres extraits
    v_Id_Logement SAE_Compteur.Id_Logement%TYPE;
    v_Id_Compteur SAE_Compteur.Id_Compteur%TYPE;
    -- Variable pour stocker le prix total de la consommation
    v_PrixTotal NUMBER := 0;
    -- Variable pour stocker la partie variable de la consommation
    v_PartieVariable NUMBER := 0;
BEGIN
    -- Extraire les paramètres de l'entrée unique
    SELECT SUBSTR(p_Input, 1, INSTR(p_Input, ',') - 1),
           SUBSTR(p_Input, INSTR(p_Input, ',') + 1)
    INTO v_Id_Logement, v_Id_Compteur
    FROM DUAL;

    DBMS_OUTPUT.PUT_LINE('ID Logement : ' || v_Id_Logement);
    DBMS_OUTPUT.PUT_LINE('ID Compteur : ' || v_Id_Compteur);

    -- Récupérer la partie variable de la consommation via CalculerPartieVariableConso
    v_PartieVariable := CalculerPartieVariableConso(v_Id_Compteur);

    -- Vérifier si la partie variable est valide
    IF v_PartieVariable IS NULL THEN
        v_PartieVariable := 0; -- Par défaut, 0 si non calculé
    END IF;

    -- Assigner la partie variable calculée au prix total
    v_PrixTotal := v_PartieVariable;

    DBMS_OUTPUT.PUT_LINE('Prix total de la consommation : ' || v_PrixTotal);

    -- Retourner le prix total
    RETURN v_PrixTotal;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Erreur : ' || SQLERRM);
        RETURN NULL;
END;
/

set serveroutput on
DECLARE
    v_Input VARCHAR2(100) := 'LOG001,COMP001';
    v_Result NUMBER;
BEGIN
    v_Result := CalculerPrixConsoLogement_SP(v_Input);
    DBMS_OUTPUT.PUT_LINE('Prix total de la consommation pour le logement : ' || v_Result);
END;
/

--CREATE OR REPLACE FUNCTION PrixConsoLogementQuotite(
    --p_Id_Bien IN SAE_Bien.Id_Bien%TYPE,
    --p_Id_Compteur IN SAE_Compteur.Id_Compteur%TYPE
--) RETURN NUMBER
--IS
    -- Déclaration des variables
    --v_ConsommationLogement NUMBER := 0;
    --v_Quotite NUMBER(20, 10);
    --v_TypeQuotite VARCHAR2(50);
    --v_TypeCompteur SAE_Compteur.TypeComp%TYPE;
    --v_PrixConsommationTotale NUMBER := 0;

BEGIN
    -- Récupérer le type de compteur
    --SELECT TypeComp
    --INTO v_TypeCompteur
    --FROM SAE_Compteur
    --WHERE Id_Compteur = p_Id_Compteur;

    --DBMS_OUTPUT.PUT_LINE('Type de compteur : ' || v_TypeCompteur);

    -- Récupérer la quotité du bien
    --BEGIN
        --SELECT pourcentage, type_quotite
        --INTO v_Quotite, v_TypeQuotite
        --FROM SAE_Quotter
        --WHERE Id_Bien = p_Id_Bien
          --AND type_quotite = v_TypeCompteur;

        --DBMS_OUTPUT.PUT_LINE('Quotité trouvée : ' || v_Quotite || ', Type : ' || v_TypeQuotite);

    --EXCEPTION
        --WHEN NO_DATA_FOUND THEN
            --DBMS_OUTPUT.PUT_LINE('Aucune quotité trouvée pour le bien et le type de compteur.');
            --RETURN 0;
    --END;

    -- Récupérer le montant de la dernière facture liée au bien
    --BEGIN
        --SELECT NVL(SUM(montant), 0)
        --INTO v_PrixConsommationTotale
        --FROM SAE_Facture
        --WHERE Id_Bien = p_Id_Bien
          --AND designation = v_TypeCompteur
          --AND date_emission > (SELECT NVL(MAX(date_derniere_reg), TO_DATE('1900-01-01', 'YYYY-MM-DD'))
                               --FROM SAE_Louer
                               --WHERE Id_Bien = p_Id_Bien);

        --DBMS_OUTPUT.PUT_LINE('Montant total des factures : ' || v_PrixConsommationTotale);

    --EXCEPTION
        --WHEN NO_DATA_FOUND THEN
            --DBMS_OUTPUT.PUT_LINE('Aucune facture trouvée pour le bien.');
            --RETURN 0;
    --END;

    -- Vérifier si le montant total est valide
    --IF v_PrixConsommationTotale < 0 THEN
      --  RAISE_APPLICATION_ERROR(-20005, 'Montant de la facture invalide.');
    --END IF;

    -- Calculer la consommation pour le logement en prenant en compte la quotité
    --v_ConsommationLogement := v_PrixConsommationTotale * (v_Quotite / 100);

    --DBMS_OUTPUT.PUT_LINE('Consommation calculée pour le logement : ' || v_ConsommationLogement);

    -- Retourner la consommation calculée
    --RETURN v_ConsommationLogement;

--EXCEPTION
    --WHEN NO_DATA_FOUND THEN
        --DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée pour la consommation.');
        --RETURN 0;
    --WHEN OTHERS THEN
        --RAISE_APPLICATION_ERROR(-20006, 'Une erreur s''est produite : ' || SQLERRM);
        --RETURN NULL;
--END;
--/


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

-- Fonction CalculerConsoBien_SP
-- Cette fonction calcule la consommation pour un compteur spécifique associé à un bien.
-- Elle déduit la consommation à partir des relevés et applique un coût en fonction du type de compteur.
CREATE OR REPLACE FUNCTION CalculerConsoBien_SP(
    p_Input IN VARCHAR2
) RETURN NUMBER
IS
    -- Déclaration des variables
    v_Id_Bien SAE_Compteur.Id_Bien%TYPE;
    v_Id_Compteur SAE_Compteur.Id_Compteur%TYPE;
    v_NouvelIndice NUMBER := 0;
    v_AncienIndice NUMBER := 0;
    v_Consommation NUMBER := 0;
    v_Result NUMBER := 0;
BEGIN
    -- Extraction des paramètres depuis l'entrée unique
    v_Id_Bien := SUBSTR(p_Input, 1, INSTR(p_Input, ',') - 1);
    v_Id_Compteur := SUBSTR(p_Input, INSTR(p_Input, ',') + 1);

    -- Vérifier si le compteur est lié au bien spécifié
    SELECT MAX(indexCompteur) INTO v_NouvelIndice
    FROM SAE_Relevé r
    WHERE r.Id_Compteur = v_Id_Compteur;

    SELECT MAX(indexCompteur) INTO v_AncienIndice
    FROM SAE_Relevé r
    WHERE r.Id_Compteur = v_Id_Compteur
      AND r.date_relevé < (SELECT MAX(date_relevé) FROM SAE_Relevé WHERE Id_Compteur = v_Id_Compteur);

    -- Calculer la consommation
    IF v_AncienIndice IS NOT NULL THEN
        v_Consommation := v_NouvelIndice - v_AncienIndice;
    ELSE
        v_Consommation := 0;
    END IF;

    -- Vérifier le type du compteur et calculer les coûts
    SELECT CASE
            WHEN TypeComp = 'Electricité' THEN v_Consommation * 0.2516
            WHEN TypeComp = 'Eau' THEN v_Consommation * 4.34
            WHEN TypeComp = 'Gaz' THEN v_Consommation * 1.025
            ELSE 0
        END
    INTO v_Result
    FROM SAE_Compteur
    WHERE Id_Compteur = v_Id_Compteur;

    RETURN v_Result;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée pour le compteur.');
        RETURN 0;
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004, 'Erreur inattendue : ' || SQLERRM);
END;
/

set serveroutput on
DECLARE
    v_Input VARCHAR2(100) := 'BIEN001,COMP001'; -- ID Bien et ID Compteur, séparés par une virgule
    v_Result NUMBER; -- Variable pour stocker le résultat
BEGIN
    -- Appel de la fonction avec l'entrée unique
    v_Result := CalculerConsoBien_SP(v_Input);

    -- Affichage du résultat
    IF v_Result IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('Prix de la consommation pour le bien : ' || v_Result);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Erreur lors du calcul.');
    END IF;
END;
/

-- Fonction CalculChargesReellesTotal
-- Cette fonction calcule le total des consommations réelles associées à un bien spécifique.
-- Elle récupère tous les compteurs liés au bien, calcule la consommation pour chacun d'eux 
-- en utilisant la fonction CalculerPartieVariableConso, puis retourne la somme totale de ces consommations.
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


-- Fonction CalculerTotalChargesCompletes
-- Cette fonction calcule le total des charges complètes pour un bien spécifique.
-- Elle additionne :
-- 1. Les charges liées aux compteurs (eau, électricité, gaz) calculées via la fonction CalculChargesReellesTotal.
-- 2. Les charges hors compteurs (issues de la table SAE_Charge) excluant les consommations des compteurs.
-- Le résultat est la somme de ces deux catégories de charges.
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

-- Fonction CalculTotalProvisions
-- Cette fonction calcule le total des provisions pour un bien donné.
-- Elle multiplie la durée en mois entre la date actuelle et la date de la dernière régularisation (ou la date actuelle par défaut si aucune date n'est définie) par les provisions mensuelles.
-- Le résultat est la somme des provisions calculées pour le bien spécifié.
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

-- SOMMES TOTAL ORDURES MENAGERES ---
-- Fonction pour calculer le total des charges réelles liées aux ordures ménagères pour un bien loué spécifié
-- Elle récupère la somme des montants des factures émises entre la date de la dernière régularisation et la date actuelle
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

-- Fonction CalculDuLoyer
-- Cette fonction calcule la dette locative restante pour un bien donné.
-- Elle détermine la différence entre le total des loyers facturés et le total des loyers effectivement payés.
-- Les calculs prennent en compte uniquement les factures de loyer émises entre la date de la dernière régularisation et la date actuelle.
-- Le résultat est la somme des loyers dus pour le bien spécifié.
CREATE OR REPLACE FUNCTION CalculDuLoyer (
    p_Id_Bien IN SAE_Louer.Id_Bien%TYPE
) RETURN NUMBER
IS
    v_totalLoyerReels NUMBER := 0;
    v_totalLoyerPayes NUMBER := 0;
BEGIN
    -- Débogage : Afficher l'ID du bien
    DBMS_OUTPUT.PUT_LINE('Calcul en cours pour le bien : ' || p_Id_Bien);

    -- Calcul du total des loyers réels pour le bien spécifié
    SELECT NVL(SUM(f.montant), 0)
    INTO v_totalLoyerReels
    FROM SAE_Facture f
    INNER JOIN SAE_Louer l ON f.Id_Bien = l.Id_Bien
    WHERE l.Id_Bien = p_Id_Bien
      AND f.date_emission >= NVL(l.date_derniere_reg, TO_DATE('1900-01-01', 'YYYY-MM-DD'))
      AND f.date_emission <= SYSDATE
      AND f.designation = 'Loyer';

    -- Debugging : Total loyers réels
    DBMS_OUTPUT.PUT_LINE('Total loyers réels : ' || v_totalLoyerReels);

    -- Calcul du total des loyers payés pour le bien spécifié
    SELECT NVL(SUM(f.montant_reel_verse), 0)
    INTO v_totalLoyerPayes
    FROM SAE_Facture f
    INNER JOIN SAE_Louer l ON f.Id_Bien = l.Id_Bien
    WHERE l.Id_Bien = p_Id_Bien
      AND f.date_emission >= NVL(l.date_derniere_reg, TO_DATE('1900-01-01', 'YYYY-MM-DD'))
      AND f.date_emission <= SYSDATE
      AND f.designation = 'Loyer';

    -- Debugging : Total loyers payés
    DBMS_OUTPUT.PUT_LINE('Total loyers payés : ' || v_totalLoyerPayes);

    -- Calcul et retour du montant restant dû
    RETURN v_totalLoyerReels - v_totalLoyerPayes;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée pour le bien spécifié.');
        RETURN 0;
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Une erreur est survenue : ' || SQLERRM);
        RETURN NULL;
END CalculDuLoyer;
/


DECLARE
    v_result NUMBER;
BEGIN
    -- Test de la fonction avec un ID de bien spécifique
    v_result := CalculTravauxBienImmeuble('BIEN001');
    DBMS_OUTPUT.PUT_LINE('Le total des travaux est : ' || TO_CHAR(v_result));
END;
/

---------------------------------------------------------
------------- CALCUL TRAVAUX IMPUTABLES ---------------
---------------------------------------------------------
-- Fonction pour calculer la somme totale des travaux imputables au locataire pour un bien spécifié
-- recupere le total des travaux realiser sur le bien puis retourne la somme 
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

-- Fonction CalculerReguCharges_SP
-- Cette fonction calcule la régularisation des charges pour un locataire d'un bien spécifique.
-- Elle prend en compte les charges réelles, les provisions et les loyers restants.
CREATE OR REPLACE FUNCTION CalculerReguCharges_SP(
    p_Input IN VARCHAR2
) RETURN NUMBER
IS
    -- Déclaration des variables
    v_Id_Bien SAE_Louer.Id_Bien%TYPE;
    v_Id_Locataire SAE_Louer.Id_Locataire%TYPE;
    v_Date_Debut DATE;
    v_ChargesReelles NUMBER := 0;
    v_Provisions NUMBER := 0;
    v_RestantLoyer NUMBER := 0;
    v_SoldeApres NUMBER := 0;
BEGIN
    -- Extraction des paramètres depuis l'entrée unique
    v_Id_Bien := SUBSTR(p_Input, 1, INSTR(p_Input, ',') - 1);
    v_Id_Locataire := SUBSTR(p_Input, INSTR(p_Input, ',') + 1, INSTR(p_Input, ',', INSTR(p_Input, ',') + 1) - INSTR(p_Input, ',') - 1);
    v_Date_Debut := TO_DATE(SUBSTR(p_Input, INSTR(p_Input, ',', INSTR(p_Input, ',') + 1) + 1), 'YYYY-MM-DD');

    v_ChargesReelles := CalculerTotalChargesCompletes(v_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_ChargesReelles : ' || v_ChargesReelles);

    v_Provisions := CalculTotalProvisions(v_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_Provisions : ' || v_Provisions);

    v_RestantLoyer := CalculDuLoyer(v_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_RestantLoyer : ' || v_RestantLoyer);

    -- Calculer le solde après régularisation
    v_SoldeApres := v_ChargesReelles + v_RestantLoyer - v_Provisions;
    DBMS_OUTPUT.PUT_LINE('v_SoldeApres : ' || v_SoldeApres);


    -- Retourner le solde après régularisation
    RETURN v_SoldeApres;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Gérer l'absence de données
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée.');
        RETURN 0;
    WHEN OTHERS THEN
        -- Gérer toutes les autres exceptions
        RAISE_APPLICATION_ERROR(-20007, 'Erreur inattendue : ' || SQLERRM);
END;
/

set serveroutput on
DECLARE
    v_Input VARCHAR2(100) := 'BIEN001,LOC001,2023-01-01'; -- ID Bien, ID Locataire, Date Début, séparés par des virgules
    v_Result NUMBER; -- Variable pour stocker le résultat
BEGIN
    -- Appel de la fonction avec l'entrée unique
    v_Result := CalculerReguCharges_SP(v_Input);

    -- Affichage du résultat
    IF v_Result IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('Total des charges régularisées : ' || v_Result);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Erreur lors du calcul.');
    END IF;
END;
/


----------------------------------------------
---------- SOLDE DE TOUT COMPTE --------------
----------------------------------------------
-- Fonction pour calculer le solde de tout compte pour un locataire et un bien spécifiés
-- prend en compte les charges réelles, les provision , les travaux imputable , la caution 
-- pour determiner le solde final apres regularisation 

CREATE OR REPLACE FUNCTION CalculerSoldeToutCompte_SP(
    p_Input IN VARCHAR2
) RETURN NUMBER
IS
    -- Déclaration des variables locales
    v_Id_Bien SAE_Louer.Id_Bien%TYPE;
    v_Id_Locataire SAE_Louer.Id_Locataire%TYPE;
    v_Date_Debut DATE;
    v_ChargesReelles NUMBER := 0;        -- Total des charges réelles
    v_Provisions NUMBER := 0;            -- Total des provisions
    v_TravauxImputables NUMBER := 0;     -- Total des travaux imputables
    v_SoldeFinal NUMBER := 0;            -- Solde après régularisation
    v_Caution NUMBER := 0;               -- Caution associée au bien
    v_RestantLoyer NUMBER := 0;          -- Total des dettes de loyers
    v_OrduresMenageres NUMBER := 0;      -- Total des charges 'Ordures ménagères'
    v_TotalCharges NUMBER := 0;          -- Total des charges réelles
BEGIN
    -- Extraction des paramètres depuis l'entrée unique
    v_Id_Bien := SUBSTR(p_Input, 1, INSTR(p_Input, ',') - 1);
    v_Id_Locataire := SUBSTR(p_Input, INSTR(p_Input, ',') + 1, INSTR(p_Input, ',', INSTR(p_Input, ',') + 1) - INSTR(p_Input, ',') - 1);
    v_Date_Debut := TO_DATE(SUBSTR(p_Input, INSTR(p_Input, ',', INSTR(p_Input, ',') + 1) + 1), 'YYYY-MM-DD');

    -- Récupérer la caution associée au bien
    SELECT caution_TTC
    INTO v_Caution
    FROM SAE_Louer
    WHERE Id_Bien = v_Id_Bien
      AND Date_Debut = v_Date_Debut
      AND Id_Locataire = v_Id_Locataire;

    DBMS_OUTPUT.PUT_LINE('v_Caution : ' || v_Caution);

    -- Appel des fonctions pour obtenir les valeurs nécessaires
    v_OrduresMenageres := CalcultotalOrduresMenageres(v_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_OrduresMenageres : ' || v_OrduresMenageres);

    v_TotalCharges := CalculerTotalChargesCompletes(v_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_TotalCharges : ' || v_TotalCharges);

    v_ChargesReelles := v_OrduresMenageres + v_TotalCharges;
    DBMS_OUTPUT.PUT_LINE('v_ChargesReelles : ' || v_ChargesReelles);

    v_Provisions := CalculTotalProvisions(v_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_Provisions : ' || v_Provisions);

    v_TravauxImputables := CalculTravauxImputableLoca(v_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_TravauxImputables : ' || v_TravauxImputables);

    v_RestantLoyer := CalculDuLoyer(v_Id_Bien);
    DBMS_OUTPUT.PUT_LINE('v_RestantLoyer : ' || v_RestantLoyer);

    -- Calculer le solde final après régularisation
    v_SoldeFinal := v_Provisions - v_ChargesReelles + v_Caution - v_TravauxImputables - v_RestantLoyer;
    DBMS_OUTPUT.PUT_LINE('v_SoldeFinal : ' || v_SoldeFinal);


    -- Retourner le solde final après régularisation
    RETURN v_SoldeFinal;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Gérer l'absence de données
        DBMS_OUTPUT.PUT_LINE('Aucune donnée trouvée.');
        RETURN 0;
    WHEN OTHERS THEN
        -- Gérer toutes les autres exceptions
        RAISE_APPLICATION_ERROR(-20003, 'Erreur inattendue : ' || SQLERRM);
END;
/


set serveroutput on
DECLARE
    v_Input VARCHAR2(100) := 'BIEN001,LOC001,2023-01-01'; -- ID Bien, ID Locataire, Date Début, séparés par des virgules
    v_Result NUMBER; -- Variable pour stocker le résultat
BEGIN
    -- Appel de la fonction avec l'entrée unique
    v_Result := CalculerSoldeToutCompte_SP(v_Input);

    -- Affichage du résultat
    IF v_Result IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('Solde de tout compte : ' || v_Result);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Erreur lors du calcul.');
    END IF;
END;
/