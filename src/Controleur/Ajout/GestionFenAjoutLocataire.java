package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Modele.Locataire;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoLocataire;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutLocataire;

public class GestionFenAjoutLocataire implements ActionListener{

	private FenAjoutLocataire fenAjoutLoc;
	
	public GestionFenAjoutLocataire(FenAjoutLocataire fenAjoutLoc) {
		this.fenAjoutLoc = fenAjoutLoc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		DefaultTableModel modeleTableLocataire = (DefaultTableModel) fenAjoutLoc.getFenAjoutLocation().getTabMesLocataires().getModel();
		
		if (texte != null) {
			switch (texte) {
			
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Locataire !");
					this.fenAjoutLoc.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER une Locataire !");

					try {
						String IdLocataire = (String) fenAjoutLoc.getIDLocataire();					
						String Nom = (String) fenAjoutLoc.getNom();
						String Prenom = (String) fenAjoutLoc.getPrenom();
						String Telephone = (String) fenAjoutLoc.getTelephone();
						String Mail = (String) fenAjoutLoc.getMail();
						String DateDeNaissance = (String) fenAjoutLoc.getDateNaissance();
						boolean ColocataireString = (boolean) fenAjoutLoc.getcheckLoyerPayer();
						int colocataire;
						if (ColocataireString == false) {
							colocataire = 0;
						}else {
							colocataire = 1;
						}

						DaoLocataire daoLocataire = new DaoLocataire(CictOracleDataSource.getInstance().getConnection());
						
						Locataire locataire = new Locataire(IdLocataire, Nom, Prenom, Telephone, Mail, DateDeNaissance, colocataire);
						//daoLocataire.create(locataire);
						
						String []EngrLocataire = {IdLocataire, Nom};
						modeleTableLocataire.addRow(EngrLocataire);
						
						fenAjoutLoc.dispose();
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}		
					break;
				
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
}
