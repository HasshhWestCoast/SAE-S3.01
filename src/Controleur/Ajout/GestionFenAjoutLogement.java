package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Modele.Bien;
import Modele.Logement;
import Modele.Quotite;
import Modele.Quotter;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Modele.Dao.DaoLogement;
import Modele.Dao.DaoQuotite;
import Modele.Dao.DaoQuotter;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutLogement;

/**
 * Contrôleur pour gérer les actions liées à l'ajout d'un logement.
 */
public class GestionFenAjoutLogement implements ActionListener, ListSelectionListener {

    private FenAjoutLogement fenAjoutLogement; 
    private Bien bien; 

    /**
     * Constructeur de la classe.
     *
     * @param fenAjoutLogement Fenêtre associée à l'ajout d'un logement.
     */
    public GestionFenAjoutLogement(FenAjoutLogement fenAjoutLogement) {
        this.fenAjoutLogement = fenAjoutLogement;
        this.bien = null;
    }

    /**
     * Gère les actions déclenchées par les boutons de l'interface utilisateur.
     *
     * @param e L'événement associé à l'action utilisateur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText(); // Récupération du texte du bouton cliqué

        FenAccueil fenAC = (FenAccueil) this.fenAjoutLogement.getTopLevelAncestor();
        DefaultTableModel modeleTableBien = (DefaultTableModel) this.fenAjoutLogement.getTabMesBiens().getModel();

        if (texte != null) {
            switch (texte) {
                case "Annuler":
                    // Action pour fermer la fenêtre d'ajout
                    System.out.println("Vous FERMEZ la page ajout logement");
                    this.fenAjoutLogement.dispose();
                    break;

                case "Charger":
                    // Charger les biens disponibles
                    System.out.println("Vous CHARGER les Biens depuis Logement !");
                    try {
                        DaoBien daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
                        List<Bien> mesDonnees = daoBien.findAll();

                        Iterateur<Bien> itB = DaoBien.getIterateurBien();
                        if (itB == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }
                        modeleTableBien.setRowCount(mesDonnees.size());

                        int countL = 0;
                        while (itB.hasNext() && countL < mesDonnees.size()) {
                            Bien bien = itB.next();
                            this.ecrireLigneTableBien(bien, countL);
                            countL++;
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                case "Ajouter":
                    // Ajouter un logement
                    System.out.println("Vous AJOUTER un logement !");
                    // Vérification des champs requis
                    if (this.fenAjoutLogement.getIdLogement().isEmpty() ||
                        this.fenAjoutLogement.getDateAcquisition().isEmpty() ||
                        this.fenAjoutLogement.getSurfaceHabitable().isEmpty() ||
                        this.fenAjoutLogement.getPourcentage().isEmpty() ||
                        this.fenAjoutLogement.getTypeQuotite().isEmpty()) {
                        JOptionPane.showMessageDialog(
                            this.fenAjoutLogement,
                            "Veuillez remplir tous les champs requis !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    // Vérification qu'un bien est sélectionné
                    if (this.bien == null) {
                        JOptionPane.showMessageDialog(
                            this.fenAjoutLogement,
                            "Veuillez sélectionner un bien !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    try {
                        // Récupération des données saisies
                        DefaultTableModel modeleTable = (DefaultTableModel) fenAC.getTabMesLogements().getModel();
                        String IdLogement = (String) fenAjoutLogement.getIdLogement();
                        String SurfaceHabitableString = (String) fenAjoutLogement.getSurfaceHabitable();
                        double SurfaceHabitable = Double.parseDouble(SurfaceHabitableString);
                        String DateAcquisition = (String) fenAjoutLogement.getDateAcquisition();
                        String TypeDeLogement = (String) fenAjoutLogement.getComboBoxTypeDeLogement();
                        String NbPieceString = (String) fenAjoutLogement.getNbPiece();
                        int NbPiece = Integer.parseInt(NbPieceString);
                        String NumEtageString = (String) fenAjoutLogement.getNumEtage();
                        int NumEtage = Integer.parseInt(NumEtageString);
                        boolean garageBoolean = (Boolean) fenAjoutLogement.getcheckGarage();
                        int garage = garageBoolean ? 1 : 0;

                        // Insertion du logement
                        DaoLogement daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
                        Logement logement = new Logement(IdLogement, SurfaceHabitable, DateAcquisition, TypeDeLogement, NbPiece, NumEtage, garage, bien);
                        daoLogement.create(logement);

                        // Ajout du logement dans le tableau
                        String[] EngrLogement = {IdLogement, SurfaceHabitableString, DateAcquisition, TypeDeLogement, NbPieceString, NumEtageString};
                        modeleTable.addRow(EngrLogement);

                        // Gestion des quotités
                        String pourcentageString = (String) fenAjoutLogement.getPourcentage();
                        String typeQuotite = (String) fenAjoutLogement.getTypeQuotite();

                        DaoQuotite daoQuotite = new DaoQuotite(CictOracleDataSource.getInstance().getConnection());
                        Quotite quotite = new Quotite(typeQuotite);
                        daoQuotite.create(quotite);

                        DaoQuotter daoQuotter = new DaoQuotter(CictOracleDataSource.getInstance().getConnection());
                        Quotter quotter = new Quotter(Integer.parseInt(pourcentageString), logement, quotite);
                        daoQuotter.create(quotter);

                        // Fermeture de la fenêtre après ajout
                        fenAjoutLogement.dispose();
                    } catch (Exception ex) {
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
     * Gère les changements de sélection dans la table des biens.
     *
     * @param e L'événement de changement de sélection.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable tabBiens = this.fenAjoutLogement.getTabMesBiens();
        int selectedRow = tabBiens.getSelectedRow();
        if (selectedRow > -1) {
            try {
                DaoBien daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
                this.bien = daoBien.findById(tabBiens.getValueAt(selectedRow, 0).toString());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Insère les données d'un bien dans une ligne de la table des biens.
     *
     * @param bien Bien à insérer.
     * @param numeroLigne Numéro de ligne où insérer les données.
     */
    public void ecrireLigneTableBien(Bien bien, int numeroLigne) {
        DefaultTableModel modeleTableBiens = (DefaultTableModel) this.fenAjoutLogement.getTabMesBiens().getModel();
        modeleTableBiens.setValueAt(bien.getIdBien(), numeroLigne, 0);
        modeleTableBiens.setValueAt(bien.getTypeBien(), numeroLigne, 1);
    }
}
