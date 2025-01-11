-- Insertion des données dans la table SAE_Bien
INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction)
VALUES ('BIEN001', '10 Rue du Soleil', 'Paris', 'Immeuble', '75001', '1990-2000');

INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction)
VALUES ('BIEN002', '15 Avenue des Lilas', 'Lyon', 'Maison', '69002', '2000-2010');

INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction)
VALUES ('BIEN003', '20 Boulevard des Roses', 'Toulouse', 'Garage', '31000', '1980-1990');

INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction)
VALUES ('BIEN004', '5 Place de l''Étoile', 'Marseille', 'Maison', '13000', '2010-2020');

INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction )
VALUES ('BIEN005', '8 Rue de la Paix', 'Lille', 'Garage', '59000', '1990-2000' );

INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction)
VALUES ('BIEN006', '12 Quai du Rhône', 'Strasbourg', 'Immeuble', '67000', '1980-1990' );

INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction)
VALUES ('BIEN007', '3 Allée des Pins', 'Bordeaux', 'Maison', '33000', '2000-2010');

INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction)
VALUES ('BIEN008', '7 Rue des Fleurs', 'Nantes', 'Immeuble', '44000', '2010-2020');

-- Insertion des donnÃ©es dans la table SAE_Logement
INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, TYPE_LOGEMENT, nb_pieces, num_etage, garage, Id_Bien)
VALUES ('LOG001', 100, TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'Appartement', 3, 1, 1, 'BIEN001');

INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, type_logement, nb_pieces, num_etage, garage, Id_Bien)
VALUES ('LOG002', 120, TO_DATE('2019-06-15', 'YYYY-MM-DD'), 'Maison', 4, 0, 1, 'BIEN002');

INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, type_logement, nb_pieces, num_etage, garage, Id_Bien)
VALUES ('LOG003', 80, TO_DATE('2018-03-10', 'YYYY-MM-DD'), 'Maison', 5, 0, 0, 'BIEN003');

INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, type_logement, nb_pieces, num_etage, garage, Id_Bien)
VALUES ('LOG004', 150, TO_DATE('2021-12-01', 'YYYY-MM-DD'), 'Maison', 6, 0, 1, 'BIEN004');

INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, type_logement, nb_pieces, num_etage, garage, Id_Bien)
VALUES ('LOG005', 200, TO_DATE('2022-05-20', 'YYYY-MM-DD'), 'Maison', 5, 0, 1, 'BIEN005');

INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, type_logement, nb_pieces, num_etage, garage, Id_Bien)
VALUES ('LOG006', 90, TO_DATE('2017-11-11', 'YYYY-MM-DD'), 'Appartement', 2, 1, 0, 'BIEN006');

INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, type_logement, nb_pieces, num_etage, garage, Id_Bien)
VALUES ('LOG007', 110, TO_DATE('2016-04-04', 'YYYY-MM-DD'), 'Maison', 4, 0, 1, 'BIEN007');

INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, type_logement, nb_pieces, num_etage, garage, Id_Bien)
VALUES ('LOG008', 70, TO_DATE('2015-08-15', 'YYYY-MM-DD'), 'Appartement', 2, 2, 0, 'BIEN008');


-- Insertion des donnÃ©es dans la table SAE_Entreprise
INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('12345678901234', 'Entreprise Alpha', '1 Rue du Siège', '75001', 'Paris', 'contact@alpha.fr', '0123456789', 'FR76300040000312345678901234');

INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('56789012345678', 'Entreprise Beta', '2 Avenue des Champs', '69002', 'Lyon', 'contact@beta.fr', '0987654321', 'FR76300040000356789012345678');

INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('98765432109876', 'Entreprise Gamma', '3 Boulevard du Centre', '31000', 'Toulouse', 'contact@gamma.fr', '0561123456', 'FR76300040000398765432109876');

INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('45678901234567', 'Entreprise Delta', '4 Place de l''étoile', '13000', 'Marseille', 'contact@delta.fr', '0412345678', 'FR76300040000345678901234567');

INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('11223344556677', 'Entreprise Epsilon', '5 Quai de la Seine', '59000', 'Lille', 'contact@epsilon.fr', '0320123456', 'FR76300040000311223344556677');

INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('88990011223344', 'Entreprise Zeta', '6 Chemin des Roses', '67000', 'Strasbourg', 'contact@zeta.fr', '0388123456', 'FR76300040000388990011223344');

INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('55667788990011', 'Entreprise Eta', '7 Rue des Lilas', '33000', 'Bordeaux', 'contact@eta.fr', '0558123456', 'FR76300040000355667788990011');

INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('66778899001122', 'Entreprise Theta', '8 Avenue des Fleurs', '44000', 'Nantes', 'contact@theta.fr', '0248123456', 'FR76300040000366778899001122');


-- Exemples d'insertion dans SAE_Facture avec des SIRET valides et des Id_Facture uniques
INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC001', TO_DATE('2023-01-15', 'YYYY-MM-DD'), TO_DATE('2023-01-30', 'YYYY-MM-DD'), 'Carte bancaire', 'DEV001', 'Location Appartement', 1000, 1200, 1, 300, 'LOG001', NULL, '12345678901234');

INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC002', TO_DATE('2023-02-05', 'YYYY-MM-DD'), TO_DATE('2023-02-10', 'YYYY-MM-DD'), 'Virement', 'DEV002', 'Réparation chaudière', 500, 600, 0, 100, NULL, 'BIEN002', '56789012345678');

INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC003', TO_DATE('2023-03-01', 'YYYY-MM-DD'), TO_DATE('2023-03-10', 'YYYY-MM-DD'), 'Chèque', 'DEV003', 'Entretien annuel', 1200, 1300, 1, 200, 'LOG003', 'BIEN003', '98765432109876');

INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC004', TO_DATE('2023-04-20', 'YYYY-MM-DD'), TO_DATE('2023-04-25', 'YYYY-MM-DD'), 'Espèces', 'DEV004', 'Réparation plomberie', 800, 950, 1, 150, 'LOG004', NULL, '45678901234567');

INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC005', TO_DATE('2023-05-10', 'YYYY-MM-DD'), TO_DATE('2023-05-15', 'YYYY-MM-DD'), 'Virement', 'DEV005', 'Service de nettoyage', 600, 700, 0, 100, NULL, 'BIEN004', '11223344556677');

INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC006', TO_DATE('2023-06-05', 'YYYY-MM-DD'), TO_DATE('2023-06-10', 'YYYY-MM-DD'), 'Carte bancaire', 'DEV006', 'Frais d''assurance', 450, 500, 1, 50, 'LOG005', 'BIEN005', '88990011223344');

INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC007', TO_DATE('2023-07-15', 'YYYY-MM-DD'), TO_DATE('2023-07-20', 'YYYY-MM-DD'), 'Chèque', 'DEV007', 'Entretien des espaces communs', 700, 800, 0, 100, 'LOG006', NULL, '55667788990011');

-- Insertion de donnÃ©es dans la table SAE_Impot
INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP001', 1200, '2023');

INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP002', 1500, '2023');

INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP003', 1800, '2024');

INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP004', 2200, '2024');

INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP005', 1000, '2023');

INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP006', 2500, '2023');

INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP007', 1300, '2023');

INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP008', 1700, '2023');

-- Insertion de donnÃ©es dans la table SAE_Locataire
INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, Collocataire)
VALUES ('LOC001', 'Dupont', 'Pierre', '0601020304', 'pierre.dupont@mail.com', TO_DATE('1990-05-12', 'YYYY-MM-DD'), 0);

INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, Collocataire)
VALUES ('LOC002', 'Martin', 'Sophie', '0604050607', 'sophie.martin@mail.com', TO_DATE('1985-07-25', 'YYYY-MM-DD'), 0);

INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, Collocataire)
VALUES ('LOC003', 'Lemoine', 'Julien', '0611223344', 'julien.lemoine@mail.com', TO_DATE('1988-11-18', 'YYYY-MM-DD'), 0);

INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, Collocataire)
VALUES ('LOC004', 'Bernard', 'Claire', '0622334455', 'claire.bernard@mail.com', TO_DATE('1993-02-10', 'YYYY-MM-DD'), 0);

INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, Collocataire)
VALUES ('LOC005', 'Robert', 'Lucie', '0633445566', 'lucie.robert@mail.com', TO_DATE('1992-09-30', 'YYYY-MM-DD'), 0);

INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, Collocataire)
VALUES ('LOC006', 'Durand', 'Marc', '0644556677', 'marc.durand@mail.com', TO_DATE('1984-04-14', 'YYYY-MM-DD'), 0);

INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, Collocataire)
VALUES ('LOC007', 'Leclerc', 'Amandine', '0655667788', 'amandine.leclerc@mail.com', TO_DATE('1995-01-22', 'YYYY-MM-DD'), 0);

INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, Collocataire)
VALUES ('LOC008', 'Petit', 'Alain', '0666778899', 'alain.petit@mail.com', TO_DATE('1997-03-05', 'YYYY-MM-DD'), 0);

-- Insertion des données dans la table SAE_Charge
INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH001', 'Charge d''eau', 120.00, 150.00, 1, TO_DATE('2023-01-01', 'YYYY-MM-DD'), 'BIEN001');

INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH002', 'Charge d''électricité', 200.00, 210.00, 0, TO_DATE('2023-02-15', 'YYYY-MM-DD'), 'BIEN002');

INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH003', 'Charge de chauffage', 180.00, 180.00, 1, TO_DATE('2023-03-10', 'YYYY-MM-DD'), 'BIEN003');

INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH004', 'Taxe d''ordures ménagères', 50.00, 60.00, 0, TO_DATE('2023-04-05', 'YYYY-MM-DD'), 'BIEN004');

INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH005', 'Maintenance ascenseur', 300.00, 320.00, 0, TO_DATE('2023-05-10', 'YYYY-MM-DD'), 'BIEN005');

INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH006', 'Entretien extérieur', 150.00, 180.00, 1, TO_DATE('2023-06-20', 'YYYY-MM-DD'), 'BIEN006');

INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH007', 'Frais de gestion', 100.00, 120.00, 0, TO_DATE('2023-07-15', 'YYYY-MM-DD'), 'BIEN007');

INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH008', 'Assurance immeuble', 250.00, 250.00, 1, TO_DATE('2023-08-01', 'YYYY-MM-DD'), 'BIEN008');

-- Insertion des données dans la table SAE_Diagnostic
INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG001', TO_DATE('2025-07-15', 'YYYY-MM-DD'), 'Électricité', 'BIEN001');

INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG002', TO_DATE('2025-02-15', 'YYYY-MM-DD'), 'Plomberie', 'BIEN002');

INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG003', TO_DATE('2025-03-10', 'YYYY-MM-DD'), 'Isolation', 'BIEN003');

INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG004', TO_DATE('2025-04-20', 'YYYY-MM-DD'), 'Chauffage', 'BIEN004');

INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG005', TO_DATE('2025-05-05', 'YYYY-MM-DD'), 'Électricité', 'BIEN005');

INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG006', TO_DATE('2025-06-01', 'YYYY-MM-DD'), 'Plomberie', 'BIEN006');

INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG007', TO_DATE('2025-07-15', 'YYYY-MM-DD'), 'Sécurité incendie', 'BIEN007');

INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG008', TO_DATE('2025-08-10', 'YYYY-MM-DD'), 'Électricité', 'BIEN008');

