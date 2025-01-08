-- Supprimer les tables existantes dans le bon ordre
DROP TABLE SAE_Retient CASCADE CONSTRAINTS;
DROP TABLE SAE_Imposer CASCADE CONSTRAINTS;
DROP TABLE SAE_Compteur CASCADE CONSTRAINTS;
DROP TABLE SAE_Charge CASCADE CONSTRAINTS;
DROP TABLE SAE_Louer CASCADE CONSTRAINTS;
DROP TABLE SAE_Facture CASCADE CONSTRAINTS;
DROP TABLE SAE_Assurance CASCADE CONSTRAINTS;
DROP TABLE SAE_Diagnostic CASCADE CONSTRAINTS;
DROP TABLE SAE_Bien CASCADE CONSTRAINTS;
DROP TABLE SAE_ICC CASCADE CONSTRAINTS;
DROP TABLE SAE_Locataire CASCADE CONSTRAINTS;
DROP TABLE SAE_Entreprise CASCADE CONSTRAINTS;
DROP TABLE SAE_Impot CASCADE CONSTRAINTS;
DROP TABLE SAE_Logement CASCADE CONSTRAINTS;

-- Supprimer les séquences
DROP SEQUENCE compteur_Impot;
DROP SEQUENCE compteur_Diagnostic;

-- Créer les séquences
CREATE SEQUENCE compteur_Impot START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE compteur_Diagnostic START WITH 1 INCREMENT BY 1;

--------------------------LOGEMENT-------------------------------------
CREATE TABLE SAE_Logement(
   Id_Logement VARCHAR2(30) CONSTRAINT SAE_pk_log PRIMARY KEY,
   surface_habitable NUMBER CONSTRAINT SAE_nn_bien_surface_habitable NOT NULL,
   date_acquisition DATE CONSTRAINT SAE_nn_bien_date_acq NOT NULL,
   type_logement VARCHAR2(30) CONSTRAINT SAE_nn_log_type NOT NULL,
   nb_pieces INT CONSTRAINT SAE_nn_bien_nb_pieces NOT NULL,
   num_etage INT CONSTRAINT SAE_nn_bien_etage NOT NULL
);

--------------------------IMPOT-------------------------------------
CREATE TABLE SAE_Impot(
   Id_Impot VARCHAR2(50) PRIMARY KEY,
   montant NUMBER CONSTRAINT SAE_nn_impot_montant NOT NULL,
   annee VARCHAR2(50) CONSTRAINT SAE_nn_impot_annee NOT NULL
);

--------------------------ENTREPRISE-------------------------------------
CREATE TABLE SAE_Entreprise(
   SIRET CHAR(14) CONSTRAINT SAE_pk_entreprise PRIMARY KEY,
   nom VARCHAR2(50) CONSTRAINT SAE_nn_entreprise_nom NOT NULL,
   adresse VARCHAR2(50) CONSTRAINT SAE_nn_entreprise_adresse NOT NULL,
   codepostal CHAR(5) CONSTRAINT SAE_nn_entreprise_cp NOT NULL,
   ville VARCHAR2(50) CONSTRAINT SAE_nn_entreprise_ville NOT NULL,
   mail VARCHAR2(50),
   telephone VARCHAR2(15) CONSTRAINT SAE_nn_entreprise_tel NOT NULL,
   IBAN CHAR(34) CONSTRAINT SAE_nn_entreprise_iban NOT NULL,
   CONSTRAINT SAE_un_entreprise_iban UNIQUE(IBAN),
   CONSTRAINT SAE_ck_entreprise_mail CHECK (REGEXP_LIKE(mail, '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$'))
);

--------------------------LOCATAIRE-------------------------------------
CREATE TABLE SAE_Locataire(
   Id_Locataire VARCHAR2(30) CONSTRAINT SAE_pk_locataire PRIMARY KEY,
   nom VARCHAR2(30) CONSTRAINT SAE_nn_locataire_nom NOT NULL,
   prenom VARCHAR2(30) CONSTRAINT SAE_nn_locataire_prenom NOT NULL,
   telephone VARCHAR2(15) CONSTRAINT SAE_nn_locataire_tel NOT NULL,
   mail VARCHAR2(50),
   date_naissance DATE CONSTRAINT SAE_nn_locataire_dn NOT NULL,
   quotite NUMBER CONSTRAINT SAE_nn_locataire_quotite NOT NULL,
   CONSTRAINT SAE_ck_locataire_mail CHECK (REGEXP_LIKE(mail, '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$'))
);

