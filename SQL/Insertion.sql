-- Données pour SAE_ICC
INSERT INTO SAE_ICC (annee, trimestre, indice) VALUES ('2023', 'Q1', 110.5);
INSERT INTO SAE_ICC (annee, trimestre, indice) VALUES ('2023', 'Q2', 112.0);
INSERT INTO SAE_ICC (annee, trimestre, indice) VALUES ('2023', 'Q3', 113.2);
INSERT INTO SAE_ICC (annee, trimestre, indice) VALUES ('2023', 'Q4', 114.1);
INSERT INTO SAE_ICC (annee, trimestre, indice) VALUES ('2024', 'Q1', 115.3);
INSERT INTO SAE_ICC (annee, trimestre, indice) VALUES ('2024', 'Q2', 116.0);
INSERT INTO SAE_ICC (annee, trimestre, indice) VALUES ('2024', 'Q3', 117.8);
INSERT INTO SAE_ICC (annee, trimestre, indice) VALUES ('2024', 'Q4', 118.4);
INSERT INTO SAE_ICC (annee, trimestre, indice) VALUES ('2022', 'Q2', 108.0);


-- Données pour SAE_Logement
INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, nb_pieces, num_etage, TYPE_IMMEUBLE)
VALUES ('LOG001', 120, TO_DATE('2020-01-01', 'YYYY-MM-DD'), 3, 1, 'Appartement');
INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, nb_pieces, num_etage, TYPE_IMMEUBLE)
VALUES ('LOG002', 150, TO_DATE('2021-05-15', 'YYYY-MM-DD'), 4, 0, 'Maison');
INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, nb_pieces, num_etage, TYPE_IMMEUBLE)
VALUES ('LOG003', 90, TO_DATE('2019-06-10', 'YYYY-MM-DD'), 2, 5, 'Studio');
INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, nb_pieces, num_etage, TYPE_IMMEUBLE)
VALUES ('LOG004', 200, TO_DATE('2022-03-20', 'YYYY-MM-DD'), 6, 0, 'Villa');
INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, nb_pieces, num_etage, TYPE_IMMEUBLE)
VALUES ('LOG005', 110, TO_DATE('2018-12-01', 'YYYY-MM-DD'), 3, 2, 'Appartement');
INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, nb_pieces, num_etage, TYPE_IMMEUBLE)
VALUES ('LOG006', 180, TO_DATE('2020-09-12', 'YYYY-MM-DD'), 5, 0, 'Maison');
INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, nb_pieces, num_etage, TYPE_IMMEUBLE)
VALUES ('LOG007', 75, TO_DATE('2023-02-14', 'YYYY-MM-DD'), 2, 3, 'Duplex');
INSERT INTO SAE_Logement (Id_Logement, surface_habitable, date_acquisition, nb_pieces, num_etage, TYPE_IMMEUBLE)
VALUES ('LOG008', 140, TO_DATE('2024-07-30', 'YYYY-MM-DD'), 4, 0, 'Loft');

-- Données pour SAE_Bien
INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction, Id_Logement)
VALUES ('BIEN001', '10 Rue du Soleil', 'Paris', 'Immeuble', '75001', '1990-2000', 'LOG001');
INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction, Id_Logement)
VALUES ('BIEN002', '15 Avenue des Lilas', 'Lyon', 'Maison', '69002', '2000-2010', 'LOG002');
INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction, Id_Logement)
VALUES ('BIEN003', '8 Place Verte', 'Marseille', 'Appartement', '13001', '1980-1990', 'LOG003');
INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction, Id_Logement)
VALUES ('BIEN004', '22 Boulevard Bleu', 'Bordeaux', 'Villa', '33000', '2010-2020', 'LOG004');
INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction, Id_Logement)
VALUES ('BIEN005', '30 Allée des Chênes', 'Nantes', 'Loft', '44000', '2005-2015', 'LOG005');
INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction, Id_Logement)
VALUES ('BIEN006', '5 Impasse Rouge', 'Toulouse', 'Duplex', '31000', '1995-2005', 'LOG006');
INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction, Id_Logement)
VALUES ('BIEN007', '12 Rue Jaune', 'Nice', 'Maison', '06000', '1970-1980', 'LOG007');
INSERT INTO SAE_Bien (Id_Bien, adresse, ville, type_bien, codepostal, periode_construction, Id_Logement)
VALUES ('BIEN008', '50 Route Blanche', 'Strasbourg', 'Studio', '67000', '2015-2025', 'LOG008');

