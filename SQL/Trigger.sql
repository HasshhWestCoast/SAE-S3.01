-------------------------- LOCATAIRE -------------------------------------

-- Vérifie si la date de naissance est dans le passé
CREATE OR REPLACE TRIGGER SAE_check_date_naissance
BEFORE INSERT OR UPDATE ON SAE_Locataire
FOR EACH ROW
BEGIN
   IF :NEW.date_naissance >= SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date de naissance doit être dans le passé.');
   END IF;
END;
/

-- Vérifie si le locataire est majeur
CREATE OR REPLACE TRIGGER SAE_trg_check_majeur
BEFORE INSERT OR UPDATE ON SAE_Locataire
FOR EACH ROW
BEGIN
   IF MONTHS_BETWEEN(SYSDATE, :NEW.date_naissance) < 216 THEN
      RAISE_APPLICATION_ERROR(-20002, 'Le locataire doit être majeur.');
   END IF;
END;
/

-------------------------- LOGEMENT -------------------------------------

-- Vérifie si la date d'acquisition est dans le passé
CREATE OR REPLACE TRIGGER SAE_check_date_acquisition
BEFORE INSERT OR UPDATE ON SAE_Logement
FOR EACH ROW
BEGIN
   IF :NEW.date_acquisition > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date d''acquisition doit être dans le passé.');
   END IF;
END;
/

-- Vérifie les contraintes sur le type de logement et les pièces
CREATE OR REPLACE TRIGGER SAE_CHECK_TYPE_LOGEMENT
BEFORE INSERT OR UPDATE ON SAE_Logement
FOR EACH ROW
BEGIN
   IF (:NEW.type_logement = 'Maison' AND :NEW.nb_pieces > 10) THEN
      RAISE_APPLICATION_ERROR(-20003, 'Une maison ne peut pas avoir plus de 10 pièces.');
   END IF;
   IF (:NEW.nb_pieces * 10 > :NEW.surface_habitable) THEN
      RAISE_APPLICATION_ERROR(-20004, 'Nombre de pièces disproportionné par rapport à la surface habitable.');
   END IF;
END;
/

-------------------------- ICC -------------------------------------

-- Vérifie que l'année de l'ICC est valide
CREATE OR REPLACE TRIGGER SAE_icc_check_date
BEFORE INSERT OR UPDATE ON SAE_ICC
FOR EACH ROW
BEGIN
   IF :NEW.annee <= '1900' OR :NEW.annee > TO_CHAR(SYSDATE, 'YYYY') THEN
      RAISE_APPLICATION_ERROR(-20001, 'L''année de l''ICC doit être entre 1900 et l''année actuelle.');
   END IF;
END;
/

-------------------------- LOUER -------------------------------------

-- Attribue une valeur par défaut à la date de dernière régularisation
CREATE OR REPLACE TRIGGER SAE_louer_datedr_defaut
BEFORE INSERT ON SAE_Louer
FOR EACH ROW
BEGIN
  IF :NEW.date_derniere_reg IS NULL THEN
    :NEW.date_derniere_reg := :NEW.Date_Debut;
  END IF;
END;
/

-- Vérifie si la date de début de location est dans le passé
CREATE OR REPLACE TRIGGER SAE_louer_check_dateDebut
BEFORE INSERT OR UPDATE ON SAE_Louer
FOR EACH ROW
BEGIN
   IF :NEW.Date_debut > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date de début doit être dans le passé.');
   END IF;
END;
/

-------------------------- DIAGNOSTIC -------------------------------------

-- Vérifie si la date de validité du diagnostic est dans le futur
CREATE OR REPLACE TRIGGER SAE_DIAGNOSTIC_CHECK_DATE
BEFORE INSERT OR UPDATE ON SAE_Diagnostic
FOR EACH ROW
BEGIN
    IF :NEW.date_validite <= SYSDATE THEN
        RAISE_APPLICATION_ERROR(-20001, 'La date de validité doit être dans le futur.');
    END IF;
END;
/

-------------------------- FACTURE -------------------------------------

-- Vérifie si la date d'émission est dans le passé
CREATE OR REPLACE TRIGGER SAE_check_date_emission
BEFORE INSERT OR UPDATE ON SAE_Facture
FOR EACH ROW
BEGIN
   IF :NEW.date_emission > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date d''émission doit être dans le passé.');
   END IF;
END;
/

-- Vérifie si la date de paiement est dans le passé
CREATE OR REPLACE TRIGGER SAE_check_date_paiement
BEFORE INSERT OR UPDATE ON SAE_Facture
FOR EACH ROW
BEGIN
   IF :NEW.date_paiement > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date de paiement doit être dans le passé.');
   END IF;
END;
/

-- Vérifie si la date d'émission précède la date de paiement
CREATE OR REPLACE TRIGGER SAE_facture_demission_p_trig
BEFORE INSERT OR UPDATE ON SAE_Facture
FOR EACH ROW
BEGIN
   IF :NEW.date_emission > :NEW.date_paiement THEN
      RAISE_APPLICATION_ERROR(-20002, 'La date d''émission ne peut pas être postérieure à la date de paiement.');
   END IF;
END;
/

--------------------------COMPTEUR-------------------------------------

-- Vérifie si la date du relevé est dans le passé
CREATE OR REPLACE TRIGGER SAE_releve_date_releve_trigger
BEFORE INSERT OR UPDATE ON SAE_Relevé
FOR EACH ROW
BEGIN
   IF :NEW.date_relevé > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date du relevé doit être dans le passé.');
   END IF;
END;
/

------------------------- IMPOT -------------------------------------

-- Vérifie si l'année de l'impôt est correcte
CREATE OR REPLACE TRIGGER SAE_checkAnneeImpot
BEFORE INSERT ON SAE_Impot
FOR EACH ROW
DECLARE
    v_AnneeActuelle NUMBER;
BEGIN
    SELECT EXTRACT(YEAR FROM SYSDATE) - 1 INTO v_AnneeActuelle FROM dual;
    IF :NEW.annee > v_AnneeActuelle THEN
        RAISE_APPLICATION_ERROR(-20001, 'L''année de l''impôt ne peut pas dépasser l''année actuelle moins un an.');
    END IF;
END checkAnneeImpot;
/
