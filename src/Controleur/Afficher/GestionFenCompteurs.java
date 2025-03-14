package Controleur.Afficher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Modele.Compteur;
import Modele.Releve;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoCompteur;
import Modele.Dao.DaoReleve;
import Modele.Dao.Iterateur;
import Vue.FenCompteurs;
import Vue.FenMesReleves;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutCompteur;

/**
 * Classe contrôleur pour gérer les actions et les événements liés à la fenêtre des compteurs.
 */
public class GestionFenCompteurs implements ActionListener, ListSelectionListener {

    private FenCompteurs fenComp; // Référence à la fenêtre des compteurs
    private DaoCompteur daoCompteur; // DAO pour interagir avec les données des compteurs
    private DaoReleve daoReleve; // DAO pour interagir avec les données des relevés
    private Compteur compteur; // Compteur sélectionné

    /**
     * Constructeur de la classe.
     * @param fenComp Référence à la fenêtre des compteurs.
     * @throws SQLException En cas d'erreur de connexion à la base de données.
     */
    public GestionFenCompteurs(FenCompteurs fenComp) throws SQLException {
        this.fenComp = fenComp;
        this.daoReleve = new DaoReleve(CictOracleDataSource.getInstance().getConnection());
        this.daoCompteur = new DaoCompteur(CictOracleDataSource.getInstance().getConnection());
    }

    /**
     * Gère les actions déclenchées par les boutons de l'interface utilisateur.
     * @param e L'événement déclenché.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText(); // Texte du bouton cliqué

        if (texte != null) {
            switch (texte) {
                case "Annuler":
                    // Fermer la fenêtre des compteurs
                    System.out.println("Vous FERMEZ la page Compteurs !");
                    this.fenComp.dispose();
                    break;

                case "Afficher date relevé":
                    // Afficher les relevés associés au compteur sélectionné
                    System.out.println("Vous Afficher les relevé d'un compteur !");
                    if (this.compteur == null) {
                        JOptionPane.showMessageDialog(
                            this.fenComp,
                            "Veuillez sélectionner un compteur d'abord !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    try {
                        // Initialisation de la fenêtre des relevés
                        FenMesReleves fenMesReleve = new FenMesReleves(this.compteur);
                        fenComp.getLayeredPane().add(fenMesReleve);

                        // Récupération des relevés du compteur
                        DefaultTableModel modeleTableReleve = (DefaultTableModel) fenMesReleve.getTabMesReleves().getModel();
                        List<Releve> mesReleves = this.daoReleve.findAllById(this.compteur.getIdCompteur());

                        // Remplissage de la table avec les relevés récupérés
                        modeleTableReleve.setRowCount(mesReleves.size());
                        for (int i = 0; i < mesReleves.size(); i++) {
                            Releve releve = mesReleves.get(i);
                            modeleTableReleve.setValueAt(releve.getCompteur().getIdCompteur(), i, 0);
                            modeleTableReleve.setValueAt(releve.getDateReleve(), i, 1);
                            modeleTableReleve.setValueAt(releve.getIndexReleve(), i, 2);
                        }

                        // Rendre la fenêtre des relevés visible
                        fenMesReleve.setVisible(true);
                        break;

                    } catch (Exception e2) {
                        System.out.println(e2.getMessage());
                        e2.printStackTrace();
                    }
                    break;

                case "Ajouter":
                    // Afficher la fenêtre pour ajouter un compteur
                    System.out.println("Vous charger la page ajouter un Compteur !");
                    try {
                        FenAjoutCompteur fenaddComp = null;
                        fenaddComp = new FenAjoutCompteur();
                        fenComp.getLayeredPane().add(fenaddComp);
                        fenaddComp.setVisible(true);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    break;

                case "Charger":
                    // Charger les compteurs dans la table
                	System.out.println("Vous charger les compteurs.");
                    DefaultTableModel modeleTableCompteur = (DefaultTableModel) fenComp.gettabMesCompteurs().getModel();
                    List<Compteur> mesDonnees = null;

                    try {
                        mesDonnees = this.daoCompteur.findAll();
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                        e1.printStackTrace();
                    }

                    // Utilisation de l'itérateur pour parcourir les compteurs
                    Iterateur<Compteur> it = DaoCompteur.getIterateurCompteur();
                    if (it == null) {
                        System.out.println("Itérateur non initialisé !");
                        break;
                    }

                    // Remplissage de la table avec les compteurs récupérés
                    modeleTableCompteur.setRowCount(mesDonnees.size());
                    int count = 0;
                    while (it.hasNext() && count < mesDonnees.size()) {
                        Compteur compteur = it.next();
                        this.ecrireLigneTable(compteur, count);
                        count++;
                    }
                    break;

                default:
                    // Cas d'une action non reconnue
                    System.out.println("Action non reconnue !");
            }
        } else {
            // Si aucune source valide n'est détectée
            System.out.println("Source non reconnue !");
        }
    }

    /**
     * Insère les données d'un compteur dans une ligne de la table.
     * @param compteur Le compteur à insérer.
     * @param numeroLigne Le numéro de la ligne où insérer les données.
     */
    public void ecrireLigneTable(Compteur compteur, int numeroLigne) {
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenComp.gettabMesCompteurs().getModel();

        modeleTable.setValueAt(compteur.getIdCompteur(), numeroLigne, 0);
        modeleTable.setValueAt(compteur.getTypeComp(), numeroLigne, 1);
        modeleTable.setValueAt(compteur.getImmeuble() != null ? compteur.getImmeuble().getIdLogement() : "NA", numeroLigne, 2);
        modeleTable.setValueAt(compteur.getBien().getIdBien(), numeroLigne, 3);
    }

    /**
     * Gère les changements de sélection dans la table des compteurs.
     * @param e L'événement de sélection.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable tabCompteur = this.fenComp.gettabMesCompteurs();
        int selectedRow = tabCompteur.getSelectedRow();

        if (selectedRow > -1) {
            try {
                this.compteur = daoCompteur.findById(tabCompteur.getValueAt(selectedRow, 0).toString());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
