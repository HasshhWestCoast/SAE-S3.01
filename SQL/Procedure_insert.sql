/*
Étant donné que nous avons mis en place toutes les vérifications des paramètres de chaque table avec 
des contraintes telles que NOT NULL, CHECK, UNIQUE, etc., nous avons décidé de ne pas reproduire ces 
mêmes vérifications dans les procédures d'insertion. Par conséquent, ces procédures d'insertion effectuent 
l'insertion dans la base de données sans aucune vérification supplémentaire.

Nous avons créé  ces procedures pour privilégiér l'utilisation maximale de procédures PL/SQL, 
évitant ainsi d'effectuer des insertions directes via JDBC ou DAO.
*/

--------------------------LOGEMENT-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Logement(
    p_Id_Logement IN SAE_Logement.Id_Logement%TYPE,
    p_Surface_Habitable IN SAE_Logement.Surface_Habitable%TYPE,
    p_Date_Acquisition IN SAE_Logement.Date_Acquisition%TYPE,
    p_Type_Logement IN SAE_Logement.Type_Logement%TYPE,
    p_Nb_Pieces IN SAE_Logement.Nb_Pieces%TYPE,
    p_Num_Etage IN SAE_Logement.Num_Etage%TYPE,
    p_Garage IN SAE_Logement.Garage%TYPE 
) AS
BEGIN
    INSERT INTO SAE_Logement (Id_Logement, Surface_Habitable, Date_Acquisition, Type_Logement, Nb_Pieces, Num_Etage, Garage)
    VALUES (p_Id_Logement, p_Surface_Habitable, p_Date_Acquisition, p_Type_Logement, p_Nb_Pieces, p_Num_Etage, p_Garage);
END;
/

--------------------------IMPOT-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Impot(
    p_Id_Impot IN SAE_Impot.Id_Impot%TYPE,
    p_montant IN SAE_Impot.montant%TYPE,
    p_Annee IN SAE_Impot.Annee%TYPE
) AS
BEGIN
    INSERT INTO SAE_Impot (Id_Impot, montant, Annee)
    VALUES (p_Id_Impot, p_montant, p_Annee);
END;
/


--------------------------ENTREPRISE-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Entreprise(
    p_SIRET IN SAE_Entreprise.SIRET%TYPE,
    p_Nom IN SAE_Entreprise.Nom%TYPE,
    p_Adresse IN SAE_Entreprise.Adresse%TYPE,
    p_Ville IN SAE_Entreprise.Ville%TYPE,
    p_Mail IN SAE_Entreprise.Mail%TYPE,
    p_Telephone IN SAE_Entreprise.Telephone%TYPE,
    p_IBAN IN SAE_Entreprise.IBAN%TYPE
) AS
BEGIN
    INSERT INTO SAE_Entreprise (SIRET, Nom, Adresse, Ville, Mail, Telephone, IBAN)
    VALUES (p_SIRET, p_Nom, p_Adresse, p_Ville, p_Mail, p_Telephone, p_IBAN);
END;
/

--------------------------LOCATAIRE-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Locataire(
    p_Id_Locataire IN SAE_Locataire.Id_Locataire%TYPE,
    p_Nom IN SAE_Locataire.Nom%TYPE,
    p_Prenom IN SAE_Locataire.Prenom%TYPE,
    p_Telephone IN SAE_Locataire.Telephone%TYPE,
    p_Mail IN SAE_Locataire.Mail%TYPE,
    p_Date_Naissance IN SAE_Locataire.Date_Naissance%TYPE,
    p_Collocataire IN SAE_Locataire.Collocataire%TYPE
) AS
BEGIN
    INSERT INTO SAE_Locataire (Id_Locataire, Nom, Prenom, Telephone, Mail, Date_Naissance, Collocataire)
    VALUES (p_Id_Locataire, p_Nom, p_Prenom, p_Telephone, p_Mail, p_Date_Naissance, p_Collocataire);
END;
/

--------------------------ICC-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Icc(
    p_annee IN SAE_Icc.annee%TYPE,
    p_trimestre IN SAE_Icc.trimestre%TYPE,
    p_indice IN SAE_Icc.indice%TYPE
) AS
BEGIN 
    INSERT INTO SAE_Icc (annee, trimestre, indice)
    VALUES (p_annee, p_trimestre, p_indice);
END;
/


