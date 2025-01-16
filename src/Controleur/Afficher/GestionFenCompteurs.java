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
import Vue.FenCompteurs;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutCompteur;

public class GestionFenCompteurs implements ActionListener{
	
	private FenCompteurs fenComp;
	private DaoCompteur daoCompteur;

	public GestionFenCompteurs(FenCompteurs fenComp) throws SQLException {
		this.fenComp = fenComp;
		this.daoCompteur = new DaoCompteur(CictOracleDataSource.getInstance().getConnection());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page Compteurs !");
					this.fenComp.dispose();
					break;
				
				case "Afficher date relevé":
					System.out.println("Vous Afficher les relevé d'un compteur !");
					break;
				
				case "Ajouter":
					System.out.println("Vous charger la page ajouter un Compteur !");
					try {
						FenAjoutCompteur fenaddComp = null;	
						
						fenaddComp = new FenAjoutCompteur();
						
						fenComp.getLayeredPane().add(fenaddComp);					
						fenaddComp.setVisible(true);
					}catch (Exception e2) {
						e2.printStackTrace();
					}	
					break;
				
				case "Charger":
					DefaultTableModel modeleTableCompteur = (DefaultTableModel) fenComp.gettabMesCompteurs().getModel();	
					List<Compteur> mesDonnees = null;
					
					try {
						mesDonnees = this.daoCompteur.findAll();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
	
					Iterateur<Compteur> it = DaoCompteur.getIterateurCompteur();					
			        if (it == null) {
			            System.out.println("Itérateur non initialisé !");
			            break;
			        }
			        
					modeleTableCompteur.setRowCount(mesDonnees.size());  	
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
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenComp.gettabMesCompteurs().getModel();

		modeleTable.setValueAt(compteur.getIdCompteur(), numeroLigne, 0);
		modeleTable.setValueAt(compteur.getTypeComp(), numeroLigne, 1);
		modeleTable.setValueAt(compteur.getImmeuble().getIdLogement(), numeroLigne, 2);
		modeleTable.setValueAt(compteur.getBien().getIdBien(), numeroLigne, 3);
	}
}