--------------------------ICC-------------------------------------
CREATE TABLE SAE_ICC(
   annee VARCHAR2(50),
   trimestre VARCHAR2(50),
   indice NUMBER CONSTRAINT SAE_nn_icc_indice NOT NULL,
   CONSTRAINT SAE_pk_icc PRIMARY KEY (annee, trimestre)
);

--------------------------BIEN-------------------------------------
CREATE TABLE SAE_Bien(
   Id_Bien VARCHAR2(30) CONSTRAINT SAE_pk_bien PRIMARY KEY,
   adresse VARCHAR2(50) CONSTRAINT SAE_nn_log_adresse NOT NULL,
   ville VARCHAR2(50) CONSTRAINT SAE_nn_log_ville NOT NULL,
   type_bien VARCHAR2(30) CONSTRAINT SAE_nn_bien_type NOT NULL,
   codepostal CHAR(5) CONSTRAINT SAE_nn_log_cp NOT NULL,
   periode_construction VARCHAR2(15),
   Id_Logement VARCHAR2(30) CONSTRAINT SAE_fk_bien_Logement REFERENCES SAE_Logement(Id_Logement) NOT NULL,
   CONSTRAINT SAE_un_log_adresse UNIQUE(adresse, codepostal, ville),
   CONSTRAINT SAE_ck_log_cp CHECK (REGEXP_LIKE(codepostal, '^[0-9]{5}$'))
);

--------------------------DIAGNOSTIC-------------------------------------
CREATE TABLE SAE_Diagnostic(
   Id_Diagnostic VARCHAR2(50) PRIMARY KEY,
   date_validite DATE CONSTRAINT SAE_nn_diagnostic_date_valid NOT NULL,
   type_diagnostic VARCHAR2(50) CONSTRAINT SAE_nn_diagnostic_type NOT NULL,
   Id_Bien VARCHAR2(30) CONSTRAINT SAE_fk_diagnostic_bien REFERENCES SAE_Bien(Id_Bien) NOT NULL
);

--------------------------ASSURANCE-------------------------------------
CREATE TABLE SAE_Assurance(
   numero_police VARCHAR2(50) PRIMARY KEY,
   montant NUMBER NOT NULL,
   date_echeance DATE NOT NULL,
   SIRET CHAR(14) CONSTRAINT SAE_fk_assurance_siret REFERENCES SAE_Entreprise(SIRET),
   Id_Logement VARCHAR2(30) CONSTRAINT SAE_fk_assurance_logement REFERENCES SAE_Logement(Id_Logement)
);

--------------------------FACTURE-------------------------------------
CREATE TABLE SAE_Facture(
   Id_Facture VARCHAR2(50) PRIMARY KEY,
   date_emission DATE CONSTRAINT SAE_nn_facture_date_emission NOT NULL,
   date_paiement DATE,
   mode_paiement VARCHAR2(30),
   numero_devis VARCHAR2(50),
   designation VARCHAR2(50) CONSTRAINT SAE_nn_facture_designation NOT NULL,
   montant_reel_verse NUMBER,
   montant NUMBER CONSTRAINT SAE_nn_facture_montant NOT NULL,
   imputable_locataire NUMBER(1) CONSTRAINT SAE_ck_facture_imputable_loc CHECK (imputable_locataire IN (0, 1)),
   acompte_verse NUMBER,
   Id_Logement VARCHAR2(30),
   Id_Bien VARCHAR2(30),
   SIRET CHAR(14),
   CONSTRAINT SAE_fk_facture_Logement FOREIGN KEY(Id_Logement) REFERENCES SAE_Logement(Id_Logement),
   CONSTRAINT SAE_fk_facture_bien FOREIGN KEY(Id_Bien) REFERENCES SAE_Bien(Id_Bien),
   CONSTRAINT SAE_fk_facture_siret FOREIGN KEY(SIRET) REFERENCES SAE_Entreprise(SIRET)
);

