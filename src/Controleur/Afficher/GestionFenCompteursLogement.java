package Controleur.Afficher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenCompteursLogement;
import Vue.RoundedButton;

public class GestionFenCompteursLogement implements ActionListener{
	
	private FenCompteursLogement fenCompLogement;
	
	public GestionFenCompteursLogement(FenCompteursLogement fenCompLogement) {
		this.fenCompLogement = fenCompLogement;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		//FenAccueil fenAC = (FenAccueil) this.fenCompLogement.getTopLevelAncestor();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page Compteurs Logement !");
					this.fenCompLogement.dispose();
					break;
					
				case "Ajouter un relevé":
					System.out.println("Vous AJOUTER UN RELEVE à un  Compteurs Logement !");
					break;
					
				case "Afficher les relevés":
					System.out.println("Vous AFFICHER LES RELEVES d'un Compteur Logement !");
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