-- Insertion des donnÃ©es dans la table SAE_Assurance
INSERT INTO SAE_Assurance (numero_police, montant, Protection_juridique, date_echeance, SIRET, Id_Logement)
VALUES ('POL001', 500.00, 0.00, TO_DATE('2025-01-15', 'YYYY-MM-DD'), '12345678901234', 'LOG001');

INSERT INTO SAE_Assurance (numero_police, montant, Protection_juridique, date_echeance, SIRET, Id_Logement)
VALUES ('POL002', 750.50, 128.50, TO_DATE('2025-06-30', 'YYYY-MM-DD'), '56789012345678', 'LOG002');

INSERT INTO SAE_Assurance (numero_police, montant, Protection_juridique,  date_echeance, SIRET, Id_Logement)
VALUES ('POL003', 1200.00, 1200.70, TO_DATE('2026-03-20', 'YYYY-MM-DD'), '98765432109876', 'LOG003');

INSERT INTO SAE_Assurance (numero_police, montant, Protection_juridique, date_echeance, SIRET, Id_Logement)
VALUES ('POL004', 300.00, 300.00, TO_DATE('2025-11-10', 'YYYY-MM-DD'), '45678901234567', 'LOG004');

INSERT INTO SAE_Assurance (numero_police, montant, Protection_juridique, date_echeance, SIRET, Id_Logement)
VALUES ('POL005', 450.00, 300.00, TO_DATE('2025-07-05', 'YYYY-MM-DD'), '11223344556677', 'LOG005');

INSERT INTO SAE_Assurance (numero_police, montant, Protection_juridique, date_echeance, SIRET, Id_Logement)
VALUES ('POL006', 650.75, 300.00, TO_DATE('2025-12-01', 'YYYY-MM-DD'), '88990011223344', 'LOG006');

INSERT INTO SAE_Assurance (numero_police, montant, Protection_juridique, date_echeance, SIRET, Id_Logement)
VALUES ('POL007', 800.00, 300.00, TO_DATE('2025-09-15', 'YYYY-MM-DD'), '55667788990011', 'LOG007');

INSERT INTO SAE_Assurance (numero_police, montant, Protection_juridique, date_echeance, SIRET, Id_Logement)
VALUES ('POL008', 1000.00, 300.00, TO_DATE('2026-02-28', 'YYYY-MM-DD'), '66778899001122', 'LOG008');

-- Insertion des données dans la table SAE_Compteur
INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP001', 'Electricité', 150, TO_DATE('2023-01-01', 'YYYY-MM-DD'), 'LOG001', 'BIEN001');

INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP002', 'Eau', 120, TO_DATE('2023-02-01', 'YYYY-MM-DD'), 'LOG002', NULL);

INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP003', 'Chauffage', 300, TO_DATE('2023-03-01', 'YYYY-MM-DD'), NULL, 'BIEN003');

INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP004', 'Eau', 80, TO_DATE('2023-04-01', 'YYYY-MM-DD'), 'LOG004', NULL);

INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP005', 'Electricité', 200, TO_DATE('2023-05-01', 'YYYY-MM-DD'), NULL, 'BIEN005');

INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP006', 'Chauffage', 220, TO_DATE('2023-06-01', 'YYYY-MM-DD'), 'LOG006', NULL);

INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP007', 'Eau', 100, TO_DATE('2023-07-01', 'YYYY-MM-DD'), NULL, 'BIEN007');

INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP008', 'Electricité', 180, TO_DATE('2023-08-01', 'YYYY-MM-DD'), 'LOG008', NULL);

-- Insertion de donnÃ©es dans la table SAE_ICC
INSERT INTO SAE_ICC (ICC, annee, trimestre, indice)
VALUES (2130, '2023', 'Q1', 110.5);

INSERT INTO SAE_ICC (ICC, annee, trimestre, indice)
VALUES (3542, '2023', 'Q2', 112.3);

INSERT INTO SAE_ICC (ICC, annee, trimestre, indice)
VALUES (272, '2023', 'Q3', 113.7);

INSERT INTO SAE_ICC (ICC, annee, trimestre, indice)
VALUES (2724, '2023', 'Q4', 114.1);