--------------------------BIEN-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Bien(
    p_Id_Bien IN SAE_Bien.Id_Bien%TYPE,
    p_Adresse IN SAE_Bien.Adresse%TYPE,
    p_Ville IN SAE_Bien.Ville%TYPE,
    p_Type_Bien IN SAE_Bien.Type_Bien%TYPE,
    p_CodePostal IN SAE_Bien.CodePostal%TYPE,
    p_Periode_Construction IN SAE_Bien.Periode_Construction%TYPE,
    p_Id_Logement IN SAE_Bien.Id_Logement%TYPE
) AS
BEGIN
    INSERT INTO SAE_Bien (Id_Bien, Adresse, Ville, Type_Bien, CodePostal, Periode_Construction, Id_Logement)
    VALUES (p_Id_Bien, p_Adresse, p_Ville, p_Type_Bien, p_CodePostal, p_Periode_Construction, p_Id_Logement);
END;
/

--------------------------DIAGNOSTIC-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Diagnostic(
    p_Id_Diagnostic IN SAE_Diagnostic.Id_Diagnostic%TYPE,
    p_Date_Validite IN SAE_Diagnostic.Date_Validite%TYPE,
    p_Type_Diagnostic IN SAE_Diagnostic.Type_Diagnostic%TYPE,
    p_Id_Bien IN SAE_Diagnostic.Id_Bien%TYPE
) AS
BEGIN
    INSERT INTO SAE_Diagnostic (Id_Diagnostic, Date_Validite, Type_Diagnostic, Id_Bien)
    VALUES (p_Id_Diagnostic, p_Date_Validite, p_Type_Diagnostic, p_Id_Bien);
END;
/

--------------------------ASSURANCE-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Assurance(
    p_Numero_Police IN SAE_Assurance.Numero_Police%TYPE,
    p_Montant IN SAE_Assurance.Montant%TYPE,
    p_Date_Echeance IN SAE_Assurance.Date_Echeance%TYPE,
    p_SIRET IN SAE_Assurance.SIRET%TYPE,
    p_Id_Logement IN SAE_Assurance.Id_Logement%TYPE
) AS
BEGIN
    INSERT INTO SAE_Assurance (Numero_Police, Montant, Date_Echeance, SIRET, Id_Logement)
    VALUES (p_Numero_Police, p_Montant, p_Date_Echeance, p_SIRET, p_Id_Logement);
END;
/

--------------------------FACTURE-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Facture(
    p_Id_Facture IN SAE_Facture.Id_Facture%TYPE,
    p_Date_Emission IN SAE_Facture.Date_Emission%TYPE,
    p_Date_Paiement IN SAE_Facture.Date_Paiement%TYPE,
    p_Mode_Paiement IN SAE_Facture.Mode_Paiement%TYPE,
    p_Numero_Devis IN SAE_Facture.Numero_Devis%TYPE,
    p_Designation IN SAE_Facture.Designation%TYPE,
    p_Montant_Reel_Verse IN SAE_Facture.Montant_Reel_Verse%TYPE,
    p_Montant IN SAE_Facture.Montant%TYPE,
    p_Imputable_Locataire IN SAE_Facture.Imputable_Locataire%TYPE,
    p_Acompte_Verse IN SAE_Facture.Acompte_Verse%TYPE,
    p_Id_Logement IN SAE_Facture.Id_Logement%TYPE,
    p_Id_Bien IN SAE_Facture.Id_Bien%TYPE,
    p_SIRET IN SAE_Facture.SIRET%TYPE
) AS
BEGIN
    INSERT INTO SAE_Facture (Id_Facture, Date_Emission, Date_Paiement, Mode_Paiement, Numero_Devis, Designation, Montant_Reel_Verse, Montant, Imputable_Locataire, Acompte_Verse, Id_Logement, Id_Bien, SIRET)
    VALUES (p_Id_Facture, p_Date_Emission, p_Date_Paiement, p_Mode_Paiement, p_Numero_Devis, p_Designation, p_Montant_Reel_Verse, p_Montant, p_Imputable_Locataire, p_Acompte_Verse, p_Id_Logement, p_Id_Bien, p_SIRET);
END;
/

