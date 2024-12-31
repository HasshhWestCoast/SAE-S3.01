package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Modele.assurance;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoAssurance;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutAssurance;


public class GestionFenAssurances implements ActionListener{

	private FenAccueil fenAc;
	private DaoAssurance daoAssurance;
	
	public GestionFenAssurances(FenAccueil fenAc) throws SQLException {
		this.fenAc = fenAc;
		this.daoAssurance = new DaoAssurance(CictOracleDataSource.getInstance().getConnection());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesAssurances().getModel();

		
		if (texte != null) {
			switch (texte) {
				case "Charger":
					System.out.println("Vous CHARGER les données dans Assurance !");
					try {
						List<assurance> mesDonnees = this.daoAssurance.findAll();
		
						Iterateur<assurance> it = DaoAssurance.getIterateurAssurance();
						
				        if (it == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
						modeleTable.setRowCount(mesDonnees.size());  
						
						int count = 0;
						while(it.hasNext() && count < mesDonnees.size()) {	
							assurance ass = it.next();
							this.ecrireLigneTable(ass, count);
							count++;
						}
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
					
				case "Inserer":
					System.out.println("Vous INSERER une donnée dans Assurances !");
					FenAjoutAssurance fenAddAssurance = new FenAjoutAssurance();
					
					int width = fenAc.getLayeredPane().getWidth();
		            int height = fenAc.getLayeredPane().getHeight();
					fenAddAssurance.setBounds(50, 50, width - 100, height - 100);
					
					fenAc.getLayeredPane().add(fenAddAssurance);
					fenAddAssurance.setVisible(true);
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}	
	}
	
	public void ecrireLigneTable(assurance ass, int numeroLigne) {
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesAssurances().getModel();

		modeleTable.setValueAt(ass.getNuméroPolice(), numeroLigne, 0);
		modeleTable.setValueAt(ass.getMontant(), numeroLigne, 1);
		modeleTable.setValueAt(ass.getDateEcheance(), numeroLigne, 2);		
		modeleTable.setValueAt(ass.getEntreprise().getSiret(), numeroLigne, 3);
		modeleTable.setValueAt(ass.getLogement().getIdLogement(), numeroLigne, 4);
	}

}
