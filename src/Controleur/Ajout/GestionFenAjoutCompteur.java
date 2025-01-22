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
import Modele.Compteur;
import Modele.Logement;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Modele.Dao.DaoCompteur;
import Modele.Dao.DaoLogement;
import Modele.Dao.Iterateur;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutCompteur;

/**
 * Contrôleur pour gérer les actions et les événements liés à l'ajout d'un compteur.
 */
public class GestionFenAjoutCompteur implements ActionListener, ListSelectionListener {

    private FenAjoutCompteur fenAjoutCompteur; 
    private Bien bien; 
    private Logement logement; 
    private DaoLogement daoLogement; 
    private DaoBien daoBien; 

    /**
     * Constructeur de la classe.
     *
     * @param fenAjoutCompteur Fenêtre associée à l'ajout d'un compteur.
     * @throws SQLException En cas de problème de connexion avec la base de données.
     */
    public GestionFenAjoutCompteur(FenAjoutCompteur fenAjoutCompteur) throws SQLException {
        this.fenAjoutCompteur = fenAjoutCompteur;
        this.bien = null;
        this.logement = null;
        this.daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
        this.daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
    }

    /**
     * Gère les actions déclenchées par les boutons de l'interface utilisateur.
     *
     * @param e L'événement associé à l'action utilisateur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText(); // Texte du bouton cliqué

        if (texte != null) {
            switch (texte) {
                case "Annuler":
                    // Action pour fermer la fenêtre d'ajout
                    System.out.println("Vous FERMEZ la page ajout compteur");
                    this.fenAjoutCompteur.dispose();
                    break;

                case "Ajouter":
                    // Action pour ajouter un compteur à un bien ou logement
                    System.out.println("Vous AJOUTER un compteur au bien !");
                    
                    // Vérification que le champ ID du compteur est renseigné
                    if (this.fenAjoutCompteur.getTextFieldIdCompteur().isEmpty()) {
                        JOptionPane.showMessageDialog(
                            this.fenAjoutCompteur,
                            "Veuillez remplir tous les champs !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    try {
                        // Récupération des données saisies par l'utilisateur
                        String IdCompteur = (String) fenAjoutCompteur.getTextFieldIdCompteur();
                        String TypeCompteur = (String) fenAjoutCompteur.getComboBoxTypeComp();

                        // Création d'une instance du DAO pour les compteurs
                        DaoCompteur daoCompteur = new DaoCompteur(CictOracleDataSource.getInstance().getConnection());

                        // Création et insertion du compteur dans la base de données
                        Compteur compteur = new Compteur(IdCompteur, TypeCompteur, bien, logement);
                        daoCompteur.create(compteur);

                        // Fermeture de la fenêtre après ajout
                        fenAjoutCompteur.dispose();

                    } catch (SQLException ex) {
                        // Gestion des erreurs SQL
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                case "Charger les tableaux":
                    // Action pour charger les données des logements et biens
                    System.out.println("Vous CHARGER les tableaux depuis ajout compteur !");
                    DefaultTableModel modeleTableLogement = (DefaultTableModel) this.fenAjoutCompteur.getTabMesLogements().getModel();
                    DefaultTableModel modeleTableBien = (DefaultTableModel) this.fenAjoutCompteur.getTabMesBiens().getModel();

                    // Chargement des logements
                    try {
                        List<Logement> mesDonneesLog = this.daoLogement.findAll();
                        Iterateur<Logement> itL = DaoLogement.getIterateurLogement();

                        if (itL == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }
                        modeleTableLogement.setRowCount(mesDonneesLog.size());

                        int countL = 0;
                        while (itL.hasNext() && countL < mesDonneesLog.size()) {
                            Logement logement = itL.next();
                            this.ecrireLigneTableLogement(logement, countL);
                            countL++;
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }

                    // Chargement des biens
                    try {
                        List<Bien> mesDonnees = this.daoBien.findAll();
                        Iterateur<Bien> itB = DaoBien.getIterateurBien();

                        if (itB == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }
                        modeleTableBien.setRowCount(mesDonnees.size());

                        int countB = 0;
                        while (itB.hasNext() && countB < mesDonnees.size()) {
                            Bien bien = itB.next();
                            this.ecrireLigneTableBien(bien, countB);
                            countB++;
                        }
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
     * Gère les changements de sélection dans les tableaux de la fenêtre.
     *
     * @param e L'événement de changement de sélection.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Gestion de la sélection d'un bien
        JTable tabBiens = this.fenAjoutCompteur.getTabMesBiens();
        int selectedRowBien = tabBiens.getSelectedRow();
        if (selectedRowBien > -1) {
            try {
                this.bien = daoBien.findById(tabBiens.getValueAt(selectedRowBien, 0).toString());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("bien trouvé : " + bien);
        }

        // Gestion de la sélection d'un logement
        JTable tabLogement = this.fenAjoutCompteur.getTabMesLogements();
        int selectedRowLogement = tabLogement.getSelectedRow();
        if (selectedRowLogement > -1) {
            try {
                this.logement = daoLogement.findById(tabLogement.getValueAt(selectedRowLogement, 0).toString());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("logement trouvé : " + logement);
        }
    }

    /**
     * Insère les données d'un logement dans une ligne de la table des logements.
     *
     * @param logement Le logement à insérer.
     * @param numeroLigne Le numéro de la ligne où insérer les données.
     */
    public void ecrireLigneTableLogement(Logement logement, int numeroLigne) {
        DefaultTableModel modeleTableLogement = (DefaultTableModel) this.fenAjoutCompteur.getTabMesLogements().getModel();
        modeleTableLogement.setValueAt(logement.getIdLogement(), numeroLigne, 0);
        modeleTableLogement.setValueAt(logement.getDateAcquisition(), numeroLigne, 1);
    }

    /**
     * Insère les données d'un bien dans une ligne de la table des biens.
     *
     * @param bien Le bien à insérer.
     * @param numeroLigne Le numéro de la ligne où insérer les données.
     */
    public void ecrireLigneTableBien(Bien bien, int numeroLigne) {
        DefaultTableModel modeleTableBien = (DefaultTableModel) this.fenAjoutCompteur.getTabMesBiens().getModel();
        modeleTableBien.setValueAt(bien.getIdBien(), numeroLigne, 0);
        modeleTableBien.setValueAt(bien.getTypeBien(), numeroLigne, 1);
    }
}
