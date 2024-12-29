package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutBien;
import Vue.Insertion.FenAjoutCompteur;
import Vue.Insertion.FenAjoutFacture;

public class GestionFenAjoutBien implements ActionListener{

	private FenAjoutBien fenAjoutBien;
	
	
	public GestionFenAjoutBien(FenAjoutBien fenAjoutBien) {
		this.fenAjoutBien = fenAjoutBien;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		FenAccueil fenAC = (FenAccueil) this.fenAjoutBien.getTopLevelAncestor();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout bien");
					this.fenAjoutBien.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER un bien !");
					break;
					
				case "Ajout Compteur":
					System.out.println("Vous OUVREZ la page ajout compteur");
				     FenAjoutCompteur fenAjoutCompteur = new FenAjoutCompteur();
					
	                
	                fenAC.getLayeredPane().add(fenAjoutCompteur);
	                fenAjoutCompteur.setVisible(true);
	                fenAjoutCompteur.moveToFront();
	               
					break;
				case "Ajouter des factures":
					System.out.println("Vous ajouter des factures au logements");
					  FenAjoutFacture fenAjoutFacture = new FenAjoutFacture();
	                
	                fenAC.getLayeredPane().add(fenAjoutFacture);
	                fenAjoutFacture.setVisible(true);
	                fenAjoutFacture.moveToFront();
	               
					
					break;
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
