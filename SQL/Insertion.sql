-- ---------------------- IMMEUBLE ----------------------
INSERT INTO SAE_Immeuble (Id_Immeuble, adresse, codepostal, ville, periode_construction, type_immeuble)
VALUES ('IMM001', '12 Rue Lafayette', '31000', 'Toulouse', '1980-1990', 'Maison');

INSERT INTO SAE_Immeuble (Id_Immeuble, adresse, codepostal, ville, periode_construction, type_immeuble)
VALUES ('IMM002', '45 Avenue des Minimes', '31200', 'Toulouse', '1990-2000', 'Immeuble');

INSERT INTO SAE_Immeuble (Id_Immeuble, adresse, codepostal, ville, periode_construction, type_immeuble)
VALUES ('IMM003', '8 Place du Capitole', '31000', 'Toulouse', 'Avant 1900', 'Maison');

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
INSERT INTO SAE_Bien (Id_Bien, surface_habitable, nb_pieces, num_etage, date_acquisition, type_bien, Id_Immeuble)
VALUES ('BIEN001', 80, 3, 2, TO_DATE('2015-06-15', 'YYYY-MM-DD'), 'Appartement', 'IMM001');

INSERT INTO SAE_Bien (Id_Bien, surface_habitable, nb_pieces, num_etage, date_acquisition, type_bien, Id_Immeuble)
VALUES ('BIEN002', 150, 5, 1, TO_DATE('2010-04-10', 'YYYY-MM-DD'), 'Maison', 'IMM002');

INSERT INTO SAE_Bien (Id_Bien, surface_habitable, nb_pieces, num_etage, date_acquisition, type_bien, Id_Immeuble)
VALUES ('BIEN003', 60, 1,0, TO_DATE('2020-08-20', 'YYYY-MM-DD'), 'garage', 'IMM003');

-- ---------------------- DIAGNOSTIC ----------------------
INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG001', TO_DATE('2025-06-30', 'YYYY-MM-DD'), 'Énergie', 'BIEN001');

INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG002', TO_DATE('2024-12-31', 'YYYY-MM-DD'), 'Amiante', 'BIEN002');

INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG003', TO_DATE('2026-03-15', 'YYYY-MM-DD'), 'Gaz', 'BIEN003');

-- ---------------------- ASSURANCE ----------------------
INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Immeuble)
VALUES ('POL001', 300, TO_DATE('2024-01-01', 'YYYY-MM-DD'), '12345678901234', 'IMM001');

INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Immeuble)
VALUES ('POL002', 450, TO_DATE('2024-06-15', 'YYYY-MM-DD'), '98765432109876', 'IMM002');

INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Immeuble)
VALUES ('POL003', 500, TO_DATE('2024-09-30', 'YYYY-MM-DD'), '45678912345678', 'IMM003');

-- ---------------------- FACTURE ----------------------

INSERT INTO SAE_Facture (
    Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, 
    designation, montant_reel_verse, montant, imputable_locataire, 
    acompte_verse, Id_Immeuble, Id_Bien, SIRET
) 
VALUES (
    'FACT001', TO_DATE('2023-10-15', 'YYYY-MM-DD'), TO_DATE('2023-10-20', 'YYYY-MM-DD'), 
    'Virement', 'DEV001', 'Facture de location annuelle', 1000, 1200, 1, 300, 
    'IMM001', 'BIEN001', '12345678901234'
);

INSERT INTO SAE_Facture (
    Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, 
    designation, montant_reel_verse, montant, imputable_locataire, 
    acompte_verse, Id_Immeuble, Id_Bien, SIRET
) 
VALUES (
    'FACT002', TO_DATE('2023-11-01', 'YYYY-MM-DD'), TO_DATE('2023-11-05', 'YYYY-MM-DD'), 
    'Chèque', 'DEV002', 'Facture pour travaux de rénovation', 500, 800, 0, 200, 
    'IMM002', 'BIEN002', '98765432109876'
);

INSERT INTO SAE_Facture (
    Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, 
    designation, montant_reel_verse, montant, imputable_locataire, 
    acompte_verse, Id_Immeuble, Id_Bien, SIRET
) 
VALUES (
    'FACT003', TO_DATE('2023-12-15', 'YYYY-MM-DD'), TO_DATE('2023-12-20', 'YYYY-MM-DD'), 
    'Espèces', 'DEV003', 'Facture de charges mensuelles', 200, 400, 1, 100, 
    'IMM003', 'BIEN003', '45678912345678'
);

