package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutLocation;


public class GestionFenLocation implements ActionListener {

	private FenAccueil fenAc;
	
	public GestionFenLocation(FenAccueil fenAc) {
		this.fenAc = fenAc;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		if (texte != null) {
			switch (texte) {
				case "Charger":
					System.out.println("Vous Charger les donnée dans Location !");
					break;
					
				case "Supprimer":
					System.out.println("Vous SUPPRIMER une données dans Location !");
					break;
				
				case "Inserer":
					System.out.println("Vous INSERER une donnée dans Location !");
					FenAjoutLocation fenAddLocation = new FenAjoutLocation();
					
					int width = fenAc.getLayeredPane().getWidth();
		            int height = fenAc.getLayeredPane().getHeight();
		            fenAddLocation.setBounds(50, 50, width - 100, height - 50);
					
					fenAc.getLayeredPane().add(fenAddLocation);
					fenAddLocation.setVisible(true);
					break;
					
				case "Mise à jour":
					System.out.println("Vous METTEZ A JOUR une donnée dans Location !");
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
