
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

-- Déclencheur pour vérifier le type de logement en fonction de ses caractéristiques
CREATE OR REPLACE TRIGGER SAE_check_type_logement
BEFORE INSERT OR UPDATE ON SAE_Logement
FOR EACH ROW
BEGIN
   IF (:NEW.type_logement = 'Maison' AND :NEW.nb_pieces > 10) THEN
      RAISE_APPLICATION_ERROR(-20003, 'Une maison ne peut pas avoir plus de 10 pièces.');
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

-------------------------- DIAGNOSTIC -------------------------------------

-- Déclencheur pour vérifier que la date de validité du diagnostic est ultérieure à la date actuelle
CREATE OR REPLACE TRIGGER SAE_diagnostic_check_date
BEFORE INSERT OR UPDATE ON SAE_Diagnostic
FOR EACH ROW
BEGIN
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