-- Données pour SAE_Entreprise
INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('12345678901234', 'Entreprise Alpha', '1 Rue du Siège', '75001', 'Paris', 'contact@alpha.fr', '0123456789', 'FR76300040000312345678901234');
INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('23456789012345', 'Entreprise Beta', '2 Rue Centrale', '69002', 'Lyon', 'contact@beta.fr', '0234567890', 'FR76300040000323456789012345');
INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('34567890123456', 'Entreprise Gamma', '3 Rue Haute', '13001', 'Marseille', 'contact@gamma.fr', '0345678901', 'FR76300040000334567890123456');
INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('45678901234567', 'Entreprise Delta', '4 Rue Basse', '33000', 'Bordeaux', 'contact@delta.fr', '0456789012', 'FR76300040000345678901234567');
INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('56789012345678', 'Entreprise Epsilon', '5 Rue Large', '44000', 'Nantes', 'contact@epsilon.fr', '0567890123', 'FR76300040000356789012345678');
INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('67890123456789', 'Entreprise Zeta', '6 Rue Étroite', '31000', 'Toulouse', 'contact@zeta.fr', '0678901234', 'FR76300040000367890123456789');
INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('78901234567890', 'Entreprise Eta', '7 Rue Ouest', '06000', 'Nice', 'contact@eta.fr', '0789012345', 'FR76300040000378901234567890');
INSERT INTO SAE_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, IBAN)
VALUES ('89012345678901', 'Entreprise Theta', '8 Rue Est', '67000', 'Strasbourg', 'contact@theta.fr', '0890123456', 'FR76300040000389012345678901');


-- Données pour SAE_Assurance
INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Logement)
VALUES ('POL001', 500.00, TO_DATE('2025-01-15', 'YYYY-MM-DD'), '12345678901234', 'LOG001');
INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Logement)
VALUES ('POL002', 600.00, TO_DATE('2026-06-30', 'YYYY-MM-DD'), '23456789012345', 'LOG002');
INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Logement)
VALUES ('POL003', 450.00, TO_DATE('2025-12-20', 'YYYY-MM-DD'), '34567890123456', 'LOG003');
INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Logement)
VALUES ('POL004', 700.00, TO_DATE('2026-03-15', 'YYYY-MM-DD'), '45678901234567', 'LOG004');
INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Logement)
VALUES ('POL005', 550.00, TO_DATE('2027-08-10', 'YYYY-MM-DD'), '56789012345678', 'LOG005');
INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Logement)
VALUES ('POL006', 400.00, TO_DATE('2025-11-25', 'YYYY-MM-DD'), '67890123456789', 'LOG006');
INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Logement)
VALUES ('POL007', 800.00, TO_DATE('2026-10-05', 'YYYY-MM-DD'), '78901234567890', 'LOG007');
INSERT INTO SAE_Assurance (numero_police, montant, date_echeance, SIRET, Id_Logement)
VALUES ('POL008', 750.00, TO_DATE('2027-01-01', 'YYYY-MM-DD'), '89012345678901', 'LOG008');

