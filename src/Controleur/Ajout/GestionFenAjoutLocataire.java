package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.RoundedButton;
import Vue.Insertion.FenAjoutLocataire;

public class GestionFenAjoutLocataire implements ActionListener{

	private FenAjoutLocataire fenAjoutLoc;
	
	public GestionFenAjoutLocataire(FenAjoutLocataire fenAjoutLoc) {
		this.fenAjoutLoc = fenAjoutLoc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		//FenAccueil fenAC = (FenAccueil) this.fenAjoutLoc.getTopLevelAncestor();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Locataire !");
					this.fenAjoutLoc.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER une Locataire !");
					break;
				
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
}
