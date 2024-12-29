package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutAssurance;
import Vue.Insertion.FenAjoutLogement;


public class GestionFenAjoutLogement implements ActionListener{

	private FenAjoutLogement fenAjoutLogement;
	
	public GestionFenAjoutLogement(FenAjoutLogement fenAjoutLogement) {
		this.fenAjoutLogement = fenAjoutLogement;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout logement");
					this.fenAjoutLogement.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER un logement !");
					break;
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
