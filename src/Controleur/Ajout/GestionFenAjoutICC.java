package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Modele.ICC;
import Modele.Locataire;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoICC;
import Modele.Dao.DaoLocataire;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutICC;

public class GestionFenAjoutICC implements ActionListener{

	private FenAjoutICC fenAjoutICC;
	
	public GestionFenAjoutICC(FenAjoutICC fenAjoutICC) {
		this.fenAjoutICC = fenAjoutICC;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		DefaultTableModel modeleTableIcc = (DefaultTableModel) fenAjoutICC.getFenAjoutLocation().getTabMesICC().getModel();
		
		if (texte != null) {
			switch (texte) {
				
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout ICC !");
					this.fenAjoutICC.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER un ICC !");
					
					try {
						String iccString = (String) fenAjoutICC.getIDIcc();
						int Idicc = Integer.parseInt(iccString);
						String annee = (String) fenAjoutICC.getAnnee();					
						String trimestre = (String) fenAjoutICC.getTrimestre();
						String indiceString = (String) fenAjoutICC.getIndice();
						double indice = Double.parseDouble(indiceString);

						DaoICC daoICC = new DaoICC(CictOracleDataSource.getInstance().getConnection());
						
						ICC icc = new ICC(Idicc, annee, trimestre, indice);
						//daoICC.create(icc);
						
						String []EngrICC = {iccString, annee, trimestre, indiceString};
						modeleTableIcc.addRow(EngrICC);
						
						fenAjoutICC.dispose();
						
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
