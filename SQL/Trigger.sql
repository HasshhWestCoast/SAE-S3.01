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

-------------------------- ICC -------------------------------------

-- Déclencheur pour vérifier la validité de l'année de l'ICC lors de l'insertion ou de la mise à jour
CREATE OR REPLACE TRIGGER SAE_icc_check_date
BEFORE INSERT OR UPDATE ON SAE_ICC
FOR EACH ROW
BEGIN
   -- Vérifier si l'année de l'ICC est antérieure à 1900 ou supérieure à l'année actuelle
   IF :NEW.annee <= '1900' OR :NEW.annee > TO_CHAR(SYSDATE, 'YYYY') THEN
      RAISE_APPLICATION_ERROR(-20001, 'L''année de l''ICC doit être supérieure à 1900 et inférieure ou égale à l''année actuelle.');
   END IF;
END;
/

-------------------------- BIEN -------------------------------------

-- Déclencheur pour vérifier que la date d'acquisition du bien n'est pas ultérieure à la date actuelle
CREATE OR REPLACE TRIGGER SAE_check_date_acquisition
BEFORE INSERT OR UPDATE ON SAE_Bien
FOR EACH ROW
BEGIN
   -- Vérifier si la date d'acquisition est postérieure à la date actuelle
   IF :NEW.date_acquisition > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date d''acquisition ne peut pas être ultérieure à la date actuelle.');
   END IF;
END;
/

-- Déclencheur pour vérifier le type de bien en fonction du type d'immeuble associé
CREATE OR REPLACE TRIGGER SAE_check_type_bien
BEFORE INSERT OR UPDATE ON SAE_Bien
FOR EACH ROW
DECLARE
    v_imm_type VARCHAR2(30);
BEGIN
    -- Récupérer le type de l'immeuble associé au bien
    SELECT type_immeuble INTO v_imm_type
    FROM SAE_Immeuble
    WHERE Id_Immeuble = :NEW.Id_Immeuble;

    -- Vérifier les conditions selon le type de l'immeuble
    IF (v_imm_type = 'Immeuble' AND :NEW.type_bien NOT IN ('Appartement', 'Garage')) THEN
        RAISE_APPLICATION_ERROR(
            -20022,
            'Un immeuble ne peut contenir que des appartements ou des garages'
        );
    ELSIF (v_imm_type = 'Maison' AND :NEW.type_bien <> 'Maison') THEN
        RAISE_APPLICATION_ERROR(
            -20023,
            'Une maison ne peut contenir que des biens de type Maison'
        );
    END IF;
END;
/

-- Déclencheur pour vérifier qu'un immeuble de type "maison" ne contient qu'un seul logement
CREATE OR REPLACE TRIGGER SAE_verif_maison_solo
BEFORE INSERT OR UPDATE ON SAE_Bien
FOR EACH ROW
DECLARE
    v_imm_type VARCHAR2(15);
    v_nb_logements NUMBER;
BEGIN
    -- Récupérer le type de l'immeuble associé au bien
    SELECT type_immeuble INTO v_imm_type
    FROM SAE_Immeuble
    WHERE Id_Immeuble = :NEW.Id_Immeuble;

    -- Vérifier si l'immeuble est de type "maison"
    IF v_imm_type = 'Maison' THEN
        -- Compter le nombre de logements existants de type "Maison" dans l'immeuble (exclure l'enregistrement en cours)
        SELECT COUNT(*) INTO v_nb_logements
        FROM SAE_Bien
        WHERE Id_Immeuble = :NEW.Id_Immeuble
          AND type_bien = 'Maison'
          AND Id_Bien != :NEW.Id_Bien;

        -- Si le nombre de logements est supérieur à 0, déclencher une erreur
        IF v_nb_logements > 0 THEN
            RAISE_APPLICATION_ERROR(-20023,'Un immeuble de type "maison" ne peut contenir qu''un seul logement.');
        END IF;
    END IF;
END;
/

-- Déclencheur pour vérifier si le nombre de pièces est proportionnel à la surface habitable
CREATE OR REPLACE TRIGGER SAE_verif_nb_pieces
BEFORE INSERT OR UPDATE ON SAE_Bien
FOR EACH ROW
BEGIN
    -- Vérifier si le nombre de pièces est proportionnel à la surface habitable
    IF :NEW.nb_pieces * 10 > :NEW.surface_habitable THEN
        RAISE_APPLICATION_ERROR(-20001, 'Le nombre de pièces doit être proportionnel à la surface.');
    END IF;
END;
/

-------------------------- DIAGNOSTIC -------------------------------------

-- Déclencheur pour vérifier que la date de validité du diagnostic est ultérieure à la date actuelle
CREATE OR REPLACE TRIGGER SAE_diagnostic_check_date
BEFORE INSERT OR UPDATE ON SAE_Diagnostic
FOR EACH ROW
BEGIN
   -- Vérifier si la date de validité est antérieure ou égale à la date actuelle
   IF :NEW.date_validite <= SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date de validité doit être ultérieure à la date actuelle.');
   END IF;
END;
/

-------------------------- LOUER -------------------------------------

-- Déclencheur pour vérifier que la date de début de location n'est pas ultérieure à la date actuelle lors de l'insertion ou de la mise à jour
CREATE OR REPLACE TRIGGER SAE_louer_check_dateDebut
BEFORE INSERT OR UPDATE ON SAE_Louer
FOR EACH ROW
BEGIN
   -- Vérifier si la date de début de location est postérieure à la date actuelle
   IF :NEW.Date_Debut > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date de début de location ne peut pas être ultérieure à la date actuelle.');
   END IF;
END;
/

-------------------------- FACTURE -------------------------------------

-- Déclencheur pour vérifier que la date d'émission de la facture n'est pas ultérieure à la date actuelle lors de l'insertion ou de la mise à jour
CREATE OR REPLACE TRIGGER SAE_check_date_emission
BEFORE INSERT OR UPDATE ON SAE_Facture
FOR EACH ROW
BEGIN
   -- Vérifier si la date d'émission de la facture est postérieure à la date actuelle
   IF :NEW.date_emission > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date d''émission de la facture ne peut pas être ultérieure à la date actuelle.');
   END IF;
END;
/

-- Déclencheur pour vérifier que la date de paiement de la facture n'est pas ultérieure à la date actuelle lors de l'insertion ou de la mise à jour
CREATE OR REPLACE TRIGGER SAE_check_date_paiement
BEFORE INSERT OR UPDATE ON SAE_Facture
FOR EACH ROW
BEGIN
   -- Vérifier si la date de paiement de la facture est postérieure à la date actuelle
   IF :NEW.date_paiement > SYSDATE THEN
      RAISE_APPLICATION_ERROR(-20001, 'La date de paiement ne peut pas être ultérieure à la date actuelle.');
   END IF;
END;
/
