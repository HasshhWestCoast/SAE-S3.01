package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modele.Compteur;
import Modele.Releve;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoCompteur;
import Modele.Dao.DaoReleve;
import Vue.FenMesReleves;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutReleves;

public class GestionFenMesReleves implements ActionListener{


	private FenMesReleves fenMesReleve;
	private DaoReleve daoReleve;
	private DaoCompteur daoCompteur;
	private Compteur compteur;
	
	public GestionFenMesReleves(FenMesReleves fenMesReleve) throws SQLException {
		this.fenMesReleve = fenMesReleve;
		this.compteur = null;
		this.daoReleve = new DaoReleve(CictOracleDataSource.getInstance().getConnection());
		this.daoCompteur = new DaoCompteur(CictOracleDataSource.getInstance().getConnection());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenMesReleve.getTabMesReleves().getModel();
		
		if (texte != null) {
			switch (texte) {

			case "Ajouter":
				System.out.println("Vous ouvrez AJOUTER RELEVE dans MesReleves !");

				try {
					this.compteur = this.CompteurActuelle();
					FenAjoutReleves fenAddReleve = new FenAjoutReleves(this.compteur);
					fenMesReleve.getLayeredPane().add(fenAddReleve);
					fenAddReleve.setVisible(true);
					break;
				}catch (Exception e2) {
					System.out.println(e2.getMessage());
					e2.printStackTrace();
				}				
				
			case "Supprimer":
				System.out.println("Vous SUPPRIMER une données dans Mes Releves !");
				int ligneSelectionnee = this.fenMesReleve.getTabMesReleves().getSelectedRow();

				try {
					String IdCompteur = (String) this.fenMesReleve.getTabMesReleves().getValueAt(ligneSelectionnee, 0);
					String DateReleve = (String) this.fenMesReleve.getTabMesReleves().getValueAt(ligneSelectionnee, 1);
					
					Releve releve = daoReleve.findById(IdCompteur, DateReleve);
					this.daoReleve.delete(releve);
					
					modeleTable.removeRow(ligneSelectionnee);
					
				}catch (SQLException ex) {
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
				break;
				
				case "Annuler":
					System.out.println("Vous FERMEZ la page Mes Releves");
					this.fenMesReleve.dispose();
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
	
	public Compteur CompteurActuelle() {
		JTable tabReleves = this.fenMesReleve.getTabMesReleves();
		try {
			Compteur compt = daoCompteur.findById(tabReleves.getValueAt(0, 0).toString());
			return compt;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
