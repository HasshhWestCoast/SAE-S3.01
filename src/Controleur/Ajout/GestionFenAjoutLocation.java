package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Modele.Bien;
import Modele.ICC;
import Modele.Locataire;
import Modele.Louer;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Modele.Dao.DaoICC;
import Modele.Dao.DaoLocataire;
import Modele.Dao.DaoLouer;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutBien;
import Vue.Insertion.FenAjoutICC;
import Vue.Insertion.FenAjoutLocataire;
import Vue.Insertion.FenAjoutLocation;

public class GestionFenAjoutLocation implements ActionListener{

	private FenAjoutLocation fenAjoutLoc;
	private DaoLocataire daoLocataire;
	private DaoBien daoBien;
	private DaoICC daoICC;


	
	public GestionFenAjoutLocation(FenAjoutLocation fenAjoutLoc) throws SQLException {
		this.fenAjoutLoc = fenAjoutLoc;
		this.daoLocataire = new DaoLocataire(CictOracleDataSource.getInstance().getConnection());
		this.daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
		this.daoICC = new DaoICC(CictOracleDataSource.getInstance().getConnection());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		FenAccueil fenAC = (FenAccueil) this.fenAjoutLoc.getTopLevelAncestor();
		DefaultTableModel modeleTableLocataire = (DefaultTableModel) this.fenAjoutLoc.getTabMesLocataires().getModel();
		DefaultTableModel modeleTableBien = (DefaultTableModel) this.fenAjoutLoc.getTabMesBiens().getModel();
		DefaultTableModel modeleTableICC = (DefaultTableModel) this.fenAjoutLoc.getTabMesICC().getModel();

		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Location !");
					this.fenAjoutLoc.dispose();
					break;
				
				case "Ajouter":
					System.out.println("Vous AJOUTER une Location !");
					try {
						DefaultTableModel modeleTable = (DefaultTableModel) fenAC.getTabMesLocations().getModel();

						String DateDebut = (String) fenAjoutLoc.getTextFieldDateDebut();					
						String NbMois = (String) fenAjoutLoc.getTextFieldNbMois();
						String ProvisionCharge = (String) fenAjoutLoc.getTextFieldProvisionsCharge();
						String MontantReel = (String) fenAjoutLoc.getTextFieldMontantReel();
						String Caution = (String) fenAjoutLoc.getTextFieldCaution();
						
						DaoLouer daoLouer = new DaoLouer(CictOracleDataSource.getInstance().getConnection());

						Louer louer = new Louer(DateDebut, Integer.parseInt(NbMois), ProvisionCharge, MontantReel, Caution, PeriodeConstruction, null);
						//daoLouer.create(louer);
						
						String []EngrLocation = {IdBien, Adresse, Ville, CodePostal, TypeBien, PeriodeConstruction};
						modeleTable.addRow(EngrLocation);
						
						fenAjoutLoc.dispose();
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}			
					break;
				

				case "Charger":
					System.out.println("Vous CHARGEZ BIENS depuis Location !");
					List<Bien> mesDonneesBien = null;
					
					try {
						mesDonneesBien = this.daoBien.findAll();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
					
					Iterateur<Bien> itBien = DaoBien.getIterateurBien();
					
			        if (itBien == null) {
			            System.out.println("Itérateur non initialisé !");
			            break;
			        }
			        modeleTableBien.setRowCount(mesDonneesBien.size());  
					
					int countBien = 0;
					while(itBien.hasNext() && countBien < mesDonneesBien.size()) {	
						Bien bien = itBien.next();
						this.ecrireLigneTableBien(bien, countBien);
						countBien++;
					}
					
					List<ICC> mesDonneesICC = null;
					
					try {
						mesDonneesICC = this.daoICC.findAll();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
					
					Iterateur<ICC> itICC = DaoICC.getIterateurICC();
					
			        if (itICC == null) {
			            System.out.println("Itérateur non initialisé !");
			            break;
			        }
			        modeleTableICC.setRowCount(mesDonneesICC.size());  
					
					int countICC = 0;
					while(itICC.hasNext() && countICC < mesDonneesICC.size()) {	
						ICC icc = itICC.next();
						this.ecrireLigneTableICC(icc, countICC);
						countICC++;
					}
					
					List<Locataire> mesDonneesLoc = null;
					
					try {
						mesDonneesLoc = this.daoLocataire.findAll();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
					
					Iterateur<Locataire> it = DaoLocataire.getIterateurLocataire();
					
			        if (it == null) {
			            System.out.println("Itérateur non initialisé !");
			            break;
			        }
			        modeleTableLocataire.setRowCount(mesDonneesLoc.size());  
					
					int count = 0;
					while(it.hasNext() && count < mesDonneesLoc.size()) {	
						Locataire locataire = it.next();
						this.ecrireLigneTableLoc(locataire, count);
						count++;
					}
					break;
										
				case "Inserer L":
					System.out.println("Vous INSERER un LOCATAIRE depuis Location !");
				    FenAjoutLocataire fenAjoutLocataire = new FenAjoutLocataire();
	                fenAC.getLayeredPane().add(fenAjoutLocataire);
	                fenAjoutLocataire.setVisible(true);
	                fenAjoutLocataire.moveToFront();
					break;
				
				case "Inserer Bien":
					System.out.println("Vous INSERER un BIEN depuis Location !");
					FenAjoutBien fenAddBien = new FenAjoutBien();
					fenAC.getLayeredPane().add(fenAddBien);
					fenAddBien.setVisible(true);
					fenAddBien.moveToFront();
					break;
					
				case "Inserer ICC":
					System.out.println("Vous INSERER un ICC depuis Location !");
					FenAjoutICC fenAddICC = new FenAjoutICC();
					fenAC.getLayeredPane().add(fenAddICC);
					fenAddICC.setVisible(true);
					fenAddICC.moveToFront();
					break;
					
				case "Ajouter Lieux":
					System.out.println("Vous AJOUTER LIEUX pour une Location !");
					break;
					
				case "Ajouter Etat Lieux":
					System.out.println("Vous AJOUTER ETAT LIEUX pour une Location !");
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
	
	public void ecrireLigneTableLoc(Locataire locataire, int numeroLigne) {
		DefaultTableModel modeleTableLocataire = (DefaultTableModel) this.fenAjoutLoc.getTabMesLocataires().getModel();

		modeleTableLocataire.setValueAt(locataire.getIdLocataire(), numeroLigne, 0);
		modeleTableLocataire.setValueAt(locataire.getNom(), numeroLigne, 1);
	}
	
	public void ecrireLigneTableBien(Bien bien, int numeroLigne) {
		DefaultTableModel modeleTableBien = (DefaultTableModel) this.fenAjoutLoc.getTabMesBiens().getModel();

		modeleTableBien.setValueAt(bien.getIdBien(), numeroLigne, 0);
	}
	
	public void ecrireLigneTableICC(ICC icc, int numeroLigne) {
		DefaultTableModel modeleTableICC = (DefaultTableModel) this.fenAjoutLoc.getTabMesICC().getModel();

		modeleTableICC.setValueAt(icc.getAnnee(), numeroLigne, 0);
		modeleTableICC.setValueAt(icc.getTrimestre(), numeroLigne, 1);
		modeleTableICC.setValueAt(icc.getIndice(), numeroLigne, 2);

	}


}
