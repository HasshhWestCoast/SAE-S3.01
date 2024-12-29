package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutEntreprise;
import Vue.Insertion.FenAjoutFacture;

public class GestionFenAjoutFacture implements ActionListener{

	private FenAjoutFacture fenAjoutFacture;
	
	public GestionFenAjoutFacture(FenAjoutFacture fenAjoutFacture) {
		this.fenAjoutFacture = fenAjoutFacture;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		FenAccueil fenAC = (FenAccueil) this.fenAjoutFacture.getTopLevelAncestor();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Facture !");
					this.fenAjoutFacture.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER une Facture !");
					break;
					
				case "Inserer":
					System.out.println("Vous AJOUTER une Entreprise depuis Facture !");
				    FenAjoutEntreprise fenAjoutEntreprise = new FenAjoutEntreprise();
	                fenAC.getLayeredPane().add(fenAjoutEntreprise);
	                fenAjoutEntreprise.setVisible(true);
	                fenAjoutEntreprise.moveToFront();
					break;
					
				case "Charger":
					System.out.println("Vous CHARGEZ les Entreprise depuis Factures !");              
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