-- Données pour SAE_Diagnostic
INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG001', TO_DATE('2025-12-01', 'YYYY-MM-DD'), 'Électricité', 'BIEN001');
INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG002', TO_DATE('2026-06-15', 'YYYY-MM-DD'), 'Gaz', 'BIEN002');
INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG003', TO_DATE('2025-09-30', 'YYYY-MM-DD'), 'Amiante', 'BIEN003');
INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG004', TO_DATE('2027-01-01', 'YYYY-MM-DD'), 'Plomb', 'BIEN004');
INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG005', TO_DATE('2025-05-20', 'YYYY-MM-DD'), 'Termites', 'BIEN005');
INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG006', TO_DATE('2026-11-11', 'YYYY-MM-DD'), 'Performance Énergétique', 'BIEN006');
INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG007', TO_DATE('2027-02-28', 'YYYY-MM-DD'), 'Accessibilité', 'BIEN007');
INSERT INTO SAE_Diagnostic (Id_Diagnostic, date_validite, type_diagnostic, Id_Bien)
VALUES ('DIAG008', TO_DATE('2026-04-15', 'YYYY-MM-DD'), 'Sécurité Incendie', 'BIEN008');

-- Données pour SAE_Facture
INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC001', TO_DATE('2023-01-15', 'YYYY-MM-DD'), TO_DATE('2023-01-30', 'YYYY-MM-DD'), 'Carte bancaire', 'DEV001', 'Location Appartement', 1000, 1200, 1, 300, 'LOG001', 'BIEN001', '12345678901234');
INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC002', TO_DATE('2024-02-20', 'YYYY-MM-DD'), TO_DATE('2024-02-25', 'YYYY-MM-DD'), 'Virement', 'DEV002', 'Location Maison', 1500, 1700, 1, 500, 'LOG002', 'BIEN002', '23456789012345');
INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC003', TO_DATE('2023-12-31', 'YYYY-MM-DD'), TO_DATE('2023-12-31', 'YYYY-MM-DD'), 'Chèque', 'DEV003', 'Réparation', 800, 900, 0, 200, 'LOG003', 'BIEN003', '34567890123456');
INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC004', TO_DATE('2023-04-15', 'YYYY-MM-DD'), TO_DATE('2023-04-30', 'YYYY-MM-DD'), 'Espèces', 'DEV004', 'Rénovation', 2000, 2500, 0, 500, 'LOG004', 'BIEN004', '45678901234567');
INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC005', TO_DATE('2022-05-01', 'YYYY-MM-DD'), TO_DATE('2022-05-15', 'YYYY-MM-DD'), 'Virement', 'DEV005', 'Entretien', 500, 550, 1, 100, 'LOG005', 'BIEN005', '56789012345678');
INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC006', TO_DATE('2023-12-31', 'YYYY-MM-DD'), TO_DATE('2023-12-31', 'YYYY-MM-DD'), 'Carte bancaire', 'DEV006', 'Assurance', 1200, 1300, 0, 400, 'LOG006', 'BIEN006', '67890123456789');
INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC007', TO_DATE('2024-07-15', 'YYYY-MM-DD'), TO_DATE('2024-07-25', 'YYYY-MM-DD'), 'Chèque', 'DEV007', 'Charges Locatives', 300, 400, 1, 100, 'LOG007', 'BIEN007', '78901234567890');
INSERT INTO SAE_Facture (Id_Facture, date_emission, date_paiement, mode_paiement, numero_devis, designation, montant_reel_verse, montant, imputable_locataire, acompte_verse, Id_Logement, Id_Bien, SIRET)
VALUES ('FAC008', TO_DATE('2023-12-31', 'YYYY-MM-DD'), TO_DATE('2023-12-31', 'YYYY-MM-DD'), 'Espèces', 'DEV008', 'Rénovation', 700, 800, 0, 200, 'LOG008', 'BIEN008', '89012345678901');

-- Données pour SAE_Charge
INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH001', 'Charge d''eau', 120.00, 150.00, 1, TO_DATE('2023-01-01', 'YYYY-MM-DD'), 'BIEN001');
INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH002', 'Electricité', 90.00, 100.00, 1, TO_DATE('2024-02-15', 'YYYY-MM-DD'), 'BIEN002');
INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH003', 'Gaz', 110.00, 120.00, 1, TO_DATE('2025-03-10', 'YYYY-MM-DD'), 'BIEN003');
INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH004', 'Entretien chaudière', 200.00, 250.00, 0, TO_DATE('2023-04-20', 'YYYY-MM-DD'), 'BIEN004');
INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH005', 'Taxes foncières', 300.00, 320.00, 1, TO_DATE('2022-05-05', 'YYYY-MM-DD'), 'BIEN005');
INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH006', 'Nettoyage parties communes', 150.00, 180.00, 1, TO_DATE('2026-06-15', 'YYYY-MM-DD'), 'BIEN006');
INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH007', 'Réparation toiture', 400.00, 450.00, 0, TO_DATE('2024-07-25', 'YYYY-MM-DD'), 'BIEN007');
INSERT INTO SAE_Charge (Id_Charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, Id_Bien)
VALUES ('CH008', 'Remplacement ascenseur', 1000.00, 1200.00, 0, TO_DATE('2025-08-30', 'YYYY-MM-DD'), 'BIEN008');


