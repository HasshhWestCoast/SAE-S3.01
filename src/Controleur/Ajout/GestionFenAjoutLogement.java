package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutCompteur;
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
		
		FenAccueil fenAC = (FenAccueil) this.fenAjoutLogement.getTopLevelAncestor();
		
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout logement");
					this.fenAjoutLogement.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER un logement !");
					break;
					
				case "Ajouter un compteur":
					System.out.println("Vous OUVREZ la page ajout compteur");
				     FenAjoutCompteur fenAjoutCompteur = new FenAjoutCompteur();
					
	                
	                fenAC.getLayeredPane().add(fenAjoutCompteur);
	                fenAjoutCompteur.setVisible(true);
	                fenAjoutCompteur.moveToFront();
	               
					
					break;
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
