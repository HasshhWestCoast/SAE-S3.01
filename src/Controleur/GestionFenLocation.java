package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Modele.Locataire;
import Modele.Louer;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoLocataire;
import Modele.Dao.DaoLouer;
import Modele.Dao.Iterateur;
import Modele.Dao.Requetes.Select.RequeteSelectDatePaiement;
import Modele.Dao.Requetes.sous_programme.SousProgrammeCalculDuLoyer;
import Vue.FenAccueil;
import Vue.FenInfosLocataire;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutLocation;

/**
 * Contrôleur pour la gestion de la fenêtre de location.
 * Implémente ActionListener pour gérer les actions utilisateur
 * et ListSelectionListener pour gérer les changements de sélection.
 */
public class GestionFenLocation implements ActionListener, ListSelectionListener {

    private FenAccueil fenAc; // Référence à la fenêtre d'accueil
    private DaoLouer daoLouer; // DAO pour la gestion des locations
    private Louer louer; // Instance représentant une location

    /**
     * Constructeur de la classe.
     *
     * @param fenAc Fenêtre d'accueil à laquelle ce contrôleur est associé.
     * @throws SQLException En cas d'erreur de connexion à la base de données.
     */
    public GestionFenLocation(FenAccueil fenAc) throws SQLException {
        this.louer = null;
        this.fenAc = fenAc;
        this.daoLouer = new DaoLouer(CictOracleDataSource.getInstance().getConnection());
    }

    /**
     * Gère les actions déclenchées par les boutons de l'interface utilisateur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText();
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLocations().getModel();

        if (texte != null) {
            switch (texte) {
                case "Charger":
                    // Charge les données de location dans le tableau
                    System.out.println("Vous Charger les données dans Location !");
                    try {
                        List<Louer> mesDonnees = this.daoLouer.findAll();
                        Iterateur<Louer> it = DaoLouer.getIterateurLouer();

                        if (it == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }
                        modeleTable.setRowCount(mesDonnees.size());

                        // Parcourt les données et les insère dans le tableau
                        int count = 0;
                        while (it.hasNext() && count < mesDonnees.size()) {
                            Louer louer = it.next();
                            this.ecrireLigneTable(louer, count);
                            count++;
                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                case "Supprimer":
                    // Supprime une location sélectionnée dans le tableau
                    System.out.println("Vous SUPPRIMER une donnée dans Location !");
                    int ligneSelectionnee = this.fenAc.getTabMesLocations().getSelectedRow();

                    try {
                        String IdLocataire = (String) this.fenAc.getTabMesLocations().getValueAt(ligneSelectionnee, 0);
                        String IdBien = (String) this.fenAc.getTabMesLocations().getValueAt(ligneSelectionnee, 1);
                        String DateDebut = (String) this.fenAc.getTabMesLocations().getValueAt(ligneSelectionnee, 3);

                        Louer louer = this.daoLouer.findById(IdBien, IdLocataire, DateDebut);
                        this.daoLouer.delete(louer);
                        modeleTable.removeRow(ligneSelectionnee);

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                case "Inserer":
                    // Ouvre la fenêtre d'insertion d'une nouvelle location
                    System.out.println("Vous INSERER une donnée dans Location !");
                    FenAjoutLocation fenAddLocation = null;

                    try {
                        fenAddLocation = new FenAjoutLocation();
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                        e1.printStackTrace();
                    }

                    int width = fenAc.getLayeredPane().getWidth();
                    int height = fenAc.getLayeredPane().getHeight();
                    fenAddLocation.setBounds(50, 10, width - 100, height - 15);

                    fenAc.getLayeredPane().add(fenAddLocation);
                    fenAddLocation.setVisible(true);
                    break;

                case "Informations locataire":
                    // Affiche les informations détaillées du locataire
                    System.out.println("Vous ouvrez la page informations locataire !");
                    FenInfosLocataire fenInfoLocataire = null;

                    if (this.louer == null) {
                        JOptionPane.showMessageDialog(
                                this.fenAc,
                                "Veuillez sélectionner un locataire !",
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    try {
                        fenInfoLocataire = new FenInfosLocataire();
                        // Récupère et affiche les informations du locataire dans la fenêtre
                        JTable tabLocations = this.fenAc.getTabMesLocations();
                        int selectedRow = tabLocations.getSelectedRow();

                        if (selectedRow > -1) {
                            String IdLocataire = (String) tabLocations.getValueAt(selectedRow, 0);
                            DaoLocataire daoLocataire = new DaoLocataire(CictOracleDataSource.getInstance().getConnection());
                            Locataire locataire = daoLocataire.findById(IdLocataire);

                            if (locataire != null) {
                                fenInfoLocataire.setTextFieldIdentifiant(locataire.getIdLocataire());
                                fenInfoLocataire.setTextFieldNom(locataire.getNom());
                                fenInfoLocataire.setTextFieldPrenom(locataire.getPrenom());
                                fenInfoLocataire.setTextFieldDateNaissance(locataire.getDateNaissance());
                                fenInfoLocataire.setTextFieldNumTelephone(locataire.getTelephone());
                                fenInfoLocataire.setTextFieldMail(locataire.getMail());
                            }
                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    this.fenAc.getLayeredPane().add(fenInfoLocataire);
                    fenInfoLocataire.setVisible(true);
                    break;

                default:
                    // Action non reconnue
                    System.out.println("Action non reconnue !");
            }
        } else {
            System.out.println("Source non reconnue !");
        }
    }

    // Ajoutez des commentaires pour chaque méthode si nécessaire.
}