-- ---------------------- LOUER ----------------------
INSERT INTO SAE_Louer (
    Id_Bien, Id_Locataire, Date_debut, nb_mois, loyer_payer, provisions_chargesMois_TTC, 
    caution_TTC, bail, etat_lieux, montant_reel_payer, annee, trimestre
) 
VALUES (
    'BIEN001', 'LOC001', TO_DATE('2023-01-01', 'YYYY-MM-DD'), 12, 1200, 150, 
    1000, 'Bail de location annuelle', 'Bon état', 1200, '2023', '1'
);

INSERT INTO SAE_Louer (
    Id_Bien, Id_Locataire, Date_debut, nb_mois, loyer_payer, provisions_chargesMois_TTC, 
    caution_TTC, bail, etat_lieux, montant_reel_payer, annee, trimestre
) 
VALUES (
    'BIEN002', 'LOC002', TO_DATE('2023-06-01', 'YYYY-MM-DD'), 6, 600, 75, 
    500, 'Bail de location pour 6 mois', 'Très bon état', 600, '2023', '2'
);

INSERT INTO SAE_Louer (
    Id_Bien, Id_Locataire, Date_debut, nb_mois, loyer_payer, provisions_chargesMois_TTC, 
    caution_TTC, bail, etat_lieux, montant_reel_payer, annee, trimestre
) 
VALUES (
    'BIEN003', 'LOC003', TO_DATE('2023-09-01', 'YYYY-MM-DD'), 12, 800, 100, 
    700, 'Bail de location annuelle', 'Bon état', 800, '2023', '3'
);

-- ---------------------- CHARGE ----------------------
-- Insertion dans la table SAE_Charge
INSERT INTO SAE_Charge (
    Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien
) 
VALUES (
    'CHARGE001', 'eau', 150, 200, 1, TO_DATE('2023-10-01', 'YYYY-MM-DD'), 'BIEN001'
);

INSERT INTO SAE_Charge (
    Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien
) 
VALUES (
    'CHARGE002', 'Elect', 300, 350, 0, TO_DATE('2023-10-10', 'YYYY-MM-DD'), 'BIEN002'
);

INSERT INTO SAE_Charge (
    Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien
) 
VALUES (
    'CHARGE003', 'entretien partie commune', 200, 250, 1, TO_DATE('2023-11-01', 'YYYY-MM-DD'), 'BIEN001'
);


-- ---------------------- COMPTEUR ----------------------
INSERT INTO SAE_Compteur (
    Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Immeuble, Id_Bien
) 
VALUES (
    'COMP001', 'Eau', 100, TO_DATE('2023-10-01', 'YYYY-MM-DD'), 'IMM001', 'BIEN001'
);

INSERT INTO SAE_Compteur (
    Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Immeuble, Id_Bien
) 
VALUES (
    'COMP002', 'Gaz', 50, TO_DATE('2023-10-15', 'YYYY-MM-DD'), 'IMM002', 'BIEN002'
);

INSERT INTO SAE_Compteur (
    Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Immeuble, Id_Bien
) 
VALUES (
    'COMP003', 'Electricité', 150, TO_DATE('2023-11-01', 'YYYY-MM-DD'), 'IMM001', 'BIEN002'
);

-- ---------------------- IMPOSER ----------------------
INSERT INTO SAE_Imposer (Id_Bien, Id_Impot)
VALUES ('BIEN001', 'IMP001');

INSERT INTO SAE_Imposer (Id_Bien, Id_Impot)
VALUES ('BIEN002', 'IMP002');

INSERT INTO SAE_Imposer (Id_Bien, Id_Impot)
VALUES ('BIEN003', 'IMP003');

-- ---------------------- RETIENT ----------------------
INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN001', 'LOC001', TO_DATE('2023-01-01', 'YYYY-MM-DD'), 'CHARGE001');

INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN002', 'LOC002', TO_DATE('2023-06-01', 'YYYY-MM-DD'), 'CHARGE002');

INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN003', 'LOC003', TO_DATE('2023-10-01', 'YYYY-MM-DD'), 'CHARGE003');
