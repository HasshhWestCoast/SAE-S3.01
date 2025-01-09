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
						//DefaultTableModel modeleTable = (DefaultTableModel) fenAjoutCompteur.getTab().getModel();

						String IdCompteur = (String) fenAjoutCompteur.getTextFieldIdCompteur();					
						String IndiceCompteurString = (String) fenAjoutCompteur.getTextFieldIndiceCompteur();
						int IndiceCompteur = Integer.parseInt(IndiceCompteurString);
						String DateRelevé = (String) fenAjoutCompteur.getTextFieldDateRelevé();
						String TypeCompteur = (String) fenAjoutCompteur.getComboBoxTypeComp();

						DaoBien daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());

						//Compteur compteur = new Compteur(IdCompteur, TypeCompteur, IndiceCompteur, DateRelevé, bien, immeuble);
						//daoBien.create(bien);
						
						String []EngrCompteur = {IdCompteur, TypeCompteur, String.valueOf(IndiceCompteur), DateRelevé};
						//modeleTable.addRow(EngrCompteur);
						
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