INSERT INTO SAE_ICC (ICC, annee, trimestre, indice)
VALUES (242, '2024', 'Q1', 115.2);

INSERT INTO SAE_ICC (ICC, annee, trimestre, indice)
VALUES (273, '2024', 'Q2', 116.5);

INSERT INTO SAE_ICC (ICC, annee, trimestre, indice)
VALUES (741, '2024', 'Q3', 118.0);

INSERT INTO SAE_ICC (ICC, annee, trimestre, indice)
VALUES (376, '2024', 'Q4', 119.0);

-- Insertion de données dans la table SAE_Louer
INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, Date_Sortie, date_derniere_reg, nb_mois, loyer_mens_ttc, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, ICC)
VALUES ('BIEN001', 'LOC001', TO_DATE('2023-01-01', 'YYYY-MM-DD'), TO_DATE('2023-12-01', 'YYYY-MM-DD'), NULL, 12, 150, 500, 800, 'Bail de location appartement', 'Bon état', 1800, 2130);

INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, Date_Sortie, date_derniere_reg, nb_mois, loyer_mens_ttc, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, ICC)
VALUES ('BIEN002', 'LOC002', TO_DATE('2023-02-01', 'YYYY-MM-DD'), TO_DATE('2025-02-01', 'YYYY-MM-DD'), NULL, 24, 200, 600, 1000, 'Bail de location maison', 'Bon état', 4800, 2130);

INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, Date_Sortie, date_derniere_reg, nb_mois, loyer_mens_ttc, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, ICC)
VALUES ('BIEN003', 'LOC003', TO_DATE('2023-03-01', 'YYYY-MM-DD'), TO_DATE('2024-09-01', 'YYYY-MM-DD'), NULL, 18, 120, 400, 600, 'Bail de location garage', 'Bon état', 2160, 3542);

INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, Date_Sortie, date_derniere_reg, nb_mois, loyer_mens_ttc, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, ICC)
VALUES ('BIEN004', 'LOC004', TO_DATE('2023-04-01', 'YYYY-MM-DD'), TO_DATE('2024-04-01', 'YYYY-MM-DD'), NULL, 12, 180, 550, 1100, 'Bail de location maison', 'Bon état', 2160, 3542);

INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, Date_Sortie, date_derniere_reg, nb_mois, loyer_mens_ttc, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, ICC)
VALUES ('BIEN005', 'LOC005', TO_DATE('2023-05-01', 'YYYY-MM-DD'), TO_DATE('2023-11-01', 'YYYY-MM-DD'), NULL, 6, 130, 450, 750, 'Bail de location maison', 'Bon état', 780, 272);

INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, Date_Sortie, date_derniere_reg, nb_mois, loyer_mens_ttc, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, ICC)
VALUES ('BIEN006', 'LOC006', TO_DATE('2023-06-01', 'YYYY-MM-DD'), TO_DATE('2024-06-01', 'YYYY-MM-DD'), NULL, 12, 150, 500, 900, 'Bail de location appartement', 'Bon état', 1800, 272);

INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, Date_Sortie, date_derniere_reg, nb_mois, loyer_mens_ttc, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, ICC)
VALUES ('BIEN007', 'LOC007', TO_DATE('2023-07-01', 'YYYY-MM-DD'), TO_DATE('2025-07-01', 'YYYY-MM-DD'), NULL, 24, 160, 550, 950, 'Bail de location maison', 'Bon état', 3840, 2724);

INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, Date_Sortie, date_derniere_reg, nb_mois, loyer_mens_ttc, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, ICC)
VALUES ('BIEN008', 'LOC008', TO_DATE('2023-08-01', 'YYYY-MM-DD'), TO_DATE('2024-08-01', 'YYYY-MM-DD'), NULL, 12, 180, 600, 1100, 'Bail de location appartement', 'Bon état', 2160, 2724);

