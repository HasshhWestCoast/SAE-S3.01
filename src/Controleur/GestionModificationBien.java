package controleur.modification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;


import Modele.Compteur;
import Modele.Immeuble;
import Vue.Fenetre_Accueil;
import Vue.Fenetre_ModificationBien;

public class GestionModificationBien implements ActionListener {

	private Fenetre_ModificationBien modificationBien;
	
	private String idBien;

	public GestionModificationBien(Fenetre_ModificationBien modificationBien) {
		this.modificationBien = modificationBien;
		this.idBien = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		Fenetre_Accueil fenetrePrincipale = (Fenetre_Accueil) this.modificationBien.getTopLevelAncestor();
		switch (btn.getText()) {
		// Action lors du clic sur "Ajouter un compteur"
		case "Ajouter un compteur":
			this.idBien = this.modificationBien.getTextField_IdImmeuble().getText();
			Fenetre_InsertionCompteur fenetreCompteur = new Fenetre_InsertionCompteur();
			// Je crée temporairement le bien pour pouvoir récupérer l'ID quand je vais
			// créer le compteur
			Immeuble immeubleTemporaire = new Immeuble(this.modificationBien.getTextField_IdImmeuble().getText(),
					this.modificationBien.getTextField_adresse().getText(),
					this.modificationBien.getTextField_codePostal().getText(),
					this.modificationBien.getTextField_ville().getText(),
					this.modificationBien.getTextField_periodeDeConstruction().getText(),
					this.modificationBien.getComboBox_typeDeBien().getSelectedItem().toString());
			// J'ajoute l'immeuble dans la sauvegarde pour réutiliser
		
			// On enleve le logement de la sauvegarde pour éviter d'avoir l'id immeuble dans
			// la création du compteur donc constraint check UU
		
			fenetrePrincipale.getLayeredPane().add(fenetreCompteur);
			fenetreCompteur.setVisible(true);
			fenetreCompteur.moveToFront();
			break;
		// Action lors du clic sur "Modifier"
		case "Modifier":
			try {
				Immeuble nouvelImmeuble = new Immeuble(this.modificationBien.getTextField_IdImmeuble().getText(),
						this.modificationBien.getTextField_adresse().getText(),
						this.modificationBien.getTextField_codePostal().getText(),
						this.modificationBien.getTextField_ville().getText(),
						this.modificationBien.getTextField_periodeDeConstruction().getText(),
						this.modificationBien.getComboBox_typeDeBien().getSelectedItem().toString());

			

				this.modificationBien.dispose(); // Fermer la page après la modification

				// Afficher un message de réussite
				JOptionPane.showMessageDialog(this.modificationBien, "Modifé avec succès !", "Succès",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		// Action lors du clic sur "Annuler"
		case "Annuler":
			// Fermeture de la fenêtre de modification sans enregistrement
			this.modificationBien.dispose();
			break;
		default:
			break;
		}
	}

	public String getIdBien() {
		return this.idBien;
	}
}