--------------------------LOUER-------------------------------------
CREATE TABLE SAE_Louer(
   Id_Bien VARCHAR2(30) CONSTRAINT SAE_fk_louer_bien REFERENCES SAE_Bien(Id_Bien) NOT NULL,
   Id_Locataire VARCHAR2(30) CONSTRAINT SAE_fk_louer_locataire REFERENCES SAE_Locataire(Id_Locataire) NOT NULL,
   Date_debut DATE CONSTRAINT SAE_nn_louer_date_debut NOT NULL,
   date_derniere_reg DATE,
   nb_mois INT CONSTRAINT SAE_ck_louer_nb_mois CHECK (nb_mois > 0),
   loyer_payer NUMBER(1),
   provisions_chargesMois_TTC NUMBER,
   caution_TTC NUMBER,
   bail VARCHAR2(50),
   etat_lieux VARCHAR2(50),
   montant_reel_payer NUMBER,
   annee VARCHAR2(50),
   trimestre VARCHAR2(50),
   CONSTRAINT SAE_pk_louer PRIMARY KEY (Id_Bien, Id_Locataire, Date_debut),
   CONSTRAINT SAE_fk_louer_icc FOREIGN KEY (annee, trimestre) REFERENCES SAE_ICC(annee, trimestre)
);

--------------------------CHARGE-------------------------------------
CREATE TABLE SAE_Charge(
   Id_Charges VARCHAR2(50) PRIMARY KEY,
   nom VARCHAR2(50),
   montant_reel NUMBER CONSTRAINT SAE_ck_charge_montant_reel CHECK (montant_reel >= 0),
   montant_previsionnel NUMBER CONSTRAINT SAE_ck_charge_montant_prev CHECK (montant_previsionnel >= 0),
   deductible INT,
   date_charge DATE,
   Id_Bien VARCHAR2(30) CONSTRAINT SAE_fk_charge_bien REFERENCES SAE_Bien(Id_Bien) NOT NULL
);

--------------------------COMPTEUR-------------------------------------
CREATE TABLE SAE_Compteur(
   Id_Compteur VARCHAR2(50) PRIMARY KEY,
   TypeComp VARCHAR2(50) CONSTRAINT SAE_nn_compteur_type NOT NULL,
   indexCompteur INT,
   date_releve DATE,
   Id_Logement VARCHAR2(30),
   Id_Bien VARCHAR2(30),
   CONSTRAINT SAE_fk_compteur_logement FOREIGN KEY(Id_Logement) REFERENCES SAE_Logement(Id_Logement),
   CONSTRAINT SAE_fk_compteur_bien FOREIGN KEY(Id_Bien) REFERENCES SAE_Bien(Id_Bien)
);

--------------------------IMPOSER-------------------------------------
CREATE TABLE SAE_Imposer(
   Id_Bien VARCHAR2(30) CONSTRAINT SAE_fk_imposer_bien REFERENCES SAE_Bien(Id_Bien),
   Id_Impot VARCHAR2(50) CONSTRAINT SAE_fk_imposer_impot REFERENCES SAE_Impot(Id_Impot),
   CONSTRAINT SAE_pk_imposer PRIMARY KEY(Id_Bien, Id_Impot)
);

--------------------------RETIENT-------------------------------------
CREATE TABLE SAE_Retient(
   Id_Bien VARCHAR2(30) NOT NULL,
   Id_Locataire VARCHAR2(30) NOT NULL,
   Date_debut DATE NOT NULL,
   Id_Charges VARCHAR2(50) NOT NULL,
   CONSTRAINT SAE_pk_retient PRIMARY KEY (Id_Bien, Id_Locataire, Date_debut, Id_Charges),
   CONSTRAINT SAE_fk_retient_bien FOREIGN KEY (Id_Bien) REFERENCES SAE_Bien(Id_Bien),
   CONSTRAINT SAE_fk_retient_locataire FOREIGN KEY (Id_Locataire) REFERENCES SAE_Locataire(Id_Locataire),
   CONSTRAINT SAE_fk_retient_charges FOREIGN KEY (Id_Charges) REFERENCES SAE_Charge(Id_Charges)
);