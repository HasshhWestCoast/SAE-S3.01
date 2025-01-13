
-------------------------- LOCATAIRE -------------------------------------

-- Déclencheur pour vérifier la date de naissance du locataire
CREATE OR REPLACE TRIGGER SAE_check_date_naissance
BEFORE INSERT OR UPDATE ON SAE_Locataire
FOR EACH ROW
BEGIN
   IF :NEW.date_naissance >= SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date de naissance ne peut pas être égale ou ultérieure à la date actuelle.');
   END IF;
END;
/

-- Déclencheur pour vérifier si le locataire est majeur
CREATE OR REPLACE TRIGGER SAE_trg_check_majeur
BEFORE INSERT OR UPDATE ON SAE_Locataire
FOR EACH ROW
BEGIN
   IF MONTHS_BETWEEN(SYSDATE, :NEW.date_naissance) < 216 THEN
      RAISE_APPLICATION_ERROR(-20002, 'Le locataire doit être majeur');
   END IF;
END;
/

-------------------------- LOGEMENT -------------------------------------

-- Déclencheur pour vérifier que la date d'acquisition du logement n'est pas ultérieure à la date actuelle
CREATE OR REPLACE TRIGGER SAE_check_date_acquisition
BEFORE INSERT OR UPDATE ON SAE_Logement
FOR EACH ROW
BEGIN
   IF :NEW.date_acquisition > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date dacquisition ne peut pas être ultérieure à la date actuelle.');
   END IF;
END;
/

CREATE OR REPLACE TRIGGER SAE_CHECK_TYPE_LOGEMENT
BEFORE INSERT OR UPDATE ON SAE_Logement
FOR EACH ROW
BEGIN
   -- Vérification du type de logement et du nombre de pièces
   IF (:NEW.type_logement = 'Maison' AND :NEW.nb_pieces > 10) THEN
      RAISE_APPLICATION_ERROR(-20003, 'Une maison ne peut pas avoir plus de 10 pièces.');
   END IF;

   -- Vérification de la proportionnalité entre nb_pieces et surface_habitable
   IF (:NEW.nb_pieces * 10 > :NEW.surface_habitable) THEN
      RAISE_APPLICATION_ERROR(-20004, 'Le nombre de pièces doit être proportionnel à la surface habitable.');
   END IF;
END;
/


-- Déclencheur pour vérifier que le nombre de pièces est proportionnel à la surface habitable
CREATE OR REPLACE TRIGGER SAE_verif_nb_pieces
BEFORE INSERT OR UPDATE ON SAE_Logement
FOR EACH ROW
BEGIN
   IF :NEW.nb_pieces * 10 > :NEW.surface_habitable THEN
      RAISE_APPLICATION_ERROR(-20001, 'Le nombre de pièces doit être proportionnel à la surface habitable.');
   END IF;
END;
/

-------------------------- ICC -------------------------------------

-- Déclencheur pour vérifier la validité de l'année de l'ICC lors de l'insertion ou de la mise à jour
CREATE OR REPLACE TRIGGER SAE_icc_check_date
BEFORE INSERT OR UPDATE ON SAE_ICC
FOR EACH ROW
BEGIN
   IF :NEW.annee <= '1900' OR :NEW.annee > TO_CHAR(SYSDATE, 'YYYY') THEN
      RAISE_APPLICATION_ERROR(-20001, 'Lannée de lICC doit être supérieure à 1900 et inférieure ou égale à lannée actuelle.');
   END IF;
END;
/


-------------------------- LOUER -------------------------------------
-- Déclencheur pour attribuer une valeur par défaut à la date de la dernière régularisation si elle est NULL lors de l'insertion
CREATE OR REPLACE TRIGGER SAE_louer_datedr_defaut
BEFORE INSERT ON SAE_Louer
FOR EACH ROW
BEGIN
  -- Vérifier si la date de la dernière régularisation est NULL
  IF :NEW.date_derniere_reg IS NULL THEN
    -- Si c'est le cas, attribuer la valeur de la date de début de location à la date de la dernière régularisation
    :NEW.date_derniere_reg := :NEW.Date_Debut;
  END IF;
END;
/

-- Déclencheur pour vérifier que la date de début de location n'est pas ultérieure à la date actuelle
CREATE OR REPLACE TRIGGER SAE_louer_check_dateDebut
BEFORE INSERT OR UPDATE ON SAE_Louer
FOR EACH ROW
BEGIN
   IF :NEW.Date_debut > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date de début de location ne peut pas être ultérieure à la date actuelle.');
   END IF;
END;
/

