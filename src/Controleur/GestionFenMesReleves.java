package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Modele.Compteur;
import Modele.Releve;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoReleve;
import Vue.FenMesReleves;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutReleves;

/**
 * Contrôleur pour la gestion des relevés dans la fenêtre "Mes Releves".
 * Gère les actions telles que l'ajout, la suppression des relevés,
 * et la fermeture de la fenêtre. Interagit avec les DAO pour manipuler
 * les données des relevés et des compteurs.
 */
public class GestionFenMesReleves implements ActionListener {

    private FenMesReleves fenMesReleve;
    private DaoReleve daoReleve;
    private Compteur compteur;

    /**
     * Constructeur qui initialise le contrôleur avec la fenêtre des relevés
     * et les DAO nécessaires pour les opérations sur les relevés et les compteurs.
     */
    public GestionFenMesReleves(FenMesReleves fenMesReleve) throws SQLException {
        this.fenMesReleve = fenMesReleve;
        this.compteur = fenMesReleve.getMonCompteur();
        this.daoReleve = new DaoReleve(CictOracleDataSource.getInstance().getConnection());
    }

    /**
     * Gère les actions déclenchées par les boutons de la fenêtre des relevés.
     * Les actions possibles sont : Ajouter, Supprimer, et Annuler.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText();
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenMesReleve.getTabMesReleves().getModel();

        if (texte != null) {
            switch (texte) {

                case "Ajouter":
                    // Ouvre la fenêtre pour ajouter un nouveau relevé
                    System.out.println("Vous ouvrez AJOUTER RELEVE dans MesReleves !");
                    try {
                        // Récupère le compteur actuel associé au relevé
                        if (this.fenMesReleve.getMonCompteur() == null) {
                            JOptionPane.showMessageDialog(
                                fenMesReleve,
                                "Aucun compteur associé non trouvé.",
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE
                            );
                            break;
                        }
                        // Ouvre la fenêtre d'ajout de relevé avec le compteur sélectionné
                        FenAjoutReleves fenAddReleve = new FenAjoutReleves(this.compteur);
                        fenMesReleve.getLayeredPane().add(fenAddReleve);
                        fenAddReleve.setVisible(true);
                        break; // Important pour éviter le fall-through
                    } catch (Exception e2) {
                        JOptionPane.showMessageDialog(
                            fenMesReleve,
                            "Erreur lors de l'ouverture de la fenêtre d'ajout : " + e2.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        e2.printStackTrace();
                        break;
                    }

                case "Supprimer":
                    // Supprime le relevé sélectionné dans le tableau
                    System.out.println("Vous SUPPRIMER une donnée dans Mes Releves !");
                    int ligneSelectionnee = this.fenMesReleve.getTabMesReleves().getSelectedRow();

                    if (ligneSelectionnee == -1) {
                        JOptionPane.showMessageDialog(
                            fenMesReleve,
                            "Veuillez sélectionner un relevé à supprimer.",
                            "Aucune sélection",
                            JOptionPane.WARNING_MESSAGE
                        );
                        break;
                    }

                    try {
                        // Récupère les identifiants du relevé à supprimer
                        String IdCompteur = (String) this.fenMesReleve.getTabMesReleves().getValueAt(ligneSelectionnee, 0);
                        String DateReleve = (String) this.fenMesReleve.getTabMesReleves().getValueAt(ligneSelectionnee, 1);

                        // Trouve et supprime le relevé dans la base de données
                        Releve releve = daoReleve.findById(IdCompteur, DateReleve);
                        if (releve != null) {
                            this.daoReleve.delete(releve);
                            modeleTable.removeRow(ligneSelectionnee);
                            JOptionPane.showMessageDialog(
                                fenMesReleve,
                                "Relevé supprimé avec succès.",
                                "Suppression réussie",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            JOptionPane.showMessageDialog(
                                fenMesReleve,
                                "Relevé introuvable.",
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(
                            fenMesReleve,
                            "Erreur lors de la suppression du relevé : " + ex.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        ex.printStackTrace();
                    }
                    break;

                case "Annuler":
                    // Ferme la fenêtre des relevés
                    System.out.println("Vous FERMEZ la page Mes Releves");
                    this.fenMesReleve.dispose();
                    break;

                default:
                    // Gère les actions non reconnues
                    System.out.println("Action non reconnue !");
            }
        } else {
            System.out.println("Source non reconnue !");
        }
    }
   
}
