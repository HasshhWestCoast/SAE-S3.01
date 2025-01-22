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


public class GestionFenLogements implements ActionListener, ListSelectionListener{

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

					try {
						String IdLogement = (String) this.fenAc.getTabMesLogements().getValueAt(ligneSelectionnee, 0);
						
						Logement logement = this.daoLogement.findById(IdLogement);
						
						this.daoLogement.delete(logement);
						
						modeleTable.removeRow(ligneSelectionnee);
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
				
				case "Archiver":
					System.out.println("Vous ARCHIVER une donnée prevenant de Logement !");
					break;
					
				case "Ajouter un diagnostic":
					System.out.println("Vous AJOUTER UN DIAGNOSTIC depuis Logement !");
					break;
					
				case "Charger":
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
						while(it.hasNext() && count < mesDonnees.size()) {	
							Logement logement = it.next();
							this.ecrireLigneTable(logement, count);
							count++;
						}
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
					
				case "Afficher les compteurs":
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
					System.out.println("Vous AJOUTER UN LOGEMENT depuis Logement !");
					FenAjoutLogement fenAddLogements = new FenAjoutLogement();
					fenAc.getLayeredPane().add(fenAddLogements);
					fenAddLogements.setVisible(true);
					break;
					
				case "Ajouter des factures":
					System.out.println("Vous AJOUTER DES FACTURES depuis Logement !");
					
					 // Vérification des conditions
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
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
					
					fenAc.getLayeredPane().add(fenAddFacture);
					fenAddFacture.setVisible(true);
					break;
				case "Ajouter Quotite":
					
					 // Vérification des conditions
				    if (this.logement == null) {
				        JOptionPane.showMessageDialog(
				            this.fenAc,
				            "Veuillez sélectionner un logement !",
				            "Erreur",
				            JOptionPane.ERROR_MESSAGE
				        );
				        return;
				    }
				    
					FenAjoutQuotite fenAddQuotite = null;
					
					fenAddQuotite = new FenAjoutQuotite(this.logement);
					
					fenAc.getLayeredPane().add(fenAddQuotite);
					fenAddQuotite.setVisible(true);
					
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
	
	public void ecrireLigneTable(Logement logement, int numeroLigne) {
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLogements().getModel();

		modeleTable.setValueAt(logement.getIdLogement(), numeroLigne, 0);
		modeleTable.setValueAt(logement.getSurfaceHabitable(), numeroLigne, 1);
		modeleTable.setValueAt(logement.getDateAcquisition(), numeroLigne, 2);		
		modeleTable.setValueAt(logement.getType_logement(), numeroLigne, 3);
		modeleTable.setValueAt(logement.getNbPieces(), numeroLigne, 4);
		modeleTable.setValueAt(logement.getNumEtage(), numeroLigne, 5);

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
	


}