--------------------------SAE_LOUER-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_SAE_Louer(
    p_Id_Bien IN SAE_Louer.Id_Bien%TYPE,
    p_Id_Locataire IN SAE_Louer.Id_Locataire%TYPE,
    p_Date_Debut IN SAE_Louer.Date_Debut%TYPE,
    p_Date_Sortie IN SAE_Louer.Date_Sortie%TYPE,
    p_Date_Derniere_Reg IN SAE_Louer.Date_Derniere_Reg%TYPE,
    p_Nb_Mois IN SAE_Louer.Nb_Mois%TYPE,
    p_Loyer_Mens_TTC IN SAE_Louer.Loyer_Mens_TTC%TYPE,
    p_Provisions_ChargesMois_TTC IN SAE_Louer.Provisions_ChargesMois_TTC%TYPE,
    p_Caution_TTC IN SAE_Louer.Caution_TTC%TYPE,
    p_Bail IN SAE_Louer.Bail%TYPE,
    p_Etat_Lieux IN SAE_Louer.Etat_Lieux%TYPE,
    p_Montant_Reel_Payer IN SAE_Louer.Montant_Reel_Payer%TYPE,
    p_ICC IN SAE_Louer.ICC%TYPE
) AS
BEGIN
    INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_Debut, Date_Sortie, Date_Derniere_Reg, Nb_Mois, Loyer_Mens_TTC, Provisions_ChargesMois_TTC, Caution_TTC, Bail, Etat_Lieux, Montant_Reel_Payer, ICC)
    VALUES (p_Id_Bien, p_Id_Locataire, p_Date_Debut, p_Date_Sortie, p_Date_Derniere_Reg, p_Nb_Mois, p_Loyer_Mens_TTC, p_Provisions_ChargesMois_TTC, p_Caution_TTC, p_Bail, p_Etat_Lieux, p_Montant_Reel_Payer, p_ICC);
END;
/
--------------------------CHARGE-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Charge(
    p_Id_Charges IN SAE_Charge.Id_Charges%TYPE,
    p_Nom IN SAE_Charge.Nom%TYPE,
    p_Montant_Reel IN SAE_Charge.Montant_Reel%TYPE,
    p_Montant_Previsionnel IN SAE_Charge.Montant_Previsionnel%TYPE,
    p_Deductible IN SAE_Charge.Deductible%TYPE,
    p_Date_Charge IN SAE_Charge.Date_Charge%TYPE,
    p_Id_Bien IN SAE_Charge.Id_Bien%TYPE
) AS
BEGIN
    INSERT INTO SAE_Charge (Id_Charges, Nom, Montant_Reel, Montant_Previsionnel, Deductible, Date_Charge, Id_Bien)
    VALUES (p_Id_Charges, p_Nom, p_Montant_Reel, p_Montant_Previsionnel, p_Deductible, p_Date_Charge, p_Id_Bien);
END;
/

--------------------------COMPTEUR-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Compteur(
    p_Id_Compteur IN SAE_Compteur.Id_Compteur%TYPE,
    p_TypeComp IN SAE_Compteur.TypeComp%TYPE,
    p_IndexCompteur IN SAE_Compteur.IndexCompteur%TYPE,
    p_Date_Releve IN SAE_Compteur.Date_Releve%TYPE,
    p_Id_Logement IN SAE_Compteur.Id_Logement%TYPE,
    p_Id_Bien IN SAE_Compteur.Id_Bien%TYPE
) AS
BEGIN
    INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, IndexCompteur, Date_Releve, Id_Logement, Id_Bien)
    VALUES (p_Id_Compteur, p_TypeComp, p_IndexCompteur, p_Date_Releve, p_Id_Logement, p_Id_Bien);
END;
/

--------------------------IMPOSER-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Imposer(
    p_Id_Bien IN SAE_Imposer.Id_Bien%TYPE,
    p_Id_Impot IN SAE_Imposer.Id_Impot%TYPE
) AS
BEGIN
    INSERT INTO SAE_Imposer (Id_Bien, Id_Impot)
    VALUES (p_Id_Bien, p_Id_Impot);
END;
/

--------------------------RETIENT-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Retient(
    p_Id_Bien IN SAE_Retient.Id_Bien%TYPE,
    p_Id_Locataire IN SAE_Retient.Id_Locataire%TYPE,
    p_Date_Debut IN SAE_Retient.Date_Debut%TYPE,
    p_Id_Charges IN SAE_Retient.Id_Charges%TYPE
) AS
BEGIN
    INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_Debut, Id_Charges)
    VALUES (p_Id_Bien, p_Id_Locataire, p_Date_Debut, p_Id_Charges);
END;
/

--------------------------QUOTITE-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Quotite(
    p_Type_Quotite IN SAE_Quotite.Type_Quotite%TYPE
) AS
BEGIN
    INSERT INTO SAE_Quotite (Type_Quotite)
    VALUES (p_Type_Quotite);
END;
/

--------------------------QUOTTER-------------------------------------
CREATE OR REPLACE PROCEDURE Inserer_Quotter(
    p_Id_Bien IN SAE_Quotter.Id_Bien%TYPE,
    p_Type_Quotite IN SAE_Quotter.Type_Quotite%TYPE,
    p_Pourcentage IN SAE_Quotter.Pourcentage%TYPE
) AS
BEGIN
    INSERT INTO SAE_Quotter (Id_Bien, Type_Quotite, Pourcentage)
    VALUES (p_Id_Bien, p_Type_Quotite, p_Pourcentage);
END;
/
