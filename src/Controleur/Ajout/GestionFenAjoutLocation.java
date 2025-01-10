package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Modele.Bien;
import Modele.ICC;
import Modele.Locataire;
import Modele.Louer;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Modele.Dao.DaoICC;
import Modele.Dao.DaoLocataire;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutBien;
import Vue.Insertion.FenAjoutICC;
import Vue.Insertion.FenAjoutLocataire;
import Vue.Insertion.FenAjoutLocation;
import javax.swing.JTable;


public class GestionFenAjoutLocation implements ActionListener, ListSelectionListener{

	private FenAjoutLocation fenAjoutLoc;
	private DaoLocataire daoLocataire;
	private DaoBien daoBien;
	private DaoICC daoICC;
	
	private Locataire locataire;
	private Bien bien;
	private ICC icc;


	
	public GestionFenAjoutLocation(FenAjoutLocation fenAjoutLoc) throws SQLException {
		this.fenAjoutLoc = fenAjoutLoc;
		this.daoLocataire = new DaoLocataire(CictOracleDataSource.getInstance().getConnection());
		this.daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
		this.daoICC = new DaoICC(CictOracleDataSource.getInstance().getConnection());
		this.locataire = null;
		this.bien = null;
		this.icc = null;
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
				    FenAjoutLocataire fenAjoutLocataire = new FenAjoutLocataire(fenAjoutLoc);
	                fenAC.getLayeredPane().add(fenAjoutLocataire);
	                fenAjoutLocataire.setVisible(true);
	                fenAjoutLocataire.moveToFront();
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
				
				case "Ajouter":
					System.out.println("Vous AJOUTER une Location !");
					try {
						DefaultTableModel modeleTable = (DefaultTableModel) fenAC.getTabMesLocations().getModel();

						String DateDebut = (String) fenAjoutLoc.getTextFieldDateDebut();					
						String NbMois = (String) fenAjoutLoc.getTextFieldNbMois();
						String ProvisionChargeString = (String) fenAjoutLoc.getTextFieldProvisionsCharge();
						double ProvisionCharge = Double.parseDouble(ProvisionChargeString);
						String MontantReelString = (String) fenAjoutLoc.getTextFieldMontantReel();
						double MontantReel = Double.parseDouble(MontantReelString);
						String CautionString = (String) fenAjoutLoc.getTextFieldCaution();
						double Caution = Double.parseDouble(CautionString);
						
						Boolean LoyerPayerString = (Boolean) fenAjoutLoc.getcheckLoyerPayer();
						int LoyerPayer;
						if (LoyerPayerString == false) {
							LoyerPayer = 0;
						}else {
							LoyerPayer = 1;
						}
						DaoLocataire daoLocataire = new DaoLocataire(CictOracleDataSource.getInstance().getConnection());
						
						System.out.println("locataire : " + locataire);
						System.out.println("icc :" + icc);
						System.out.println("bien : " + bien);
						Louer louer = new Louer(DateDebut, Integer.parseInt(NbMois), LoyerPayer, ProvisionCharge, Caution, null, null, MontantReel, locataire, icc, bien);
						//daoLouer.create(louer);
						
						String []EngrLocation = {locataire.getNom(), bien.getIdBien(), bien.getTypeBien(), DateDebut, null};
						modeleTable.addRow(EngrLocation);
						
						fenAjoutLoc.dispose();
						
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
	
	@Override
	public void valueChanged(ListSelectionEvent e) {

	    if (!e.getValueIsAdjusting()) {
	        JTable selectedTable = this.fenAjoutLoc.getSelectedTable(e);
	        int selectedRow = selectedTable.getSelectedRow();
	        
			if (selectedRow > -1) {
				try {
					if (selectedTable == this.fenAjoutLoc.getTabMesLocataires()) {
	                    System.out.println("Table Locataires sélectionnée");
	                    locataire = this.daoLocataire.findById(selectedTable.getValueAt(selectedRow, 0).toString());
					}else if (selectedTable == this.fenAjoutLoc.getTabMesBiens()) {
	                    System.out.println("Table Biens sélectionnée");
	                    bien = this.daoBien.findById(selectedTable.getValueAt(selectedRow, 0).toString());
	                }else if (selectedTable == this.fenAjoutLoc.getTabMesICC()) {
	                    System.out.println("Table ICC sélectionnée");
	                    icc = this.daoICC.findById(selectedTable.getValueAt(selectedRow, 0).toString(), selectedTable.getValueAt(selectedRow, 1).toString());
	                }				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}		
			}
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
