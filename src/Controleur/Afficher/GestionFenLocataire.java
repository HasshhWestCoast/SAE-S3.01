package Controleur.Afficher;
import Modele.RegularisationCharge;
import Modele.SoldeToutCompte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
import rapport.CreerRapport;

/**
 * Classe contrôleur pour gérer les actions liées aux informations des locataires.
 */
public class GestionFenLocataire implements ActionListener {

    private FenInfosLocataire fenLocataire;

    /**
     * Constructeur pour initialiser le contrôleur avec la fenêtre des locataires.
     * @param fenLocataire Fenêtre d'informations des locataires.
     * @throws SQLException En cas de problème de connexion avec la base de données.
     */
    public GestionFenLocataire(FenInfosLocataire fenLocataire) throws SQLException {
        this.fenLocataire = fenLocataire;
    }
    
    
    /**
     * Gère les actions effectuées sur la fenêtre.
     * @param e L'événement associé à une action utilisateur.
     */
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
                case "Word Regularisation des charges":
                    try {
                        // Récupérer les données du tableau `tabRegCharges`
                        JTable tabRegCharges = this.fenLocataire.getTabRegCharges();
                        List<RegularisationCharge> regularisations = getRegularisations(tabRegCharges);

                        // Générer le fichier Word
                        CreerRapport.genererRapportRegularisationCharges(regularisations);

                        JOptionPane.showMessageDialog(
                            this.fenLocataire,
                            "Rapport de régularisation des charges généré avec succès dans src/rapport/regularisation_charges.docx",
                            "Succès",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                            this.fenLocataire,
                            "Erreur lors de la génération du rapport : " + ex.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        ex.printStackTrace();
                    }
                    break;

                case "Word solde tout compte":
                    try {
                        // Récupérer les données du tableau `tabSoldeToutCompte`
                        JTable tabSoldeToutCompte = this.fenLocataire.gettabSoldeToutCompte();
                        List<SoldeToutCompte> soldes = getSoldes(tabSoldeToutCompte);

                        // Générer le fichier Word
                        CreerRapport.genererRapportSoldeToutCompte(soldes);

                        JOptionPane.showMessageDialog(
                            this.fenLocataire,
                            "Rapport de solde tout compte généré avec succès dans src/rapport/solde_tout_compte.docx",
                            "Succès",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                            this.fenLocataire,
                            "Erreur lors de la génération du rapport : " + ex.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        ex.printStackTrace();
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
    
    private List<RegularisationCharge> getRegularisations(JTable tabRegCharges) {
        List<RegularisationCharge> regularisations = new ArrayList<>();

        DefaultTableModel model = (DefaultTableModel) tabRegCharges.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String bien = (String) model.getValueAt(i, 0); // Bien
            String periodeDu = (String) model.getValueAt(i, 1); // Période du
            String periodeAu = (String) model.getValueAt(i, 2); // Période au
            double chargeReelle = (double) model.getValueAt(i, 3); // Charge réelle
            double ordres = (double) model.getValueAt(i, 4); // Ordres
            double totalCharges = (double) model.getValueAt(i, 5); // Total charges
            double restantDues = (double) model.getValueAt(i, 6); // Restant dû
            double totalProvisions = (double) model.getValueAt(i, 7); // Total provisions
            double reste = (double) model.getValueAt(i, 8); // Reste

            regularisations.add(new RegularisationCharge(
                bien, periodeDu, periodeAu, chargeReelle, ordres, totalCharges, restantDues, totalProvisions, reste
            ));
        }

        return regularisations;
    }
    
    private List<SoldeToutCompte> getSoldes(JTable tabSoldeToutCompte) {
        List<SoldeToutCompte> soldes = new ArrayList<>();

        DefaultTableModel model = (DefaultTableModel) tabSoldeToutCompte.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            double provisionSurCharge = (double) model.getValueAt(i, 0); // Provision sur charge
            double chargeReelle = (double) model.getValueAt(i, 1); // Charge réelle
            double caution = (double) model.getValueAt(i, 2); // Caution
            double travauxImputables = (double) model.getValueAt(i, 3); // Travaux imputables
            double restantLoyer = (double) model.getValueAt(i, 4); // Restant du loyer
            double reste = (double) model.getValueAt(i, 5); // Reste

            soldes.add(new SoldeToutCompte(provisionSurCharge, chargeReelle, caution, travauxImputables, restantLoyer, reste));
        }

        return soldes;
    }


    


}
