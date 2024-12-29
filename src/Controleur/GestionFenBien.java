package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Vue.FenAccueil;
import Vue.FenCompteursBien;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutBien;
import Vue.Insertion.FenAjoutFacture;

public class GestionFenBien implements ActionListener{

private FenAccueil fenAc;
	
	public GestionFenBien(FenAccueil fenAc) {
		this.fenAc = fenAc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		if (texte != null) {
			switch (texte) {
				case "Modifier":
					System.out.println("Vous MODIFIER une donnée dans Bien !");
					break;
					
				case "Supprimer":
					System.out.println("Vous SUPPRIMER une données dans Bien !");
					break;
				
				case "Archiver":
					System.out.println("Vous ARCHIVER une donnée prevenant de Bien !");
					break;
					
				case "Afficher les compteurs":
					System.out.println("Vous AFFICHER LES COMPTEURS depuis Bien !");
					FenCompteursBien fenCompMesBien = new FenCompteursBien();
					fenAc.getLayeredPane().add(fenCompMesBien);
					fenCompMesBien.setVisible(true);
					break;
					
				case "Ajouter un bien":
					System.out.println("Vous AJOUTER UN BIEN dans Bien !");
					FenAjoutBien fenAddBien = new FenAjoutBien();
					fenAc.getLayeredPane().add(fenAddBien);
					fenAddBien.setVisible(true);
					break;
					
				case "Ajouter des factures":
					System.out.println("Vous AJOUTER DES FACTURES depuis Bien !");
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
