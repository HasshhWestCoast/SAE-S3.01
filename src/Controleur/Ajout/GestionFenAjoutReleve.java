package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Modele.Releve;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoReleve;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutReleves;

public class GestionFenAjoutReleve implements ActionListener{

	private FenAjoutReleves fenAddReleves;
	
	public GestionFenAjoutReleve(FenAjoutReleves fenAddReleves) throws SQLException {
		this.fenAddReleves = fenAddReleves;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous fermez la page ajout relevé");
					this.fenAddReleves.dispose();
					break;
				
				case "Ajouter":
					System.out.println("Vous AJOUTER un releve dans mes relevé  !");
					

				    try {
						String DateReleve = (String) fenAddReleves.getDateReleves();					
						String IndexString = (String) fenAddReleves.getIndex();
						int Index = Integer.parseInt(IndexString);
						DaoReleve daoReleve = new DaoReleve(CictOracleDataSource.getInstance().getConnection());
						
						Releve releve = new Releve(DateReleve, Index, fenAddReleves.getCompteur());
						daoReleve.create(releve);
												
						fenAddReleves.dispose();
						
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