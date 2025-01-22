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
import Modele.Facture;
import Modele.Logement;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoEntreprise;
import Modele.Dao.DaoFacture;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutEntreprise;
import Vue.Insertion.FenAjoutFacture;

/**
 * Contrôleur pour gérer les actions et événements liés à l'ajout d'une facture.
 */
public class GestionFenAjoutFacture implements ActionListener, ListSelectionListener {

    private FenAjoutFacture fenAjoutFacture; 
    private DaoEntreprise daoEntreprise; 
    private Logement logement; 
    private Bien bien; 
    private Entreprise entreprise; 

    /**
     * Constructeur de la classe.
     *
     * @param fenAjoutFacture Fenêtre associée à l'ajout d'une facture.
     * @throws SQLException En cas de problème de connexion avec la base de données.
     */
    public GestionFenAjoutFacture(FenAjoutFacture fenAjoutFacture) throws SQLException {
        this.fenAjoutFacture = fenAjoutFacture;
        this.daoEntreprise = new DaoEntreprise(CictOracleDataSource.getInstance().getConnection());
        this.logement = null;
        this.bien = null;
        this.entreprise = null;
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
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenAjoutFacture.getTabMesEntreprise().getModel();

        FenAccueil fenAC = (FenAccueil) this.fenAjoutFacture.getTopLevelAncestor();

        if (texte != null) {
            switch (texte) {
                case "Annuler":
                    // Action pour fermer la fenêtre d'ajout
                    System.out.println("Vous FERMEZ la page ajout Facture !");
                    this.fenAjoutFacture.dispose();
                    break;

                case "Supprimer":
                    // Supprimer une entreprise sélectionnée dans la table
                    int ligneSelectionnee = this.fenAjoutFacture.getTabMesEntreprise().getSelectedRow();

                    try {
                        String Siret = (String) this.fenAjoutFacture.getTabMesEntreprise().getValueAt(ligneSelectionnee, 0);
                        Entreprise entreprise = this.daoEntreprise.findById(Siret);
                        this.daoEntreprise.delete(entreprise);
                        modeleTable.removeRow(ligneSelectionnee);
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                case "Ajouter":
                    // Ajouter une facture
                    System.out.println("Vous AJOUTER une Facture !");
                    
                    // Vérification que l'entreprise est sélectionnée
                    if (this.entreprise == null) {
                        JOptionPane.showMessageDialog(
                            this.fenAjoutFacture,
                            "Veuillez sélectionner une entreprise !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    // Vérification des champs requis
                    if (this.fenAjoutFacture.getTextFieldIdFacture().isEmpty() ||
                        this.fenAjoutFacture.getTextFieldDateEmission().isEmpty() ||
                        this.fenAjoutFacture.getTextFieldDatePaiement().isEmpty() ||
                        this.fenAjoutFacture.getTextFieldMontantReelVerse().isEmpty() ||
                        this.fenAjoutFacture.getTextFieldMontant().isEmpty()) {
                        JOptionPane.showMessageDialog(
                            this.fenAjoutFacture,
                            "Veuillez remplir tous les champs requis !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    try {
                        // Récupération des données saisies
                        DefaultTableModel modeleTableFacture = (DefaultTableModel) fenAC.getTabMesFactures().getModel();
                        String IdFacture = (String) fenAjoutFacture.getTextFieldIdFacture();
                        String DateEmission = (String) fenAjoutFacture.getTextFieldDateEmission();
                        String DatePaiement = (String) fenAjoutFacture.getTextFieldDatePaiement();
                        String ModePaiement = (String) fenAjoutFacture.getComboBoxModePaiement();
                        String NumeroDevis = (String) fenAjoutFacture.getTextFieldNumeroDevis();
                        String Designation = (String) fenAjoutFacture.getComboBoxDesignation();
                        String MontantReelVerseString = (String) fenAjoutFacture.getTextFieldMontantReelVerse();
                        double MontantReelVerse = Double.parseDouble(MontantReelVerseString);
                        String MontantString = (String) fenAjoutFacture.getTextFieldMontant();
                        double Montant = Double.parseDouble(MontantString);
                        Boolean ImputableLocataireString = (Boolean) fenAjoutFacture.getcheckImputableLocataire();
                        int ImputableLocataire = ImputableLocataireString ? 1 : 0;
                        String AcompteVerseString = (String) fenAjoutFacture.getTextFieldAcompteVersé();
                        double AcompteVerse = Double.parseDouble(AcompteVerseString);

                        // Association de la facture au bien ou logement sélectionné
                        if (this.fenAjoutFacture.getPrecedent() instanceof Bien) {
                            bien = (Bien) this.fenAjoutFacture.getPrecedent();
                        } else if (this.fenAjoutFacture.getPrecedent() instanceof Logement) {
                            logement = (Logement) this.fenAjoutFacture.getPrecedent();
                        }

                        // Création et insertion de la facture
                        DaoFacture daoFacture = new DaoFacture(CictOracleDataSource.getInstance().getConnection());
                        Facture facture = new Facture(
                            IdFacture, DateEmission, DatePaiement, ModePaiement, NumeroDevis,
                            Designation, MontantReelVerse, Montant, ImputableLocataire, AcompteVerse,
                            logement, bien, entreprise
                        );
                        daoFacture.create(facture);

                        // Ajout des données dans le tableau des factures
                        String[] EngrFacture = {
                            IdFacture, DateEmission, DatePaiement, ModePaiement, Designation,
                            MontantReelVerseString, MontantString, Integer.toString(ImputableLocataire), AcompteVerseString
                        };
                        modeleTableFacture.addRow(EngrFacture);

                        // Fermeture de la fenêtre après ajout
                        fenAjoutFacture.dispose();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                case "Inserer":
                    // Ajouter une entreprise depuis la fenêtre de facturation
                    System.out.println("Vous AJOUTER une Entreprise depuis Facture !");
                    FenAjoutEntreprise fenAjoutEntreprise = new FenAjoutEntreprise(fenAjoutFacture);
                    fenAC.getLayeredPane().add(fenAjoutEntreprise);
                    fenAjoutEntreprise.setVisible(true);
                    fenAjoutEntreprise.moveToFront();
                    break;

                case "Charger":
                    // Charger les entreprises disponibles
                    System.out.println("Vous CHARGEZ les Entreprises depuis Factures !");
                    try {
                        List<Entreprise> mesDonnees = this.daoEntreprise.findAll();
                        Iterateur<Entreprise> it = DaoEntreprise.getIterateurEntreprise();

                        if (it == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }
                        modeleTable.setRowCount(mesDonnees.size());

                        int count = 0;
                        while (it.hasNext() && count < mesDonnees.size()) {
                            Entreprise entreprise = it.next();
                            this.ecrireLigneTable(entreprise, count);
                            count++;
                        }
                        this.fenAjoutFacture.getTabMesEntreprise().getSelectionModel().addListSelectionListener(this);

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                default:
                    // Message pour une action non reconnue
                    System.out.println("Action non reconnu !");
            }
        } else {
            // Message si la source de l'événement est invalide
            System.out.println("Source non reconnu !");
        }
    }

    /**
     * Insère les données d'une entreprise dans une ligne de la table.
     *
     * @param entreprise L'entreprise à insérer.
     * @param numeroLigne Le numéro de ligne où insérer les données.
     */
    public void ecrireLigneTable(Entreprise entreprise, int numeroLigne) {
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenAjoutFacture.getTabMesEntreprise().getModel();
        modeleTable.setValueAt(entreprise.getSiret(), numeroLigne, 0);
        modeleTable.setValueAt(entreprise.getNom(), numeroLigne, 1);
    }

    /**
     * Gère les changements de sélection dans le tableau des entreprises.
     *
     * @param e L'événement de changement de sélection.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable tabEntreprise = this.fenAjoutFacture.getTabMesEntreprise();
        int selectedRow = tabEntreprise.getSelectedRow();
        if (selectedRow > -1) {
            try {
                this.entreprise = daoEntreprise.findById(tabEntreprise.getValueAt(selectedRow, 0).toString());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
