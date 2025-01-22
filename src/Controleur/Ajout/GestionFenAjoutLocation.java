package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Modele.Bien;
import Modele.ICC;
import Modele.Locataire;
import Modele.Louer;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Modele.Dao.DaoICC;
import Modele.Dao.DaoLocataire;
import Modele.Dao.DaoLouer;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutICC;
import Vue.Insertion.FenAjoutLocataire;
import Vue.Insertion.FenAjoutLocation;

import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * Contrôleur pour gérer les actions et événements liés à l'ajout d'une location.
 */
public class GestionFenAjoutLocation implements ActionListener, ListSelectionListener {

    private FenAjoutLocation fenAjoutLoc; 
    private DaoLocataire daoLocataire; 
    private DaoBien daoBien; 
    private DaoICC daoICC; 

    private Locataire locataire; 
    private Bien bien; 
    private ICC icc; 

    /**
     * Constructeur de la classe.
     *
     * @param fenAjoutLoc Fenêtre associée à l'ajout d'une location.
     * @throws SQLException En cas de problème de connexion avec la base de données.
     */
    public GestionFenAjoutLocation(FenAjoutLocation fenAjoutLoc) throws SQLException {
        this.fenAjoutLoc = fenAjoutLoc;
        this.daoLocataire = new DaoLocataire(CictOracleDataSource.getInstance().getConnection());
        this.daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
        this.daoICC = new DaoICC(CictOracleDataSource.getInstance().getConnection());
        this.locataire = null;
        this.bien = null;
        this.icc = null;
    }

