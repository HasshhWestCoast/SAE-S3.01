package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutAssurance;


public class GestionFenAssurances implements ActionListener{

	private FenAccueil fenAc;
	
	public GestionFenAssurances(FenAccueil fenAc) {
		this.fenAc = fenAc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		if (texte != null) {
			switch (texte) {
				case "Charger":
					System.out.println("Vous CHARGER les données dans Assurance !");
					break;
					
				case "Inserer":
					System.out.println("Vous INSERER une donnée dans Assurances !");
					FenAjoutAssurance fenAddAssurance = new FenAjoutAssurance();
					
					int width = fenAc.getLayeredPane().getWidth();
		            int height = fenAc.getLayeredPane().getHeight();
					fenAddAssurance.setBounds(50, 50, width - 100, height - 100);
					
					fenAc.getLayeredPane().add(fenAddAssurance);
					fenAddAssurance.setVisible(true);
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
