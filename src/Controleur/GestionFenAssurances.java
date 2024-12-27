package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutAssurance;


public class GestionFenAssurances implements ActionListener{

	private FenAccueil fenAc;
	
	public GestionFenAssurances(FenAccueil fenAc) {
		this.fenAc = fenAc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		if (texte != null) {
			switch (texte) {
				case "Charger":
					System.out.println("Vous CHARGER les données dans Assurance !");
					break;
					
				case "Inserer":
					System.out.println("Vous INSERER une donnée dans Assurances !");
					FenAjoutAssurance fenAddAssurance = new FenAjoutAssurance();
					fenAc.getLayeredPane().add(fenAddAssurance);
					fenAddAssurance.setVisible(true);
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
