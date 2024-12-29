package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
