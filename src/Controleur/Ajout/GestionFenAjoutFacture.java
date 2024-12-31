package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Modele.Entreprise;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoEntreprise;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutEntreprise;
import Vue.Insertion.FenAjoutFacture;

public class GestionFenAjoutFacture implements ActionListener{

	private FenAjoutFacture fenAjoutFacture;
	private DaoEntreprise daoEntreprise;
	
	public GestionFenAjoutFacture(FenAjoutFacture fenAjoutFacture) throws SQLException {
		this.fenAjoutFacture = fenAjoutFacture;
		this.daoEntreprise = new DaoEntreprise(CictOracleDataSource.getInstance().getConnection());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAjoutFacture.getTabMesEntreprise().getModel();


		FenAccueil fenAC = (FenAccueil) this.fenAjoutFacture.getTopLevelAncestor();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Facture !");
					this.fenAjoutFacture.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER une Facture !");
					break;
					
				case "Inserer":
					System.out.println("Vous AJOUTER une Entreprise depuis Facture !");
				    FenAjoutEntreprise fenAjoutEntreprise = new FenAjoutEntreprise();
	                fenAC.getLayeredPane().add(fenAjoutEntreprise);
	                fenAjoutEntreprise.setVisible(true);
	                fenAjoutEntreprise.moveToFront();
					break;
					
				case "Charger":
					System.out.println("Vous CHARGEZ les Entreprise depuis Factures !");
					try {
						List<Entreprise> mesDonnees = this.daoEntreprise.findAll();
		
						Iterateur<Entreprise> it = DaoEntreprise.getIterateurEntreprise();
						
				        if (it == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
						modeleTable.setRowCount(mesDonnees.size());  
						
						int count = 0;
						while(it.hasNext() && count < mesDonnees.size()) {	
							Entreprise entreprise = it.next();
							this.ecrireLigneTable(entreprise, count);
							count++;
						}
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
	
	public void ecrireLigneTable(Entreprise entreprise, int numeroLigne) {
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAjoutFacture.getTabMesEntreprise().getModel();

		modeleTable.setValueAt(entreprise.getSiret(), numeroLigne, 0);
		modeleTable.setValueAt(entreprise.getNom(), numeroLigne, 1);
	}


}
