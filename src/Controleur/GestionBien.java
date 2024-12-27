package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutBien;
import Vue.Insertion.FenAjoutFacture;

public class GestionBien implements ActionListener{

	private FenAccueil fenAc;
	
	public GestionBien(FenAccueil fenAc) {
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
					//Fen Cn = new Connexion();
					//fenPr.getLayeredPane().add(Cn);
					//Cn.setVisible(true);
					break;
					
				case "Inserer":
					System.out.println("Vous INSERER une donnée dans Bien !");
					FenAjoutBien fenBien = new FenAjoutBien();
					fenAc.getLayeredPane().add(fenBien);
					fenBien.setVisible(true);
					

					
				
					break;
					
				case "Archiver":
					System.out.println("Vous ARCHIVER une donnée prevenant de Facture !");
					//Fen Cn = new Connexion();
					//fenPr.getLayeredPane().add(Cn);
					//Cn.setVisible(true);
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
