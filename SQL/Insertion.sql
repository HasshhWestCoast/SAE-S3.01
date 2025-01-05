-- ---------------------- LOGEMENT ----------------------
INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, type_immeuble, nb_pieces, num_etage)
VALUES ('LOG001', 80, TO_DATE('2015-06-15', 'YYYY-MM-DD'), 'Maison', 3, 1);

INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, type_immeuble, nb_pieces, num_etage)
VALUES ('LOG002', 150, TO_DATE('2010-04-10', 'YYYY-MM-DD'), 'Immeuble', 5, 2);

INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, type_immeuble, nb_pieces, num_etage)
VALUES ('LOG003', 60, TO_DATE('2020-08-20', 'YYYY-MM-DD'), 'Maison', 2, 0);

-- ---------------------- IMPOT ----------------------
INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP001', 1500, '2023');

INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP002', 1800, '2022');

INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP003', 1200, '2021');

-- ---------------------- ENTREPRISE ----------------------
INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('12345678901234', 'Energie Sud', '1 Boulevard de Strasbourg', '31000', 'Toulouse', 'contact@energiesud.fr', '0561123456', 'FR7612345678901234567890123');

INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('98765432109876', 'Nettoyage Pro', '15 Rue Gambetta', '31000', 'Toulouse', 'info@netpro.fr', '0561123457', 'FR7612345678909876543210987');

INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('45678912345678', 'Ascenseurs Occitans', '10 Avenue des États-Unis', '31200', 'Toulouse', 'contact@ascensoc.fr', '0561123458', 'FR7612345678904567891234567');

-- ---------------------- LOCATAIRE ----------------------
INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, quotite)
VALUES ('LOC001', 'Dupont', 'Marie', '0612345678', 'mariedupont@example.com', TO_DATE('1990-05-12', 'YYYY-MM-DD'), 25);

INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, quotite)
VALUES ('LOC002', 'Martin', 'Jean', '0612345679', 'jeanmartin@example.com', TO_DATE('1985-08-24', 'YYYY-MM-DD'), 30);

INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, quotite)
VALUES ('LOC003', 'Durand', 'Sophie', '0612345680', 'sophiedurand@example.com', TO_DATE('1992-11-15', 'YYYY-MM-DD'), 20);

-- ---------------------- ICC ----------------------
INSERT INTO SAE_ICC (annee, trimestre, indice)
VALUES ('2023', '1', 112.5);

INSERT INTO SAE_ICC (annee, trimestre, indice)
VALUES ('2023', '2', 113.0);

INSERT INTO SAE_ICC (annee, trimestre, indice)
VALUES ('2023', '3', 114.2);

-- ---------------------- BIEN ----------------------
INSERT INTO SAE_Bien (Id_Bien, adresse, codepostal, ville, periode_construction, type_bien, Id_Logement)
VALUES ('BIEN001', '12 Rue Lafayette', '31000', 'Toulouse', '1980-1990', 'Appartement', 'LOG001');

INSERT INTO SAE_Bien (Id_Bien, adresse, codepostal, ville, periode_construction, type_bien, Id_Logement)
VALUES ('BIEN002', '45 Avenue des Minimes', '31200', 'Toulouse', '1990-2000', 'Maison', 'LOG002');

INSERT INTO SAE_Bien (Id_Bien, adresse, codepostal, ville, periode_construction, type_bien, Id_Logement)
VALUES ('BIEN003', '8 Place du Capitole', '31000', 'Toulouse', 'Avant 1900', 'Garage', 'LOG003');

-- ---------------------- DIAGNOSTIC ----------------------
INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG001', TO_DATE('2025-06-30', 'YYYY-MM-DD'), 'Énergie', 'BIEN001');

INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG002', TO_DATE('2025-12-31', 'YYYY-MM-DD'), 'Amiante', 'BIEN002'); -- Date corrigée

INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG003', TO_DATE('2026-03-15', 'YYYY-MM-DD'), 'Gaz', 'BIEN003');

-- ---------------------- ASSURANCE ----------------------
INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Logement)
VALUES ('POL001', 300, TO_DATE('2024-01-01', 'YYYY-MM-DD'), '12345678901234', 'LOG001');

INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Logement)
VALUES ('POL002', 450, TO_DATE('2024-06-15', 'YYYY-MM-DD'), '98765432109876', 'LOG002');

INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Logement)
VALUES ('POL003', 500, TO_DATE('2024-09-30', 'YYYY-MM-DD'), '45678912345678', 'LOG003');
