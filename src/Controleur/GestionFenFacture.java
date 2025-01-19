package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Modele.Facture;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoFacture;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;

public class GestionFenFacture implements ActionListener{

	private FenAccueil fenAc;
	private DaoFacture daoFacture;
	
	public GestionFenFacture(FenAccueil fenAc) throws SQLException {
		this.fenAc = fenAc;
		this.daoFacture = new DaoFacture(CictOracleDataSource.getInstance().getConnection());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesFactures().getModel();
		
		if (texte != null) {
			switch (texte) {
					
				case "Supprimer":
					System.out.println("Vous SUPPRIMER une données dans Facture !");
					int ligneSelectionnee = this.fenAc.getTabMesFactures().getSelectedRow();

					try {
						String IdFacture = (String) this.fenAc.getTabMesFactures().getValueAt(ligneSelectionnee, 0);
						
						Facture facture = this.daoFacture.findById(IdFacture);
						
						this.daoFacture.delete(facture);
						
						modeleTable.removeRow(ligneSelectionnee);
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
					
				case "Charger":
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
						while(it.hasNext() && count < mesDonnees.size()) {	
							Facture facture = it.next();
							this.ecrireLigneTable(facture, count);
							count++;
						}
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
					
				case "Archiver":
					System.out.println("Vous ARCHIVER une donnée prevenant de Facture !");
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
	
	public void ecrireLigneTable(Facture facture, int numeroLigne) {
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesFactures().getModel();

		modeleTable.setValueAt(facture.getIdFacture(), numeroLigne, 0);
		modeleTable.setValueAt(facture.getDateEmission(), numeroLigne, 1);
		modeleTable.setValueAt(facture.getDatePaiement(), numeroLigne, 2);		
		modeleTable.setValueAt(facture.getModePaiement(), numeroLigne, 3);
		modeleTable.setValueAt(facture.getDesignation(), numeroLigne, 4);
		modeleTable.setValueAt(facture.getmontantReelVerse(), numeroLigne, 5);
		modeleTable.setValueAt(facture.getMontant(), numeroLigne, 6);
		modeleTable.setValueAt(facture.getImputableLocataire(), numeroLigne, 7);
		modeleTable.setValueAt(facture.getacompteVerse(), numeroLigne, 8);
	}	
}
