package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutAssurance;
import Vue.Insertion.FenAjoutEntreprise;
import Vue.Insertion.FenAjoutLogement;

public class GestionFenAjoutAssurance implements ActionListener{

	private FenAjoutAssurance fenAjoutAssurance;
	
	public GestionFenAjoutAssurance(FenAjoutAssurance fenAjoutAssurance) {
		this.fenAjoutAssurance = fenAjoutAssurance;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		FenAccueil fenAC = (FenAccueil) this.fenAjoutAssurance.getTopLevelAncestor();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Assurance !");
					this.fenAjoutAssurance.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER une donnée à Assurance !");
					break;
					
				case "Inserer L":
					System.out.println("Vous AJOUTER un Logement depuis Assurance !");
					FenAjoutLogement fenAddLog = new FenAjoutLogement();
	                fenAC.getLayeredPane().add(fenAddLog);
	                fenAddLog.setVisible(true);
	                fenAddLog.moveToFront();
					break;
					
				case "Inserer E":
					System.out.println("Vous AJOUTER une Entreprise depuis Assurance !");
					FenAjoutEntreprise fenAjoutEntreprise = new FenAjoutEntreprise();
	                fenAC.getLayeredPane().add(fenAjoutEntreprise);
	                fenAjoutEntreprise.setVisible(true);
	                fenAjoutEntreprise.moveToFront();
					break;
				
				case "Charger L":
					System.out.println("Vous CHARGER les Logements depuis Assurance !");
					break;
					
				case "Charger E":
					System.out.println("Vous CHARGER les ENTREPRISE depuis Assurance !");
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
