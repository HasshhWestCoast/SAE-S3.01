package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutBien;
import Vue.Insertion.FenAjoutICC;
import Vue.Insertion.FenAjoutLocataire;
import Vue.Insertion.FenAjoutLocation;

public class GestionFenAjoutLocation implements ActionListener{

	private FenAjoutLocation fenAjoutLoc;
	
	public GestionFenAjoutLocation(FenAjoutLocation fenAjoutLoc) {
		this.fenAjoutLoc = fenAjoutLoc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		FenAccueil fenAC = (FenAccueil) this.fenAjoutLoc.getTopLevelAncestor();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Location !");
					this.fenAjoutLoc.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER une Location !");
					break;
					
				case "Charger L":
					System.out.println("Vous CHARGEZ LOCATAIRES depuis Location !");              
					break;
					
				case "Charger Bien":
					System.out.println("Vous CHARGEZ BIENS depuis Location !");              
					break;
					
				case "Charger ICC":
					System.out.println("Vous CHARGEZ ICC depuis Location !");              
					break;
					
				case "Inserer L":
					System.out.println("Vous INSERER un LOCATAIRE depuis Location !");
				    FenAjoutLocataire fenAjoutLocataire = new FenAjoutLocataire();
	                fenAC.getLayeredPane().add(fenAjoutLocataire);
	                fenAjoutLocataire.setVisible(true);
	                fenAjoutLocataire.moveToFront();
					break;
				
				case "Inserer Bien":
					System.out.println("Vous INSERER un BIEN depuis Location !");
					FenAjoutBien fenAddBien = new FenAjoutBien();
					fenAC.getLayeredPane().add(fenAddBien);
					fenAddBien.setVisible(true);
					fenAddBien.moveToFront();
					break;
					
				case "Inserer ICC":
					System.out.println("Vous INSERER un ICC depuis Location !");
					FenAjoutICC fenAddICC = new FenAjoutICC();
					fenAC.getLayeredPane().add(fenAddICC);
					fenAddICC.setVisible(true);
					fenAddICC.moveToFront();
					break;
					
				case "Ajouter Lieux":
					System.out.println("Vous AJOUTER LIEUX pour une Location !");
					break;
					
				case "Ajouter Etat Lieux":
					System.out.println("Vous AJOUTER ETAT LIEUX pour une Location !");
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
}