-- Déclencheur pour vérifier que la date de la dernière régularisation n'est pas ultérieure à la date actuelle lors de l'insertion ou de la mise à jour
CREATE OR REPLACE TRIGGER SAE_louer_check_date_dern_reg
BEFORE INSERT OR UPDATE ON SAE_Louer
FOR EACH ROW
BEGIN
   -- Vérifier si la date de la dernière régularisation est postérieure à la date actuelle
   IF :NEW.date_derniere_reg > SYSDATE THEN
      -- Si la condition est vraie, déclencher une erreur d'application avec le code -20002
      RAISE_APPLICATION_ERROR(-20002, 'La date de la dernière régularisation ne peut pas être ultérieure à la date actuelle.');
   END IF;
END;
/

-------------------------- DIAGNOSTIC -------------------------------------
-- Déclencheur pour vérifier que la date de validité du diagnostic est ultérieure à la date actuelle
CREATE OR REPLACE TRIGGER SAE_DIAGNOSTIC_CHECK_DATE
BEFORE INSERT OR UPDATE ON SAE_Diagnostic
FOR EACH ROW
BEGIN
    -- Vérifier si la date de validité est bien dans le futur
    IF :NEW.date_validite <= SYSDATE THEN
        RAISE_APPLICATION_ERROR(-20001, 'La date de validité doit être ultérieure à la date actuelle.');
    END IF;
END;
/

-------------------------- FACTURE -------------------------------------

-- Déclencheur pour vérifier que la date d'émission de la facture n'est pas ultérieure à la date actuelle
CREATE OR REPLACE TRIGGER SAE_check_date_emission
BEFORE INSERT OR UPDATE ON SAE_Facture
FOR EACH ROW
BEGIN
   IF :NEW.date_emission > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date demission de la facture ne peut pas être ultérieure à la date actuelle.');
   END IF;
END;
/

-- Déclencheur pour vérifier que la date de paiement de la facture n'est pas ultérieure à la date actuelle
CREATE OR REPLACE TRIGGER SAE_check_date_paiement
BEFORE INSERT OR UPDATE ON SAE_Facture
FOR EACH ROW
BEGIN
   IF :NEW.date_paiement > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date de paiement ne peut pas être ultérieure à la date actuelle.');
   END IF;
END;
/

-- Déclencheur pour vérifier que la date d'émission de la facture n'est pas ultérieure à la date de paiement lors de l'insertion ou de la mise à jour
CREATE OR REPLACE TRIGGER facture_d_emission_p_trigger
BEFORE INSERT OR UPDATE ON SAE_Facture
FOR EACH ROW
BEGIN
   -- Vérifier si la date d'émission de la facture est postérieure à la date de paiement
   IF :NEW.date_emission > :NEW.date_paiement THEN
      -- Si la condition est vraie, déclencher une erreur d'application avec le code -20002
      RAISE_APPLICATION_ERROR(-20002, 'La date d''émission ne peut pas être ultérieure à la date de paiement.');
   END IF;
END;
/

--------------------------COMPTEUR-------------------------------------
-- Déclencheur pour vérifier que la date du relevé n'est pas ultérieure à la date actuelle lors de l'insertion ou de la mise à jour
CREATE OR REPLACE TRIGGER releve_date_releve_trigger
BEFORE INSERT OR UPDATE ON SAE_Relevé
FOR EACH ROW
BEGIN
   -- Vérifier si la date du relevé est postérieure à la date actuelle
   IF :NEW.date_relevé > SYSDATE THEN
      -- Si la condiion est vraie, déclencher une erreur d'application avec le code -20001
      RAISE_APPLICATION_ERROR(-20001, 'La date du relevé ne peut pas être ultérieure à la date actuelle.');
   END IF;
END;
/

------------------------- IMPOT -------------------------------------

-- Déclencheur pour vérifier que l'année de l'impôt n'est pas supérieure à l'année actuelle moins un an lors de l'insertion
CREATE OR REPLACE TRIGGER checkAnneeImpot
BEFORE INSERT ON SAE_Impot
FOR EACH ROW
DECLARE
    v_AnneeActuelle NUMBER;
BEGIN
    -- Obtenir l'année actuelle
    SELECT EXTRACT(YEAR FROM SYSDATE) - 1 INTO v_AnneeActuelle FROM dual;

    -- Vérifier si l'année de l'impôt est supérieure à l'année actuelle moins un an
    IF :new.annee > v_AnneeActuelle THEN
        -- Si la condition est vraie, annuler l'insertion en levant une exception avec le code -20001
        RAISE_APPLICATION_ERROR(-20001, 'L''année de l''impôt ne peut pas être supérieure à l''année actuelle moins un an.');
    END IF;
END checkAnneeImpot;
/