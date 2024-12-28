package ControleurAjouter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutAssurance;
import Vue.Insertion.FenAjoutBien;
import Vue.Insertion.FenAjoutCompteur;
import Vue.Insertion.FenAjoutLogement;


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
					System.out.println("Vous fermez la page ajout bien");
					this.fenAjoutBien.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous ajouter un bien !");
					break;
					
				case "Ajout Compteur":
					System.out.println("Vous ouvrer la page ajout compteur");
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