-- Insertion de donnÃ©es dans la table SAE_Imposer
INSERT INTO SAE_Imposer (Id_Bien, Id_Impot)
VALUES ('BIEN001', 'IMP001');

INSERT INTO SAE_Imposer (Id_Bien, Id_Impot)
VALUES ('BIEN002', 'IMP002');

INSERT INTO SAE_Imposer (Id_Bien, Id_Impot)
VALUES ('BIEN003', 'IMP003');

INSERT INTO SAE_Imposer (Id_Bien, Id_Impot)
VALUES ('BIEN004', 'IMP004');

INSERT INTO SAE_Imposer (Id_Bien, Id_Impot)
VALUES ('BIEN005', 'IMP005');

INSERT INTO SAE_Imposer (Id_Bien, Id_Impot)
VALUES ('BIEN006', 'IMP006');

INSERT INTO SAE_Imposer (Id_Bien, Id_Impot)
VALUES ('BIEN007', 'IMP007');

INSERT INTO SAE_Imposer (Id_Bien, Id_Impot)
VALUES ('BIEN008', 'IMP008');

-- Insertion de donnÃ©es dans la table SAE_Retient
INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN001', 'LOC001', TO_DATE('2023-01-01', 'YYYY-MM-DD'), 'CH001');

INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN002', 'LOC002', TO_DATE('2023-02-01', 'YYYY-MM-DD'), 'CH002');

INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN003', 'LOC003', TO_DATE('2023-03-01', 'YYYY-MM-DD'), 'CH003');

INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN004', 'LOC004', TO_DATE('2023-04-01', 'YYYY-MM-DD'), 'CH004');

INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN005', 'LOC005', TO_DATE('2023-05-01', 'YYYY-MM-DD'), 'CH005');

INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN006', 'LOC006', TO_DATE('2023-06-01', 'YYYY-MM-DD'), 'CH006');

INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN007', 'LOC007', TO_DATE('2023-07-01', 'YYYY-MM-DD'), 'CH007');

INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN008', 'LOC008', TO_DATE('2023-08-01', 'YYYY-MM-DD'), 'CH008');

-- Insertion de donnÃ©es dans la table SAE_Quotite

INSERT INTO SAE_Quotite (type_quotite) 
VALUES ('Énergie');

INSERT INTO SAE_Quotite (type_quotite) 
VALUES ('Eau');

INSERT INTO SAE_Quotite (type_quotite) 
VALUES ('Maintenance');

INSERT INTO SAE_Quotite (type_quotite) 
VALUES ('Assainissement');

INSERT INTO SAE_Quotite (type_quotite) 
VALUES ('Gardiennage');

INSERT INTO SAE_Quotite (type_quotite) 
VALUES ('Ascenseur');

INSERT INTO SAE_Quotite (type_quotite) 
VALUES ('Espaces verts');

INSERT INTO SAE_Quotite (type_quotite) 
VALUES ('Sécurité');

-- Insertion de donnÃ©es dans la table SAE_Quotter

INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN001', 'Énergie', 50);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN001', 'Eau', 25);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN001', 'Maintenance', 25);

INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN002', 'Énergie', 40);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN002', 'Eau', 30);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN002', 'Maintenance', 30);

INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN003', 'Énergie', 60);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN003', 'Eau', 20);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN003', 'Maintenance', 20);

INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN004', 'Énergie', 50);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN004', 'Eau', 20);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN004', 'Gardiennage', 30);

INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN005', 'Énergie', 70);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN005', 'Eau', 10);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN005', 'Assainissement', 20);

INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN006', 'Énergie', 50);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN006', 'Eau', 25);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN006', 'Sécurité', 25);

INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN007', 'Énergie', 60);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN007', 'Eau', 30);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN007', 'Espaces verts', 10);

INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN008', 'Énergie', 50);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN008', 'Eau', 30);
INSERT INTO SAE_Quotter (Id_Bien, type_quotite, pourcentage) VALUES ('BIEN008', 'Ascenseur', 20);
