package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.tools.DocumentationTool.Location;

import Modele.Facture;
import Modele.Louer;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoFacture;
import Modele.Dao.DaoLouer;
import Modele.Dao.DaoModele;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.FenLocataire;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutLocation;


public class GestionFenLocation implements ActionListener, ListSelectionListener {

	private FenAccueil fenAc;
	private DaoLouer daoLouer;
	private Louer louer;
	private Facture facture;
	private DaoFacture daoFacture;
	
	public GestionFenLocation(FenAccueil fenAc) throws SQLException {
		this.facture = null;
		this.daoFacture = new DaoFacture(CictOracleDataSource.getInstance().getConnection());
		this.louer = null;
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
		            fenAddLocation.setBounds(50, 50, width - 100, height - 15);
					
					fenAc.getLayeredPane().add(fenAddLocation);
					fenAddLocation.setVisible(true);
					break;
					
				case "Mise à jour":
					System.out.println("Vous METTEZ A JOUR une donnée dans Location !");
					break;
					
				case "Informations locataire":
					System.out.println("Vous ouvrez la page informations locataire !");
				FenLocataire fenInfoLocataire = null;
				try {
					fenInfoLocataire = new FenLocataire();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
					fenAc.getLayeredPane().add(fenInfoLocataire);
					fenInfoLocataire.setVisible(true);
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
		modeleTable.setValueAt(louer.getDateDerniereRegularisation(), numeroLigne, 4);

	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		JTable tabLocations = this.fenAc.getTabMesLocations();
		int selectedRow = tabLocations.getSelectedRow();
		
		if (selectedRow > -1) {
			try {
				String IdLocataire = (String) tabLocations.getValueAt(selectedRow, 0);
				String IdBien = (String) tabLocations.getValueAt(selectedRow, 1);
				String DateDebut = (String) tabLocations.getValueAt(selectedRow, 3);
				String DatePaiement = (String) tabLocations.getValueAt(selectedRow, 3);

				this.louer = daoLouer.findById(IdBien, IdLocataire, DateDebut);
				this.facture = daoFacture.findById(DatePaiement);
				
				List<> resultats = find(req, id);
		        return resultats.isEmpty() ? null : resultats.get(0);
		        
				fenAc.settextFieldDatePaiement(String.valueOf());
				fenAc.settextFieldCaution(String.valueOf(louer.getCautionTTC()));
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
	}
}

}
