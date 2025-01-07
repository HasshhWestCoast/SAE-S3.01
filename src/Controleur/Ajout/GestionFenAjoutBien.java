package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Modele.Bien;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutBien;
import Vue.Insertion.FenAjoutCompteur;
import Vue.Insertion.FenAjoutFacture;

public class GestionFenAjoutBien implements ActionListener{

	private FenAjoutBien fenAjoutBien;
	
	
	public GestionFenAjoutBien(FenAjoutBien fenAjoutBien) {
		this.fenAjoutBien = fenAjoutBien;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		FenAccueil fenAC = (FenAccueil) this.fenAjoutBien.getTopLevelAncestor();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout bien");
					this.fenAjoutBien.dispose();
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

						Bien bien = new Bien(IdBien, Adresse, Ville, TypeBien, CodePostal, PeriodeConstruction, null);
						//daoBien.create(crn);
						
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
				case "Ajouter des factures":
					System.out.println("Vous ajouter des factures au logements");
					
					FenAjoutFacture fenAjoutFacture = null;
					
					try {
						fenAjoutFacture = new FenAjoutFacture();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
	                
	                fenAC.getLayeredPane().add(fenAjoutFacture);
	                fenAjoutFacture.setVisible(true);
	                fenAjoutFacture.moveToFront();
	               
					
					break;
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
