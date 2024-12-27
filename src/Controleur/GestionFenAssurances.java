package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Vue.FenAccueil;
import Vue.RoundedButton;


public class GestionFenAssurances implements ActionListener{

	private JPanel panel;
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
					System.out.println("Vous charger les données dans table Assurance !");
					
					break;
					
				case "Inserer":
					System.out.println("Vous insérer une nouvelle données dans Assurances !");
					//Fen Cn = new Connexion();
					//fenPr.getLayeredPane().add(Cn);
					//Cn.setVisible(true);
					break;
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