    /**
     * Gère les actions déclenchées par les boutons de l'interface utilisateur.
     *
     * @param e L'événement associé à l'action utilisateur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText(); // Récupère le texte du bouton cliqué

        FenAccueil fenAC = (FenAccueil) this.fenAjoutLoc.getTopLevelAncestor();
        DefaultTableModel modeleTableLocataire = (DefaultTableModel) this.fenAjoutLoc.getTabMesLocataires().getModel();
        DefaultTableModel modeleTableBien = (DefaultTableModel) this.fenAjoutLoc.getTabMesBiens().getModel();
        DefaultTableModel modeleTableICC = (DefaultTableModel) this.fenAjoutLoc.getTabMesICC().getModel();

        if (texte != null) {
            switch (texte) {
                case "Annuler":
                    // Action pour fermer la fenêtre
                    System.out.println("Vous FERMEZ la page ajout Location !");
                    this.fenAjoutLoc.dispose();
                    break;

                case "Charger":
                    // Charger les données des biens, locataires et ICC
                    System.out.println("Vous CHARGEZ BIENS depuis Location !");
                    try {
                        // Charger les biens
                        List<Bien> mesDonneesBien = this.daoBien.findAll();
                        Iterateur<Bien> itBien = DaoBien.getIterateurBien();
                        if (itBien != null) {
                            modeleTableBien.setRowCount(mesDonneesBien.size());
                            int countBien = 0;
                            while (itBien.hasNext() && countBien < mesDonneesBien.size()) {
                                Bien bien = itBien.next();
                                this.ecrireLigneTableBien(bien, countBien);
                                countBien++;
                            }
                        }

                        // Charger les ICC
                        List<ICC> mesDonneesICC = this.daoICC.findAll();
                        Iterateur<ICC> itICC = DaoICC.getIterateurICC();
                        if (itICC != null) {
                            modeleTableICC.setRowCount(mesDonneesICC.size());
                            int countICC = 0;
                            while (itICC.hasNext() && countICC < mesDonneesICC.size()) {
                                ICC icc = itICC.next();
                                this.ecrireLigneTableICC(icc, countICC);
                                countICC++;
                            }
                        }

                        // Charger les locataires
                        List<Locataire> mesDonneesLoc = this.daoLocataire.findAll();
                        Iterateur<Locataire> itLoc = DaoLocataire.getIterateurLocataire();
                        if (itLoc != null) {
                            modeleTableLocataire.setRowCount(mesDonneesLoc.size());
                            int countLoc = 0;
                            while (itLoc.hasNext() && countLoc < mesDonneesLoc.size()) {
                                Locataire locataire = itLoc.next();
                                this.ecrireLigneTableLoc(locataire, countLoc);
                                countLoc++;
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                case "Inserer L":
                    // Ajouter un locataire depuis la fenêtre
                    System.out.println("Vous INSERER un LOCATAIRE depuis Location !");
                    FenAjoutLocataire fenAjoutLocataire = new FenAjoutLocataire(fenAjoutLoc);
                    fenAC.getLayeredPane().add(fenAjoutLocataire);
                    fenAjoutLocataire.setVisible(true);
                    fenAjoutLocataire.moveToFront();
                    break;

                case "Inserer ICC":
                    // Ajouter un ICC depuis la fenêtre
                    System.out.println("Vous INSERER un ICC depuis Location !");
                    FenAjoutICC fenAddICC = new FenAjoutICC(fenAjoutLoc);
                    fenAC.getLayeredPane().add(fenAddICC);
                    fenAddICC.setVisible(true);
                    fenAddICC.moveToFront();
                    break;

                case "Supprimer L":
                    // Supprimer un locataire sélectionné
                    System.out.println("Vous SUPPRIMEZ un locataire !");
                    int ligneSelectionnee = this.fenAjoutLoc.getTabMesLocataires().getSelectedRow();
                    try {
                        String IdLocataire = (String) this.fenAjoutLoc.getTabMesLocataires().getValueAt(ligneSelectionnee, 0);
                        Locataire locataire = this.daoLocataire.findById(IdLocataire);
                        this.daoLocataire.delete(locataire);
                        modeleTableLocataire.removeRow(ligneSelectionnee);
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                case "Ajouter":
                    // Ajouter une location
                    System.out.println("Vous AJOUTER une Location !");
                    if (this.fenAjoutLoc.getTextFieldDateDebut().isEmpty() ||
                        this.fenAjoutLoc.getTextFieldNbMois().isEmpty() ||
                        this.fenAjoutLoc.getTextFieldProvisionsCharge().isEmpty() ||
                        this.fenAjoutLoc.getTextFieldMontantReel().isEmpty() ||
                        this.fenAjoutLoc.getTextFieldCaution().isEmpty()) {
                        JOptionPane.showMessageDialog(
                            this.fenAjoutLoc,
                            "Veuillez remplir tous les champs requis !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    if (this.bien == null || this.icc == null || this.locataire == null) {
                        JOptionPane.showMessageDialog(
                            this.fenAjoutLoc,
                            "Veuillez sélectionner au moins un champ par tableau !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    try {
                        // Récupération des données saisies et ajout de la location
                        DefaultTableModel modeleTable = (DefaultTableModel) fenAC.getTabMesLocations().getModel();
                        String DateDebut = this.fenAjoutLoc.getTextFieldDateDebut();
                        String NbMois = this.fenAjoutLoc.getTextFieldNbMois();
                        String ProvisionChargeString = this.fenAjoutLoc.getTextFieldProvisionsCharge();
                        double ProvisionCharge = Double.parseDouble(ProvisionChargeString);
                        String CautionString = this.fenAjoutLoc.getTextFieldCaution();
                        double Caution = Double.parseDouble(CautionString);

                        DaoLouer daoLouer = new DaoLouer(CictOracleDataSource.getInstance().getConnection());
                        Louer louer = new Louer(DateDebut, null, DateDebut, Integer.parseInt(NbMois), 1, 0.0, ProvisionCharge, Caution, "", "", 0.0, locataire, icc, bien);
                        daoLouer.create(louer);

                        String[] EngrLocation = {locataire.getNom(), bien.getIdBien(), bien.getTypeBien(), DateDebut};
                        modeleTable.addRow(EngrLocation);
                        fenAjoutLoc.dispose();

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                default:
                    // Action non reconnue
                    System.out.println("Action non reconnu !");
            }
        } else {
            System.out.println("Source non reconnu !");
        }
    }

    /**
     * Gère les changements de sélection dans les tableaux.
     *
     * @param e L'événement de changement de sélection.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            JTable selectedTable = this.fenAjoutLoc.getSelectedTable(e);
            int selectedRow = selectedTable.getSelectedRow();
            if (selectedRow > -1) {
                try {
                    if (selectedTable == this.fenAjoutLoc.getTabMesLocataires()) {
                        locataire = this.daoLocataire.findById(selectedTable.getValueAt(selectedRow, 0).toString());
                    } else if (selectedTable == this.fenAjoutLoc.getTabMesBiens()) {
                        bien = this.daoBien.findById(selectedTable.getValueAt(selectedRow, 0).toString());
                    } else if (selectedTable == this.fenAjoutLoc.getTabMesICC()) {
                        icc = this.daoICC.findById(selectedTable.getValueAt(selectedRow, 0).toString());
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * Insère les données d'un locataire dans une ligne de la table des locataires.
     *
     * @param locataire Locataire à insérer.
     * @param numeroLigne Numéro de ligne où insérer les données.
     */
    public void ecrireLigneTableLoc(Locataire locataire, int numeroLigne) {
        DefaultTableModel modeleTableLocataire = (DefaultTableModel) this.fenAjoutLoc.getTabMesLocataires().getModel();
        modeleTableLocataire.setValueAt(locataire.getIdLocataire(), numeroLigne, 0);
        modeleTableLocataire.setValueAt(locataire.getNom(), numeroLigne, 1);
    }

    /**
     * Insère les données d'un bien dans une ligne de la table des biens.
     *
     * @param bien Bien à insérer.
     * @param numeroLigne Numéro de ligne où insérer les données.
     */
    public void ecrireLigneTableBien(Bien bien, int numeroLigne) {
        DefaultTableModel modeleTableBien = (DefaultTableModel) this.fenAjoutLoc.getTabMesBiens().getModel();
        modeleTableBien.setValueAt(bien.getIdBien(), numeroLigne, 0);
        modeleTableBien.setValueAt(bien.getTypeBien(), numeroLigne, 1);
    }

    /**
     * Insère les données d'un ICC dans une ligne de la table des ICC.
     *
     * @param icc ICC à insérer.
     * @param numeroLigne Numéro de ligne où insérer les données.
     */
    public void ecrireLigneTableICC(ICC icc, int numeroLigne) {
        DefaultTableModel modeleTableICC = (DefaultTableModel) this.fenAjoutLoc.getTabMesICC().getModel();
        modeleTableICC.setValueAt(icc.getIcc(), numeroLigne, 0);
        modeleTableICC.setValueAt(icc.getAnnee(), numeroLigne, 1);
        modeleTableICC.setValueAt(icc.getTrimestre(), numeroLigne, 2);
        modeleTableICC.setValueAt(icc.getIndice(), numeroLigne, 3);
    }
}