-- Données pour SAE_Compteur
INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP001', 'Electricité', 150, TO_DATE('2023-01-01', 'YYYY-MM-DD'), 'LOG001', 'BIEN001');
INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP002', 'Gaz', 200, TO_DATE('2024-02-15', 'YYYY-MM-DD'), 'LOG002', 'BIEN002');
INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP003', 'Eau', 300, TO_DATE('2025-03-10', 'YYYY-MM-DD'), 'LOG003', 'BIEN003');
INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP004', 'Electricité', 400, TO_DATE('2023-04-20', 'YYYY-MM-DD'), 'LOG004', 'BIEN004');
INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP005', 'Gaz', 250, TO_DATE('2022-05-05', 'YYYY-MM-DD'), 'LOG005', 'BIEN005');
INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP006', 'Eau', 350, TO_DATE('2026-06-15', 'YYYY-MM-DD'), 'LOG006', 'BIEN006');
INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP007', 'Electricité', 180, TO_DATE('2024-07-25', 'YYYY-MM-DD'), 'LOG007', 'BIEN007');
INSERT INTO SAE_Compteur (Id_Compteur, TypeComp, indexCompteur, date_releve, Id_Logement, Id_Bien)
VALUES ('COMP008', 'Gaz', 220, TO_DATE('2025-08-30', 'YYYY-MM-DD'), 'LOG008', 'BIEN008');

-- Données pour SAE_Locataire
INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, quotite)
VALUES ('LOC001', 'Dupont', 'Pierre', '0601020304', 'pierre.dupont@mail.com', TO_DATE('1990-05-12', 'YYYY-MM-DD'), 50);
INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, quotite)
VALUES ('LOC002', 'Martin', 'Sophie', '0612345678', 'sophie.martin@mail.com', TO_DATE('1985-03-25', 'YYYY-MM-DD'), 40);
INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, quotite)
VALUES ('LOC003', 'Bernard', 'Lucas', '0623456789', 'lucas.bernard@mail.com', TO_DATE('1992-07-15', 'YYYY-MM-DD'), 60);
INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, quotite)
VALUES ('LOC004', 'Morel', 'Chloé', '0634567890', 'chloe.morel@mail.com', TO_DATE('1995-12-10', 'YYYY-MM-DD'), 70);
INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, quotite)
VALUES ('LOC005', 'Dubois', 'Maxime', '0645678901', 'maxime.dubois@mail.com', TO_DATE('1988-09-18', 'YYYY-MM-DD'), 45);
INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, quotite)
VALUES ('LOC006', 'Durand', 'Camille', '0656789012', 'camille.durand@mail.com', TO_DATE('1991-11-05', 'YYYY-MM-DD'), 55);
INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, quotite)
VALUES ('LOC007', 'Petit', 'Emma', '0667890123', 'emma.petit@mail.com', TO_DATE('1993-02-22', 'YYYY-MM-DD'), 50);
INSERT INTO SAE_Locataire (Id_Locataire, nom, prenom, telephone, mail, date_naissance, quotite)
VALUES ('LOC008', 'Lemoine', 'Hugo', '0678901234', 'hugo.lemoine@mail.com', TO_DATE('1989-08-01', 'YYYY-MM-DD'), 65);


