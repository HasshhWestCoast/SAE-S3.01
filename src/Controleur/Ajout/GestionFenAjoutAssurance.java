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
import Modele.Entreprise;
import Modele.Logement;
import Modele.assurance;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoAssurance;
import Modele.Dao.DaoBien;
import Modele.Dao.DaoEntreprise;
import Modele.Dao.DaoLogement;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutAssurance;
import Vue.Insertion.FenAjoutEntreprise;
import Vue.Insertion.FenAjoutLogement;

/**
 * Classe contrôleur pour gérer les actions et événements liés à l'ajout d'une assurance.
 */
public class GestionFenAjoutAssurance implements ActionListener, ListSelectionListener {

    private FenAjoutAssurance fenAjoutAssurance; 
    private DaoEntreprise daoEntreprise; 
    private DaoLogement daoLogement; 
    private DaoBien daoBien; 
    private Logement logement; 
    private Entreprise entreprise; 
    private Bien bien; 

    /**
     * Constructeur de la classe.
     *
     * @param fenAjoutAssurance Fenêtre liée à l'ajout d'assurance.
     * @throws SQLException En cas de problème de connexion à la base de données.
     */
    public GestionFenAjoutAssurance(FenAjoutAssurance fenAjoutAssurance) throws SQLException {
        this.fenAjoutAssurance = fenAjoutAssurance;
        this.daoEntreprise = new DaoEntreprise(CictOracleDataSource.getInstance().getConnection());
        this.daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
        this.daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
        this.logement = null;
        this.entreprise = null;
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
        String texte = ((RoundedButton) source).getText(); // Récupère le texte du bouton cliqué

        // Récupération des modèles de table associés à la fenêtre d'ajout d'assurance
        FenAccueil fenAC = (FenAccueil) this.fenAjoutAssurance.getTopLevelAncestor();
        DefaultTableModel modeleTableEntreprise = (DefaultTableModel) this.fenAjoutAssurance.getTabMesEntreprise().getModel();
        DefaultTableModel modeleTableLogement = (DefaultTableModel) this.fenAjoutAssurance.getTabMesLogements().getModel();
        DefaultTableModel modeleTableBien = (DefaultTableModel) this.fenAjoutAssurance.getTabMesBiens().getModel();

        if (texte != null) {
            switch (texte) {
                case "Annuler":
                    // Fermer la fenêtre
                    System.out.println("Vous FERMEZ la page ajout Assurance !");
                    this.fenAjoutAssurance.dispose();
                    break;

                case "Supprimer":
                    // Supprimer une entreprise sélectionnée dans la table
                    int ligneSelectionnee = this.fenAjoutAssurance.getTabMesEntreprise().getSelectedRow();
                    try {
                        String Siret = (String) this.fenAjoutAssurance.getTabMesEntreprise().getValueAt(ligneSelectionnee, 0);
                        Entreprise entreprise = this.daoEntreprise.findById(Siret);
                        this.daoEntreprise.delete(entreprise);
                        modeleTableEntreprise.removeRow(ligneSelectionnee);
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                case "Ajouter":
                    // Ajouter une nouvelle assurance
                    System.out.println("Vous AJOUTER une donnée à Assurance !");
                    if (this.fenAjoutAssurance.getNumeroPolice().isEmpty() ||
                        this.fenAjoutAssurance.getMontant().isEmpty() ||
                        this.fenAjoutAssurance.getDateEcheance().isEmpty()) {
                        JOptionPane.showMessageDialog(
                            this.fenAjoutAssurance,
                            "Veuillez remplir tous les champs requis !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    // Vérification des conditions
                    if (this.entreprise == null) {
                        JOptionPane.showMessageDialog(
                            this.fenAjoutAssurance,
                            "Veuillez sélectionner une entreprise !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    try {
                        DefaultTableModel modeleTable = (DefaultTableModel) fenAC.getTabMesAssurances().getModel();

                        // Récupération des données saisies par l'utilisateur
                        String NumeroPolice = (String) fenAjoutAssurance.getNumeroPolice();
                        String MontantString = (String) fenAjoutAssurance.getMontant();
                        Float Montant = Float.parseFloat(MontantString);
                        String DateEcheance = (String) fenAjoutAssurance.getDateEcheance();
                        String PorectionJuridiqueString = (String) fenAjoutAssurance.getMontant();
                        Double PorectionJuridique = Double.parseDouble(PorectionJuridiqueString);

                        // Création et insertion de l'assurance
                        DaoAssurance daoAssurance = new DaoAssurance(CictOracleDataSource.getInstance().getConnection());
                        assurance assu = new assurance(NumeroPolice, Montant, DateEcheance, PorectionJuridique, logement, entreprise, bien);
                        daoAssurance.create(assu);

                        // Ajout dans la table
                        String logementId = (logement != null) ? logement.getIdLogement() : "NA";
                        String bienId = (bien != null) ? bien.getIdBien() : "NA";
                        String[] EngrAssu = {NumeroPolice, MontantString, DateEcheance, entreprise.getSiret(), logementId, bienId};
                        modeleTable.addRow(EngrAssu);

                        // Fermeture de la fenêtre après ajout
                        fenAjoutAssurance.dispose();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                case "Inserer L":
                    // Ouvrir la fenêtre pour ajouter un logement
                    System.out.println("Vous AJOUTER un Logement depuis Assurance !");
                    FenAjoutLogement fenAddLog = new FenAjoutLogement();
                    fenAC.getLayeredPane().add(fenAddLog);
                    fenAddLog.setVisible(true);
                    fenAddLog.moveToFront();
                    break;

                case "Inserer E":
                    // Ouvrir la fenêtre pour ajouter une entreprise
                    System.out.println("Vous AJOUTER une Entreprise depuis Assurance !");
                    FenAjoutEntreprise fenAjoutEntreprise = new FenAjoutEntreprise(fenAjoutAssurance);
                    fenAC.getLayeredPane().add(fenAjoutEntreprise);
                    fenAjoutEntreprise.setVisible(true);
                    fenAjoutEntreprise.moveToFront();
                    break;

                case "Charger":
                    // Charger les données des logements, entreprises et biens
                    System.out.println("Vous CHARGER les Logements et Entreprise depuis Assurance !");
                    try {
                        // Charger les logements
                        List<Logement> mesDonnees = this.daoLogement.findAll();
                        Iterateur<Logement> itL = DaoLogement.getIterateurLogement();
                        if (itL == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }
                        modeleTableLogement.setRowCount(mesDonnees.size());
                        int countL = 0;
                        while (itL.hasNext() && countL < mesDonnees.size()) {
                            Logement logement = itL.next();
                            this.ecrireLigneTableLogement(logement, countL);
                            countL++;
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }

                    // Charger les entreprises
                    try {
                        List<Entreprise> mesDonnees = this.daoEntreprise.findAll();
                        Iterateur<Entreprise> it = DaoEntreprise.getIterateurEntreprise();
                        if (it == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }
                        modeleTableEntreprise.setRowCount(mesDonnees.size());
                        int count = 0;
                        while (it.hasNext() && count < mesDonnees.size()) {
                            Entreprise entreprise = it.next();
                            this.ecrireLigneTableEntreprise(entreprise, count);
                            count++;
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }

                    // Charger les biens
                    try {
                        List<Bien> mesBiens = this.daoBien.findAll();
                        Iterateur<Bien> itB = DaoBien.getIterateurBien();
                        if (itB == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }
                        modeleTableBien.setRowCount(mesBiens.size());
                        int count = 0;
                        while (itB.hasNext() && count < mesBiens.size()) {
                            Bien bien = itB.next();
                            this.ecrireLigneTableBien(bien, count);
                            count++;
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                default:
                    // Message pour les actions non reconnues
                    System.out.println("Action non reconnue !");
            }
        } else {
            // Message lorsque la source de l'événement est invalide
            System.out.println("Source non reconnue !");
        }
    }

    /**
     * Remplit une ligne de la table des entreprises.
     *
     * @param entreprise L'entreprise à insérer.
     * @param numeroLigne Le numéro de ligne où insérer les données.
     */
    public void ecrireLigneTableEntreprise(Entreprise entreprise, int numeroLigne) {
        DefaultTableModel modeleTableEntreprise = (DefaultTableModel) this.fenAjoutAssurance.getTabMesEntreprise().getModel();
        modeleTableEntreprise.setValueAt(entreprise.getSiret(), numeroLigne, 0);
        modeleTableEntreprise.setValueAt(entreprise.getNom(), numeroLigne, 1);
    }

    /**
     * Remplit une ligne de la table des logements.
     *
     * @param logement Le logement à insérer.
     * @param numeroLigne Le numéro de ligne où insérer les données.
     */
    public void ecrireLigneTableLogement(Logement logement, int numeroLigne) {
        DefaultTableModel modeleTableLogement = (DefaultTableModel) this.fenAjoutAssurance.getTabMesLogements().getModel();
        modeleTableLogement.setValueAt(logement.getIdLogement(), numeroLigne, 0);
        modeleTableLogement.setValueAt(logement.getDateAcquisition(), numeroLigne, 1);
    }

    /**
     * Remplit une ligne de la table des biens.
     *
     * @param bien Le bien à insérer.
     * @param numeroLigne Le numéro de ligne où insérer les données.
     */
    public void ecrireLigneTableBien(Bien bien, int numeroLigne) {
        DefaultTableModel modeleTableBien = (DefaultTableModel) this.fenAjoutAssurance.getTabMesBiens().getModel();
        modeleTableBien.setValueAt(bien.getIdBien(), numeroLigne, 0);
        modeleTableBien.setValueAt(bien.getVille(), numeroLigne, 1);
    }

    /**
     * Gère les changements de sélection dans les tables de la fenêtre.
     *
     * @param e L'événement de changement de sélection.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Gestion des sélections pour les entreprises, logements et biens
        JTable tabEntreprise = this.fenAjoutAssurance.getTabMesEntreprise();
        int selectedRow = tabEntreprise.getSelectedRow();
        if (selectedRow > -1) {
            try {
                this.entreprise = daoEntreprise.findById(tabEntreprise.getValueAt(selectedRow, 0).toString());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        JTable tabLogement = this.fenAjoutAssurance.getTabMesLogements();
        int selectedRowLog = tabLogement.getSelectedRow();
        if (selectedRowLog > -1) {
            try {
                this.logement = daoLogement.findById(tabLogement.getValueAt(selectedRowLog, 0).toString());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        JTable tabBien = this.fenAjoutAssurance.getTabMesBiens();
        int selectedRowBien = tabBien.getSelectedRow();
        if (selectedRowBien > -1) {
            try {
                this.bien = daoBien.findById(tabBien.getValueAt(selectedRowBien, 0).toString());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
