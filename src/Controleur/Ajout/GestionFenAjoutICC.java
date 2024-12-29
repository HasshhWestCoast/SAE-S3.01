package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.RoundedButton;
import Vue.Insertion.FenAjoutICC;

public class GestionFenAjoutICC implements ActionListener{

	private FenAjoutICC fenAjoutICC;
	
	public GestionFenAjoutICC(FenAjoutICC fenAjoutICC) {
		this.fenAjoutICC = fenAjoutICC;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		//FenAccueil fenAC = (FenAccueil) this.fenAjoutICC.getTopLevelAncestor();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout ICC !");
					this.fenAjoutICC.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER un ICC !");
					break;
				
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
}