-- Données pour SAE_Louer
INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, nb_mois, loyer_payer, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, annee, trimestre)
VALUES ('BIEN001', 'LOC001', TO_DATE('2023-01-01', 'YYYY-MM-DD'), 12, 800, 150, 500, 'Bail de location appartement', 'Bon état', 800, '2023', 'Q1');
INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, nb_mois, loyer_payer, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, annee, trimestre)
VALUES ('BIEN002', 'LOC002', TO_DATE('2024-02-01', 'YYYY-MM-DD'), 6, 1000, 200, 600, 'Bail de location maison', 'Très bon état', 1000, '2024', 'Q1');
INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, nb_mois, loyer_payer, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, annee, trimestre)
VALUES ('BIEN003', 'LOC003', TO_DATE('2023-12-31', 'YYYY-MM-DD'), 18, 600, 100, 400, 'Bail de location studio', 'Moyen état', 600, '2023', 'Q4');
INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, nb_mois, loyer_payer, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, annee, trimestre)
VALUES ('BIEN004', 'LOC004', TO_DATE('2023-04-01', 'YYYY-MM-DD'), 24, 1200, 300, 700, 'Bail de location villa', 'Bon état', 1200, '2023', 'Q2');
INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, nb_mois, loyer_payer, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, annee, trimestre)
VALUES ('BIEN005', 'LOC005', TO_DATE('2022-05-01', 'YYYY-MM-DD'), 36, 900, 150, 500, 'Bail de location loft', 'Très bon état', 900, '2022', 'Q2');
INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, nb_mois, loyer_payer, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, annee, trimestre)
VALUES ('BIEN006', 'LOC006', TO_DATE('2023-12-31', 'YYYY-MM-DD'), 12, 750, 120, 400, 'Bail de location maison', 'Moyen état', 750, '2023', 'Q4');
INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, nb_mois, loyer_payer, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, annee, trimestre)
VALUES ('BIEN007', 'LOC007', TO_DATE('2024-07-01', 'YYYY-MM-DD'), 6, 850, 140, 450, 'Bail de location duplex', 'Bon état', 850, '2024', 'Q3');
INSERT INTO SAE_Louer (Id_Bien, Id_Locataire, Date_debut, nb_mois, loyer_payer, provisions_chargesMois_TTC, caution_TTC, bail, etat_lieux, montant_reel_payer, annee, trimestre)
VALUES ('BIEN008', 'LOC008', TO_DATE('2023-12-31', 'YYYY-MM-DD'), 18, 950, 160, 500, 'Bail de location studio', 'Très bon état', 950, '2023', 'Q4');


-- Données pour SAE_Impot
INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP001', 1200, '2023');
INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP002', 1500, '2024');
INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP003', 1000, '2025');
INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP004', 1300, '2023');
INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP005', 1100, '2024');
INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP006', 1400, '2025');
INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP007', 1250, '2023');
INSERT INTO SAE_Impot (Id_Impot, montant, annee)
VALUES ('IMP008', 1350, '2024');


-- Données pour SAE_Imposer
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


-- Données pour SAE_Retient
INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN001', 'LOC001', TO_DATE('2023-01-01', 'YYYY-MM-DD'), 'CH001');
INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN002', 'LOC002', TO_DATE('2024-02-01', 'YYYY-MM-DD'), 'CH002');
INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN003', 'LOC003', TO_DATE('2025-03-01', 'YYYY-MM-DD'), 'CH003');
INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN004', 'LOC004', TO_DATE('2023-04-01', 'YYYY-MM-DD'), 'CH004');
INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN005', 'LOC005', TO_DATE('2022-05-01', 'YYYY-MM-DD'), 'CH005');
INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN006', 'LOC006', TO_DATE('2026-06-01', 'YYYY-MM-DD'), 'CH006');
INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN007', 'LOC007', TO_DATE('2024-07-01', 'YYYY-MM-DD'), 'CH007');
INSERT INTO SAE_Retient (Id_Bien, Id_Locataire, Date_debut, Id_Charges)
VALUES ('BIEN008', 'LOC008', TO_DATE('2025-08-01', 'YYYY-MM-DD'), 'CH008');

