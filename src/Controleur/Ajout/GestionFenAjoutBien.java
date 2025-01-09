package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Modele.Bien;
import Modele.Logement;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Modele.Dao.DaoLogement;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutBien;
import Vue.Insertion.FenAjoutCompteur;

public class GestionFenAjoutBien implements ActionListener, ListSelectionListener{

	private FenAjoutBien fenAjoutBien;
	private Logement logement;
	
	public GestionFenAjoutBien(FenAjoutBien fenAjoutBien) {
		this.fenAjoutBien = fenAjoutBien;
		this.logement = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		FenAccueil fenAC = (FenAccueil) this.fenAjoutBien.getTopLevelAncestor();
		DefaultTableModel modeleTableLogement = (DefaultTableModel) this.fenAjoutBien.getTabMesLogements().getModel();

		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Bien");
					this.fenAjoutBien.dispose();
					break;
				
				case "Charger":
					System.out.println("Vous CHARGER les Logements depuis Bien !");
					try {
						DaoLogement daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
								
						List<Logement> mesDonnees = daoLogement.findAll();
		
						Iterateur<Logement> itL = DaoLogement.getIterateurLogement();
						
				        if (itL == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
				        modeleTableLogement.setRowCount(mesDonnees.size());  
						
						int countL = 0;
						while(itL.hasNext() && countL < mesDonnees.size()) {	
							Logement logement = itL.next();
							this.ecrireLigneTableLogement(logement, countL);
							countL++;
						}
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER un bien !");
					try {
						DefaultTableModel modeleTable = (DefaultTableModel) fenAC.getTabMesBiens().getModel();

						String IdBien = (String) fenAjoutBien.getTextFieldIdBien();					
						String Adresse = (String) fenAjoutBien.getTextFieldAdresse();
						String Ville = (String) fenAjoutBien.getTextFieldVille();
						String TypeBien = (String) fenAjoutBien.getComboBoxTypeDeBien();
						String CodePostal = (String) fenAjoutBien.getTextFieldCodePostale();
						String PeriodeConstruction = (String) fenAjoutBien.getTextFieldPeriodeConstruction();
						
						DaoBien daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());

						System.out.println("logement : " + logement);
						Bien bien = new Bien(IdBien, Adresse, Ville, TypeBien, CodePostal, PeriodeConstruction, logement);
						//daoBien.create(bien);
						
						String []EngrBien = {IdBien, Adresse, Ville, CodePostal, TypeBien, PeriodeConstruction};
						modeleTable.addRow(EngrBien);
						
						fenAjoutBien.dispose();
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}			
					break;
					
				case "Ajout Compteur":
					System.out.println("Vous OUVREZ la page ajout compteur");
				    
					FenAjoutCompteur fenAjoutCompteur = new FenAjoutCompteur();
					fenAC.getLayeredPane().add(fenAjoutCompteur);
	                fenAjoutCompteur.setVisible(true);
	                fenAjoutCompteur.moveToFront();
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
		
		JTable tabEntreprise = this.fenAjoutBien.getTabMesLogements();
		
		int selectedRow = tabEntreprise.getSelectedRow();
		if (selectedRow > -1) {
			try {
				DaoLogement daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
				this.logement = daoLogement.findById(tabEntreprise.getValueAt(selectedRow, 0).toString());
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}
	
	public void ecrireLigneTableLogement(Logement logement, int numeroLigne) {
		DefaultTableModel modeleTableLogement = (DefaultTableModel) this.fenAjoutBien.getTabMesLogements().getModel();

		modeleTableLogement.setValueAt(logement.getIdLogement(), numeroLigne, 0);
		modeleTableLogement.setValueAt(logement.getDateAcquisition(), numeroLigne, 1);
	}

}
