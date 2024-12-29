package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutFacture;

public class GestionFenFacture implements ActionListener{

	private FenAccueil fenAc;
	
	public GestionFenFacture(FenAccueil fenAc) {
		this.fenAc = fenAc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		if (texte != null) {
			switch (texte) {
				case "Modifier":
					System.out.println("Vous MODIFIER une donnée dans Facture !");
					break;
					
				case "Supprimer":
					System.out.println("Vous SUPPRIMER une données dans Facture !");
					break;
					
				case "Inserer":
					System.out.println("Vous INSERER une donnée dans Facture !");
					FenAjoutFacture fenfac = new FenAjoutFacture();
					fenAc.getLayeredPane().add(fenfac);
					fenfac.setVisible(true);
				
					break;
					
				case "Archiver":
					System.out.println("Vous ARCHIVER une donnée prevenant de Facture !");
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
