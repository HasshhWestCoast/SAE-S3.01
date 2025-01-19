package Controleur.Afficher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;

import Modele.Dao.CictOracleDataSource;
import Modele.Dao.Requetes.sous_programme.SousProgrammeCalculChargesReellesTotal;
import Modele.Dao.Requetes.sous_programme.SousProgrammeCalculDuLoyer;
import Modele.Dao.Requetes.sous_programme.SousProgrammeCalculTotalOrduresMenageres;
import Modele.Dao.Requetes.sous_programme.SousProgrammeCalculTotalProvisions;
import Modele.Dao.Requetes.sous_programme.SousProgrammeCalculTravauxImputableLoca;
import Modele.Dao.Requetes.sous_programme.SousProgrammeCalculerSoldeDeToutCompte;
import Modele.Dao.Requetes.sous_programme.SousProgrammeCalculerTotalChargesCompletes;
import Vue.FenInfosLocataire;
import Vue.FenAccueil;
import Vue.RoundedButton;

public class GestionFenLocataire implements ActionListener {

    private FenInfosLocataire fenLocataire;

    public GestionFenLocataire(FenInfosLocataire fenLocataire) throws SQLException {
        this.fenLocataire = fenLocataire;
    }
    
 

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText();

        if (texte != null) {
            switch (texte) {
                case "Annuler":
                    System.out.println("Vous FERMEZ la page Locataire !");
                    this.fenLocataire.dispose();
                    break;
                case "Modifier":
                    System.out.println("Vous modifiez les données d'un locataire !");
                    break;
                case "Régularisation des charges":
                    System.out.println("Mise à jour de la charge réelle...");
                    try {
                        // Récupérer l'identifiant du bien depuis tabRegCharges
                        JTable tabRegCharges = this.fenLocataire.getTabRegCharges();
                        String idBien = (String) tabRegCharges.getValueAt(0, 0); // Ligne 0, Colonne 0 : "Bien"

                        if (idBien != null && !idBien.isEmpty()) {
                            // Appeler le sous-programme pour calculer les charges réelles
                            SousProgrammeCalculChargesReellesTotal sousProgramme = new SousProgrammeCalculChargesReellesTotal();
                            double chargeReelle = appelSousProgrammeCalculChargesReelles(sousProgramme, idBien);
                            tabRegCharges.setValueAt(chargeReelle, 0, 3); 
                            

                            // Appeler le sous-programme pour calculer les ordures ménagères
                            SousProgrammeCalculTotalOrduresMenageres sousProgrammeOrdures = new SousProgrammeCalculTotalOrduresMenageres();
                            double orduresMenageres = appelSousProgrammeCalculOrduresMenageres(sousProgrammeOrdures, idBien);
                            tabRegCharges.setValueAt(orduresMenageres, 0, 4); // Ligne 0, Colonne 4 : "Ordures ménagères"
                            
                            // Appeler le sous-programme pour calculer le total des charges complètes
                            SousProgrammeCalculerTotalChargesCompletes sousProgrammeTotalCharges = new SousProgrammeCalculerTotalChargesCompletes();
                            double totalCharges = appelSousProgrammeCalculTotalCharges(sousProgrammeTotalCharges, idBien);
                            tabRegCharges.setValueAt(totalCharges, 0, 5); // Ligne 0, Colonne 5 : "Total charges"
                            
                            // Appeler le sous-programme pour calculer le montant restant du loyer
                            SousProgrammeCalculDuLoyer sousProgrammeLoyer = new SousProgrammeCalculDuLoyer();
                            double restantLoyer = appelSousProgrammeCalculDuLoyer(sousProgrammeLoyer, idBien);
                            tabRegCharges.setValueAt(restantLoyer, 0, 6); // Ligne 0, Colonne 6 : "Restant du loyer"
                            
                         // Appeler le sous-programme pour calculer le total des provisions
                            SousProgrammeCalculTotalProvisions sousProgrammeProvisions = new SousProgrammeCalculTotalProvisions();
                            double totalProvisions = appelSousProgrammeCalculTotalProvisions(sousProgrammeProvisions, idBien);
                            tabRegCharges.setValueAt(totalProvisions, 0, 7); //
                            
                             double reste = totalCharges + restantLoyer - totalProvisions;

                                // Mettre à jour la colonne "Reste"
                             tabRegCharges.setValueAt(reste, 0, 8); // Ligne 0, Colonne 8 : "Reste"

                            System.out.println("Colonne 'Charge réelle' mise à jour avec : " + chargeReelle);
                        } else {
                            System.out.println("Identifiant du bien non valide dans tabRegCharges !");
                        }
                    } catch (SQLException ex) {
                        System.out.println("Erreur lors de la mise à jour de la charge réelle : " + ex.getMessage());
                    }
                    break;
                case "Solde tout compte ":
                    System.out.println("Mise à jour des 'Provisions sur charges'...");

                    try {
                        // Récupérer le tableau "Solde tout compte"
                        JTable tabSoldeToutCompte = this.fenLocataire.gettabSoldeToutCompte();

                        // Récupérer les informations nécessaires
                        String idBien = (String) this.fenLocataire.getTabRegCharges().getValueAt(0, 0); // Colonne "Bien"

                        if (idBien != null && !idBien.isEmpty()) {
                        	// Appeler le sous-programme pour calculer le total des provisions
                        	SousProgrammeCalculTotalProvisions sousProgrammeProvisions = new SousProgrammeCalculTotalProvisions();
                        	double totalProvisions = appelSousProgrammeCalculTotalProvisions(sousProgrammeProvisions, idBien);
                        	
                        	// Mettre à jour la colonne "Provisions sur charge" dans le tableau
                        	tabSoldeToutCompte.setValueAt(totalProvisions, 0, 0); // Ligne 0, Colonne 0
                        	System.out.println("Colonne 'Provisions sur charges' mise à jour avec : " + totalProvisions);
                        	
                        	
                        	   // Appeler le sous-programme pour calculer les charges réelles
                            SousProgrammeCalculChargesReellesTotal sousProgramme = new SousProgrammeCalculChargesReellesTotal();
                            double chargeReelle = appelSousProgrammeCalculChargesReelles(sousProgramme, idBien);
                            tabSoldeToutCompte.setValueAt(chargeReelle, 0, 1); 
                        	

                        
                        	 // Récupérer la caution depuis la table SAE_Louer
                            double caution = recupererCautionDepuisLouer(idBien);

                            // Mettre à jour la colonne "Caution"
                            tabSoldeToutCompte.setValueAt(caution, 0, 2); // Ligne 0, Colonne 2
                            
                            // Appeler le sous-programme pour calculer les travaux imputables
                            SousProgrammeCalculTravauxImputableLoca sousProgrammeTravaux = new SousProgrammeCalculTravauxImputableLoca();
                            double travauxImputables = appelSousProgrammeCalculTravauxImputables(sousProgrammeTravaux, idBien);

                            // Mettre à jour la colonne "Travaux imputable"
                            tabSoldeToutCompte.setValueAt(travauxImputables, 0, 3); // Ligne 0, Colonne 3
                            System.out.println("Colonne 'Travaux imputable' mise à jour avec : " + travauxImputables);
                            
                            // Appeler le sous-programme pour calculer le restant du loyer
                            SousProgrammeCalculDuLoyer sousProgrammeLoyer = new SousProgrammeCalculDuLoyer();
                            double restantLoyer = appelSousProgrammeCalculDuLoyer(sousProgrammeLoyer, idBien);

                            // Mettre à jour la colonne "Restant du loyer"
                            tabSoldeToutCompte.setValueAt(restantLoyer, 0,4); // Ligne 0, Colonne 4
                            System.out.println("Colonne 'Restant du loyer' mise à jour avec : " + restantLoyer);
                            
                         // Appeler le sous-programme pour calculer le solde de tout compte
                            SousProgrammeCalculerSoldeDeToutCompte sousProgrammeSolde = new SousProgrammeCalculerSoldeDeToutCompte();
                            String idLocataire = (String) this.fenLocataire.getTextFieldIdentifiant();
                            String dateDebut = (String) this.fenLocataire.getTabRegCharges().getValueAt(0, 1);
                            String []id = { idBien,  idLocataire,  dateDebut };
                            double solde = appelSousProgrammeCalculerSoldeDeToutCompte(sousProgrammeSolde, id);

                            // Mettre à jour la colonne "Reste"
                            tabSoldeToutCompte.setValueAt(solde, 0, 5); // Ligne 0, Colonne Reste
                            System.out.println("Colonne 'Reste' mise à jour avec : " + solde);
                      
                            
                            
                        	
                        
                        } else {
                            System.out.println("ID du bien introuvable pour calculer les provisions sur charges.");
                        }
                    } catch (SQLException ex) {
                        System.out.println("Erreur lors de la mise à jour des 'Provisions sur charges' : " + ex.getMessage());
                    }
                    break;

                   

            }
        } else {
            System.out.println("Source non reconnue !");
        }
    }
    
    private double appelSousProgrammeCalculChargesReelles(SousProgrammeCalculChargesReellesTotal sousProgramme, String idBien) throws SQLException {
        double chargeReelle = 0;

        try (CallableStatement callableStatement = CictOracleDataSource.getInstance().getConnection().prepareCall(sousProgramme.appelSousProgramme())) {
            // Paramétrer le CallableStatement
            sousProgramme.parametresCalcul(callableStatement, idBien);

            // Exécuter l'appel
            callableStatement.execute();

            // Récupérer le résultat
            chargeReelle = callableStatement.getDouble(1); // Premier paramètre de sortie
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'appel au sous-programme : " + e.getMessage());
            throw e;
        }

        return chargeReelle;
    }
    
    private double appelSousProgrammeCalculOrduresMenageres(SousProgrammeCalculTotalOrduresMenageres sousProgramme, String idBien) throws SQLException {
        double orduresMenageres = 0;

        try (CallableStatement callableStatement = CictOracleDataSource.getInstance().getConnection().prepareCall(sousProgramme.appelSousProgramme())) {
            // Paramétrer le CallableStatement
            sousProgramme.parametresCalcul(callableStatement, idBien);

            // Exécuter l'appel
            callableStatement.execute();

            // Récupérer le résultat
            orduresMenageres = callableStatement.getDouble(1); // Premier paramètre de sortie
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'appel au sous-programme pour les ordures ménagères : " + e.getMessage());
            throw e;
        }

        return orduresMenageres;
    }
    
    private double appelSousProgrammeCalculTotalCharges(SousProgrammeCalculerTotalChargesCompletes sousProgramme, String idBien) throws SQLException {
        double totalCharges = 0;

        try (CallableStatement callableStatement = CictOracleDataSource.getInstance().getConnection().prepareCall(sousProgramme.appelSousProgramme())) {
            // Paramétrer le CallableStatement
            sousProgramme.parametresCalcul(callableStatement, idBien);

            // Exécuter l'appel
            callableStatement.execute();

            // Récupérer le résultat
            totalCharges = callableStatement.getDouble(1); // Premier paramètre de sortie
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'appel au sous-programme pour le total des charges : " + e.getMessage());
            throw e;
        }

        return totalCharges;
    }

    
    private double appelSousProgrammeCalculDuLoyer(SousProgrammeCalculDuLoyer sousProgramme, String idBien) throws SQLException {
        double restantLoyer = 0;

        try (CallableStatement callableStatement = CictOracleDataSource.getInstance().getConnection().prepareCall(sousProgramme.appelSousProgramme())) {
            // Paramétrer le CallableStatement
            sousProgramme.parametresCalcul(callableStatement, idBien);

            // Exécuter l'appel
            callableStatement.execute();

            // Récupérer le résultat
            restantLoyer = callableStatement.getDouble(1); // Premier paramètre de sortie
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'appel au sous-programme pour le restant du loyer : " + e.getMessage());
            throw e;
        }

        return restantLoyer;
    }

    private double appelSousProgrammeCalculTotalProvisions(SousProgrammeCalculTotalProvisions sousProgramme, String idBien) throws SQLException {
        double totalProvisions = 0;

        try (CallableStatement callableStatement = CictOracleDataSource.getInstance().getConnection().prepareCall(sousProgramme.appelSousProgramme())) {
            // Paramétrer le CallableStatement
            sousProgramme.parametresCalcul(callableStatement, idBien);

            // Exécuter l'appel
            callableStatement.execute();

            // Récupérer le résultat
            totalProvisions = callableStatement.getDouble(1); // Premier paramètre de sortie
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'appel au sous-programme pour les provisions : " + e.getMessage());
            throw e;
        }

        return totalProvisions;
    }
    
    private double recupererCautionDepuisLouer(String idBien) throws SQLException {
        double caution = 0;

        String query = "SELECT caution_TTC FROM SAE_Louer WHERE Id_Bien = ?";

        try (PreparedStatement preparedStatement = CictOracleDataSource.getInstance()
                .getConnection()
                .prepareStatement(query)) {

            // Paramétrer l'ID du bien
            preparedStatement.setString(1, idBien);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    caution = resultSet.getDouble("caution_TTC");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la caution : " + e.getMessage());
            throw e;
        }

        return caution;
    }
    
    private double appelSousProgrammeCalculTravauxImputables(
            SousProgrammeCalculTravauxImputableLoca sousProgramme, String idBien) throws SQLException {
        double travauxImputables = 0;

        try (CallableStatement callableStatement = CictOracleDataSource.getInstance()
                .getConnection()
                .prepareCall(sousProgramme.appelSousProgramme())) {

            // Paramétrer le CallableStatement
            sousProgramme.parametresCalcul(callableStatement, idBien);

            // Exécuter l'appel
            callableStatement.execute();

            // Récupérer le résultat
            travauxImputables = callableStatement.getDouble(1);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'appel au sous-programme : " + e.getMessage());
            throw e;
        }

        return travauxImputables;
    }
    
    private double appelSousProgrammeCalculerSoldeDeToutCompte(
            SousProgrammeCalculerSoldeDeToutCompte sousProgramme, String ... id) throws SQLException {
        double solde = 0;

        try (CallableStatement callableStatement = CictOracleDataSource.getInstance()
                .getConnection()
                .prepareCall(sousProgramme.appelSousProgramme())) {

            // Paramétrer le CallableStatement
            sousProgramme.parametresCalcul(callableStatement, id[0], id[1], id[2]);

            // Exécuter l'appel
            callableStatement.execute();

            // Récupérer le résultat
            solde = callableStatement.getDouble(1);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'appel au sous-programme : " + e.getMessage());
            throw e;
        }

        return solde;
    }
    


}
