package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Modele.Bien;
import Modele.Compteur;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutCompteur;


public class GestionFenAjoutCompteur implements ActionListener{

	private FenAjoutCompteur fenAjoutCompteur;
	
	
	public GestionFenAjoutCompteur(FenAjoutCompteur fenAjoutCompteur) {
		this.fenAjoutCompteur = fenAjoutCompteur;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout compteur");
					this.fenAjoutCompteur.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER un compteur au bien !");
					try {
						DefaultTableModel modeleTable = (DefaultTableModel) fenAjoutCompteur.getTabMesBiens().getModel();

						String IdBien = (String) FenAjoutCompteur.getTextFieldIdBien();					
						String Adresse = (String) FenAjoutCompteur.getTextFieldAdresse();
						String Ville = (String) FenAjoutCompteur.getTextFieldVille();
						
						DaoBien daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());

						Compteur compteur = new Compteur(IdBien, Adresse, Ville, TypeBien, CodePostal, PeriodeConstruction, logement);
						//daoBien.create(bien);
						
						String []EngrCompteur = {IdBien, Adresse, Ville, CodePostal};
						modeleTable.addRow(EngrCompteur);
						
						fenAjoutCompteur.dispose();
						
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

}
