package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Modele.Logement;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoLogement;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.FenCompteurs;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutFacture;
import Vue.Insertion.FenAjoutLogement;
import Vue.Insertion.FenAjoutQuotite;

import rapport.CreerRapport;

/**
 * Classe contrôleur pour gérer les actions liées aux logements dans l'application.
 * Implémente ActionListener pour écouter les actions des boutons et ListSelectionListener
 * pour surveiller les changements de sélection dans la table.
 */
public class GestionFenLogements implements ActionListener, ListSelectionListener {

    private FenAccueil fenAc; // Référence à la fenêtre d'accueil
    private DaoLogement daoLogement; // DAO pour gérer les opérations sur les logements
    private Logement logement; // Logement sélectionné actuellement

    /**
     * Constructeur.
     *
     * @param fenAc La fenêtre d'accueil associée à ce contrôleur.
     * @throws SQLException En cas de problème de connexion avec la base de données.
     */
    public GestionFenLogements(FenAccueil fenAc) throws SQLException {
        this.fenAc = fenAc;
        this.daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
        this.logement = null;
    }

    /**
     * Gère les actions déclenchées par les boutons de l'interface utilisateur.
     *
     * @param e L'événement associé à l'action utilisateur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText();
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLogements().getModel();

        if (texte != null) {
            switch (texte) {
                case "Supprimer":
                    // Supprime un logement sélectionné dans le tableau
                    System.out.println("Vous SUPPRIMER une donnée dans Logement !");
                    int ligneSelectionnee = this.fenAc.getTabMesLogements().getSelectedRow();

                    try {
                        String IdLogement = (String) this.fenAc.getTabMesLogements().getValueAt(ligneSelectionnee, 0);
                        Logement logement = this.daoLogement.findById(IdLogement);
                        this.daoLogement.delete(logement);
                        modeleTable.removeRow(ligneSelectionnee);
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                case "Générer un word":
                    // Génère un rapport Word des logements
                    System.out.println("Vous générez un rapport des Logements !");
                    try {
                        List<Logement> logements = daoLogement.findAll();
                        CreerRapport.genererRapportLogement(logements);
                        JOptionPane.showMessageDialog(
                            this.fenAc,
                            "Rapport des logements généré avec succès !",
                            "Succès",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(
                            this.fenAc,
                            "Erreur lors de la génération du rapport des logements.",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                    break;

                case "Charger":
                    // Charge les données des logements dans le tableau
                    System.out.println("Vous CHARGER les données dans Logement !");
                    try {
                        List<Logement> mesDonnees = this.daoLogement.findAll();
                        Iterateur<Logement> it = DaoLogement.getIterateurLogement();

                        if (it == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }
                        modeleTable.setRowCount(mesDonnees.size());

                        // Parcours les données pour les insérer dans le tableau
                        int count = 0;
                        while (it.hasNext() && count < mesDonnees.size()) {
                            Logement logement = it.next();
                            this.ecrireLigneTable(logement, count);
                            count++;
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                case "Afficher les compteurs":
                    // Affiche les compteurs associés aux logements
                    System.out.println("Vous AFFICHER LES COMPTEURS depuis Logement !");
                    FenCompteurs fenCompMesLogements = null;

                    try {
                        fenCompMesLogements = new FenCompteurs();
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                        e1.printStackTrace();
                    }

                    fenAc.getLayeredPane().add(fenCompMesLogements);
                    fenCompMesLogements.setVisible(true);
                    break;

                case "Ajouter un logement":
                    // Ouvre une fenêtre pour ajouter un nouveau logement
                    System.out.println("Vous AJOUTER UN LOGEMENT depuis Logement !");
                    FenAjoutLogement fenAddLogements = new FenAjoutLogement();
                    fenAc.getLayeredPane().add(fenAddLogements);
                    fenAddLogements.setVisible(true);
                    break;

                case "Ajouter des factures":
                    // Ouvre une fenêtre pour ajouter des factures pour le logement sélectionné
                    System.out.println("Vous AJOUTER DE
