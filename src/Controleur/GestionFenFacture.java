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

import Modele.Facture;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoFacture;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;

/**
 * Gère les interactions utilisateur liées aux factures, telles que le chargement,
 * la suppression et la modification des factures dans l'interface graphique.
 */
public class GestionFenFacture implements ActionListener, ListSelectionListener {

    private FenAccueil fenAc;
    private DaoFacture daoFacture;
    private Facture facture;
    
    	
	public GestionFenFacture(FenAccueil fenAc) throws SQLException {
		this.fenAc = fenAc;
		this.facture = null;
		this.daoFacture = new DaoFacture(CictOracleDataSource.getInstance().getConnection());
	}
	
	/**
     * Gère les actions déclenchées par les boutons liés aux factures.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText();
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesFactures().getModel();

        if (texte != null) {
            switch (texte) {
                case "Modifier":
                    // TODO: Implémenter la logique de modification d'une facture
                    System.out.println("Vous MODIFIER une donnée dans Facture !");
                    break;

                case "Supprimer":
                    // Supprime la facture sélectionnée et met à jour le tableau
                    System.out.println("Vous SUPPRIMER une données dans Facture !");
                    int ligneSelectionnee = this.fenAc.getTabMesFactures().getSelectedRow();
                    try {
                        if (ligneSelectionnee == -1) {
                            JOptionPane.showMessageDialog(
                                fenAc,
                                "Veuillez sélectionner une facture à supprimer.",
                                "Aucune sélection",
                                JOptionPane.WARNING_MESSAGE
                            );
                            break;
                        }

                        String IdFacture = (String) this.fenAc.getTabMesFactures().getValueAt(ligneSelectionnee, 0);
                        Facture facture = this.daoFacture.findById(IdFacture);

                        if (facture != null) {
                            this.daoFacture.delete(facture);
                            modeleTable.removeRow(ligneSelectionnee);
                            JOptionPane.showMessageDialog(
                                fenAc,
                                "Facture supprimée avec succès.",
                                "Suppression réussie",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            JOptionPane.showMessageDialog(
                                fenAc,
                                "Facture introuvable.",
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

                case "Charger":
                    // Charge toutes les factures depuis la base de données et les affiche
                    System.out.println("Vous CHARGER les données dans Facture !");
                    try {
                        List<Facture> mesDonnees = this.daoFacture.findAll();
                        Iterateur<Facture> it = DaoFacture.getIterateurFacture();

                        if (it == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }

                        modeleTable.setRowCount(mesDonnees.size());

                        int count = 0;
                        while (it.hasNext() && count < mesDonnees.size()) {
                            Facture facture = it.next();
                            this.ecrireLigneTable(facture, count);
                            count++;
                        }

                        JOptionPane.showMessageDialog(
                            fenAc,
                            "Factures chargées avec succès.",
                            "Chargement réussi",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(
                            fenAc,
                            "Erreur lors du chargement des factures : " + ex.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        ex.printStackTrace();
                    }
                    break;

                case "Archiver":
                    // TODO: Implémenter la logique d'archivage d'une facture
                    System.out.println("Vous ARCHIVER une donnée prevenant de Facture !");

                    if (this.facture == null) {
                        JOptionPane.showMessageDialog(
                            this.fenAc,
                            "Veuillez sélectionner un logement a archiver !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }  
                    DefaultTableModel modeleTableArchives = (DefaultTableModel) this.fenAc.getTabFactureArchives().getModel();
                    
                    String[] EngrBien = {this.facture.getNumeroDevis(), this.facture.getDateEmission(), this.facture.getDatePaiement(), this.facture.getModePaiement(), this.facture.getDesignation()};
                    modeleTableArchives.addRow(EngrBien);
                    
                    // Affiche un message de confirmation après l'archivage
                    JOptionPane.showMessageDialog(
                        this.fenAc,
                        "Vous avez archivé une facture.",
                        "Confirmation",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    break;

                default:
                    System.out.println("Action non reconnue !");
            }
        } else {
            System.out.println("Source non reconnue !");
        }
    }

    /**
     * Remplit une ligne du tableau avec les données d'une facture.
     *
     */
    public void ecrireLigneTable(Facture facture, int numeroLigne) {
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesFactures().getModel();

        modeleTable.setValueAt(facture.getIdFacture(), numeroLigne, 0);
        modeleTable.setValueAt(facture.getDateEmission(), numeroLigne, 1);
        modeleTable.setValueAt(facture.getDatePaiement(), numeroLigne, 2);
        modeleTable.setValueAt(facture.getModePaiement(), numeroLigne, 3);
        modeleTable.setValueAt(facture.getDesignation(), numeroLigne, 4);
        modeleTable.setValueAt(facture.getmontantReelVerse(), numeroLigne, 5);
        modeleTable.setValueAt(facture.getMontant(), numeroLigne, 6);
        modeleTable.setValueAt(facture.getImputableLocataire() == 1 ? "Oui" : "Non", numeroLigne, 7);
        modeleTable.setValueAt(facture.getacompteVerse(), numeroLigne, 8);
    }
    
    @Override
	public void valueChanged(ListSelectionEvent e) {
		JTable tabFacture = this.fenAc.getTabMesFactures();
		int selectedRow = tabFacture.getSelectedRow();
		
		if (selectedRow > -1) {
			try {
				this.facture = daoFacture.findById(tabFacture.getValueAt(selectedRow, 0).toString());
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}
	

}
