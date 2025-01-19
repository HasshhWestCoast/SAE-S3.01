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

/**
 * Contrôleur pour la gestion des logements dans l'interface principale.
 * Gère les actions telles que le chargement, l'ajout, la suppression et l'archivage des logements.
 * Implémente également la gestion de la sélection des lignes dans le tableau des logements.
 */
public class GestionFenLogements implements ActionListener, ListSelectionListener {

    private FenAccueil fenAc;
    private DaoLogement daoLogement;
    private Logement logement;

<<<<<<< HEAD
	private FenAccueil fenAc;
	private DaoLogement daoLogement;
	private Logement logement;
	
	public GestionFenLogements(FenAccueil fenAc) throws SQLException {
		this.fenAc = fenAc;
		this.daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
		this.logement = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLogements().getModel();
		
		if (texte != null) {
			switch (texte) {
					
				case "Supprimer":
					System.out.println("Vous SUPPRIMER une données dans Logement !");
					int ligneSelectionnee = this.fenAc.getTabMesLogements().getSelectedRow();
=======
    /**
     * Constructeur qui initialise le contrôleur avec la fenêtre d'accueil et le DAO Logement.
     */
    public GestionFenLogements(FenAccueil fenAc) throws SQLException {
        this.fenAc = fenAc;
        this.daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
        this.logement = null;
    }
>>>>>>> 8c8250388d5102c38f3c2a9f467299f7911d0366

    /**
     * Gère les actions déclenchées par les boutons liés aux logements.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText();
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLogements().getModel();

        if (texte != null) {
            switch (texte) {
                case "Modifier":
                    // TODO: Implémenter la logique de modification d'un logement
                    System.out.println("Vous MODIFIER une donnée dans Logement !");
                    break;

<<<<<<< HEAD
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		JTable tabLogement = this.fenAc.getTabMesLogements();
		int selectedRow = tabLogement.getSelectedRow();
		
		if (selectedRow > -1) {
			try {
				this.logement = daoLogement.findById(tabLogement.getValueAt(selectedRow, 0).toString());
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}
	

=======
                case "Supprimer":
                    // Supprime le logement sélectionné et met à jour le tableau
                    System.out.println("Vous SUPPRIMER une données dans Logement !");
                    int ligneSelectionnee = this.fenAc.getTabMesLogements().getSelectedRow();

                    try {
                        if (ligneSelectionnee == -1) {
                            JOptionPane.showMessageDialog(
                                fenAc,
                                "Veuillez sélectionner un logement à supprimer.",
                                "Aucune sélection",
                                JOptionPane.WARNING_MESSAGE
                            );
                            break;
                        }

                        String IdLogement = (String) this.fenAc.getTabMesLogements().getValueAt(ligneSelectionnee, 0);
                        Logement logement = this.daoLogement.findById(IdLogement);

                        if (logement != null) {
                            this.daoLogement.delete(logement);
                            modeleTable.removeRow(ligneSelectionnee);
                            JOptionPane.showMessageDialog(
                                fenAc,
                                "Logement supprimé avec succès.",
                                "Suppression réussie",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            JOptionPane.showMessageDialog(
                                fenAc,
                                "Logement introuvable.",
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(
                            fenAc,
                            "Erreur lors de la suppression : " + ex.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        ex.printStackTrace();
                    }
                    break;

                case "Archiver":
                    // TODO: Implémenter la logique d'archivage d'un logement
                    System.out.println("Vous ARCHIVER une donnée prevenant de Logement !");
                    break;

                case "Ajouter un diagnostic":
                    // TODO: Implémenter la logique d'ajout d'un diagnostic à un logement
                    System.out.println("Vous AJOUTER UN DIAGNOSTIC depuis Logement !");
                    break;

                case "Charger":
                    // Charge tous les logements depuis la base de données et les affiche dans le tableau
                    System.out.println("Vous CHARGER les données dans Logement !");
                    try {
                        List<Logement> mesDonnees = this.daoLogement.findAll();
                        Iterateur<Logement> it = DaoLogement.getIterateurLogement();

                        if (it == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }

                        modeleTable.setRowCount(mesDonnees.size());

                        int count = 0;
                        while (it.hasNext() && count < mesDonnees.size()) {
                            Logement logement = it.next();
                            this.ecrireLigneTable(logement, count);
                            count++;
                        }

                        JOptionPane.showMessageDialog(
                            fenAc,
                            "Logements chargés avec succès.",
                            "Chargement réussi",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(
                            fenAc,
                            "Erreur lors du chargement des logements : " + ex.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        ex.printStackTrace();
                    }
                    break;

                case "Afficher les compteurs":
                    // Ouvre la fenêtre des compteurs liés aux logements
                    System.out.println("Vous AFFICHER LES COMPTEURS depuis Logement !");
                    FenCompteurs fenCompMesLogements = null;

                    try {
                        fenCompMesLogements = new FenCompteurs();
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(
                            fenAc,
                            "Erreur lors de l'ouverture des compteurs : " + e1.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        e1.printStackTrace();
                        break;
                    }

                    fenAc.getLayeredPane().add(fenCompMesLogements);
                    fenCompMesLogements.setVisible(true);
                    break;

                case "Ajouter un logement":
                    // Ouvre la fenêtre pour ajouter un nouveau logement
                    System.out.println("Vous AJOUTER UN LOGEMENT depuis Logement !");
                    FenAjoutLogement fenAddLogements = new FenAjoutLogement();
                    fenAc.getLayeredPane().add(fenAddLogements);
                    fenAddLogements.setVisible(true);
                    break;

                case "Ajouter des factures":
                    // Ouvre la fenêtre pour ajouter des factures au logement sélectionné
                    System.out.println("Vous AJOUTER DES FACTURES depuis Logement !");
                    
                    if (this.logement == null) {
                        JOptionPane.showMessageDialog(
                            this.fenAc,
                            "Veuillez sélectionner un logement !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    FenAjoutFacture fenAddFacture = null;

                    try {
                        fenAddFacture = new FenAjoutFacture(this.logement);
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(
                            fenAc,
                            "Erreur lors de l'ouverture de la fenêtre de factures : " + e1.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        e1.printStackTrace();
                        break;
                    }

                    fenAc.getLayeredPane().add(fenAddFacture);
                    fenAddFacture.setVisible(true);
                    break;

                default:
                    System.out.println("Action non reconnue !");
            }
        } else {
            System.out.println("Source non reconnue !");
        }
    }

    /**
     * Remplit une ligne du tableau avec les données d'un logement.
     *
     */
    public void ecrireLigneTable(Logement logement, int numeroLigne) {
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLogements().getModel();

        modeleTable.setValueAt(logement.getIdLogement(), numeroLigne, 0);
        modeleTable.setValueAt(logement.getSurfaceHabitable(), numeroLigne, 1);
        modeleTable.setValueAt(logement.getDateAcquisition(), numeroLigne, 2);
        modeleTable.setValueAt(logement.getType_logement(), numeroLigne, 3);
        modeleTable.setValueAt(logement.getNbPieces(), numeroLigne, 4);
        modeleTable.setValueAt(logement.getNumEtage(), numeroLigne, 5);
    }

    /**
     * Déclenchée lorsqu'une sélection de ligne dans le tableau change.
     * Met à jour l'objet logement avec le logement sélectionné.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable tabLogement = this.fenAc.getTabMesLogements();
        int selectedRow = tabLogement.getSelectedRow();

        if (selectedRow > -1) {
            try {
                String id = tabLogement.getValueAt(selectedRow, 0).toString();
                this.logement = daoLogement.findById(id);
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(
                    fenAc,
                    "Erreur lors de la sélection du logement : " + e1.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
                );
                e1.printStackTrace();
            }
        }
    }
>>>>>>> 8c8250388d5102c38f3c2a9f467299f7911d0366

}
