package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenAccueil;
import Vue.FenCompteursLogement;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutFacture;
import Vue.Insertion.FenAjoutLogement;


public class GestionFenLogements implements ActionListener{

	private FenAccueil fenAc;
	
	public GestionFenLogements(FenAccueil fenAc) {
		this.fenAc = fenAc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		if (texte != null) {
			switch (texte) {
				case "Modifier":
					System.out.println("Vous MODIFIER une donnée dans Logement !");
					break;
					
				case "Supprimer":
					System.out.println("Vous SUPPRIMER une données dans Logement !");
					break;
				
				case "Archiver":
					System.out.println("Vous ARCHIVER une donnée prevenant de Logement !");
					break;
					
				case "Ajouter un diagnostic":
					System.out.println("Vous AJOUTER UN DIAGNOSTIC depuis Logement !");
					break;
					
				case "Afficher les compteurs":
					System.out.println("Vous AFFICHER LES COMPTEURS depuis Logement !");
					FenCompteursLogement fenCompMesLogements = new FenCompteursLogement();
					fenAc.getLayeredPane().add(fenCompMesLogements);
					fenCompMesLogements.setVisible(true);
					break;
					
				case "Ajouter un logement":
					System.out.println("Vous AJOUTER UN LOGEMENT depuis Logement !");
					FenAjoutLogement fenAddLogements = new FenAjoutLogement();
					fenAc.getLayeredPane().add(fenAddLogements);
					fenAddLogements.setVisible(true);
					break;
					
				case "Ajouter des factures":
					System.out.println("Vous AJOUTER DES FACTURES depuis Logement !");
					FenAjoutFacture fenAddFacture = new FenAjoutFacture();
					fenAc.getLayeredPane().add(fenAddFacture);
					fenAddFacture.setVisible(true);
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
