package Controleur.Afficher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenAccueil;
import Vue.FenCompteursBien;
import Vue.RoundedButton;

public class GestionFenCompteursBien implements ActionListener{

	private FenCompteursBien fenCompBien;
	
	public GestionFenCompteursBien(FenCompteursBien fenCompBien) {
		this.fenCompBien = fenCompBien;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		FenAccueil fenAC = (FenAccueil) this.fenCompBien.getTopLevelAncestor();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page Compteurs Bien !");
					this.fenCompBien.dispose();
					break;
					
				case "Ajouter un relevé":
					System.out.println("Vous AJOUTER UN RELEVE à un  Compteurs Bien !");
					break;
					
				case "Afficher les relevés":
					System.out.println("Vous AFFICHER LES RELEVES d'un Compteur Bien !");
				    //FenAjoutCompteur fenAjoutCompteur = new FenAjoutCompteur();
					
	                //fenAC.getLayeredPane().add(fenAjoutCompteur);
	                //fenAjoutCompteur.setVisible(true);
	                //fenAjoutCompteur.moveToFront();
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
}
