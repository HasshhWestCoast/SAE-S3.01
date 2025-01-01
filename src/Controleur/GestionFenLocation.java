package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Modele.Louer;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoLouer;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutLocation;


public class GestionFenLocation implements ActionListener {

	private FenAccueil fenAc;
	private DaoLouer daoLouer;
	
	public GestionFenLocation(FenAccueil fenAc) throws SQLException {
		this.fenAc = fenAc;
		this.daoLouer = new DaoLouer(CictOracleDataSource.getInstance().getConnection());

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLocations().getModel();

		
		if (texte != null) {
			switch (texte) {
				case "Charger":
					System.out.println("Vous Charger les donnée dans Location !");
					try {
						List<Louer> mesDonnees = this.daoLouer.findAll();
		
						Iterateur<Louer> it = DaoLouer.getIterateurLouer();
						
				        if (it == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
						modeleTable.setRowCount(mesDonnees.size());  
						
						int count = 0;
						while(it.hasNext() && count < mesDonnees.size()) {	
							Louer louer = it.next();
							this.ecrireLigneTable(louer, count);
							count++;
						}
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
					
				case "Supprimer":
					System.out.println("Vous SUPPRIMER une données dans Location !");
					break;
				
				case "Inserer":
					System.out.println("Vous INSERER une donnée dans Location !");
					
					FenAjoutLocation fenAddLocation = null;
					
					try {
						fenAddLocation = new FenAjoutLocation();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
					
					int width = fenAc.getLayeredPane().getWidth();
		            int height = fenAc.getLayeredPane().getHeight();
		            fenAddLocation.setBounds(50, 50, width - 100, height - 50);
					
					fenAc.getLayeredPane().add(fenAddLocation);
					fenAddLocation.setVisible(true);
					break;
					
				case "Mise à jour":
					System.out.println("Vous METTEZ A JOUR une donnée dans Location !");
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
	
	public void ecrireLigneTable(Louer louer, int numeroLigne) {
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLocations().getModel();

		modeleTable.setValueAt(louer.getLocataire().getIdLocataire(), numeroLigne, 0);
		modeleTable.setValueAt(louer.getBien().getIdBien(), numeroLigne, 1);
		modeleTable.setValueAt(louer.getBien().getTypeBien(), numeroLigne, 2);		
		modeleTable.setValueAt(louer.getDateDebut(), numeroLigne, 3);
		modeleTable.setValueAt(null, numeroLigne, 4);

	}

}
