package Controleur.Afficher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Modele.Compteur;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoCompteur;
import Modele.Dao.Iterateur;
import Vue.FenCompteursBien;
import Vue.FenLocataire;
import Vue.RoundedButton;

public class GestionFenLocataire implements ActionListener{

	private FenLocataire fenLocataire;


	public GestionFenLocataire(FenLocataire fenLocataire) throws SQLException {
		this.fenLocataire = fenLocataire;
	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page Locataire !");
					this.fenLocataire.dispose();
					break;	
				case "Modifier":
					System.out.println("Vous Modifier les donner d'un locataire !");
					break;						
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
	
	
}
