package Controleur.Afficher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Modele.Compteur;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoCompteur;
import Modele.Dao.Iterateur;
import Vue.FenCompteursBien;
import Vue.RoundedButton;

public class GestionFenCompteursBien implements ActionListener{

	private FenCompteursBien fenCompBien;
	private DaoCompteur daoCompteur;

	public GestionFenCompteursBien(FenCompteursBien fenCompBien) throws SQLException {
		this.fenCompBien = fenCompBien;
		this.daoCompteur = new DaoCompteur(CictOracleDataSource.getInstance().getConnection());
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenCompBien.getTabMesBiens().getModel();

		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page Compteurs Bien !");
					this.fenCompBien.dispose();
					break;
					
				case "Charger":
					System.out.println("Vous Charger les Compteurs Bien !");
					
					List<Compteur> mesDonnees = null;
					
					try {
						mesDonnees = this.daoCompteur.findComptBien();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
	
					Iterateur<Compteur> it = DaoCompteur.getIterateurCompteur();
					
			        if (it == null) {
			            System.out.println("Itérateur non initialisé !");
			            break;
			        }
					modeleTable.setRowCount(mesDonnees.size());  
					
					int count = 0;
					while(it.hasNext() && count < mesDonnees.size()) {	
						Compteur compteur = it.next();
						this.ecrireLigneTable(compteur, count);
						count++;
					}
	
					break;
									
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
	
	public void ecrireLigneTable(Compteur compteur, int numeroLigne) {
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenCompBien.getTabMesBiens().getModel();

		modeleTable.setValueAt(compteur.getIdCompteur(), numeroLigne, 0);
		modeleTable.setValueAt(compteur.getTypeComp(), numeroLigne, 1);
		modeleTable.setValueAt(compteur.getIndexCompteur(), numeroLigne, 2);
		modeleTable.setValueAt(compteur.getDateRelevé(), numeroLigne, 3);

	}
}
