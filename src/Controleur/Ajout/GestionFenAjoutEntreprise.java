package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.RoundedButton;
import Vue.Insertion.FenAjoutEntreprise;

public class GestionFenAjoutEntreprise implements ActionListener{

	private FenAjoutEntreprise fenAjoutEntreprise;
	
	public GestionFenAjoutEntreprise(FenAjoutEntreprise fenEntreprise) {
		this.fenAjoutEntreprise = fenEntreprise;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Entreprise !");
					this.fenAjoutEntreprise.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER une Entreprise !");
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
}
