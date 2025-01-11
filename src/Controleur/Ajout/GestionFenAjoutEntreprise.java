package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import Modele.Entreprise;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoEntreprise;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutAssurance;
import Vue.Insertion.FenAjoutEntreprise;
import Vue.Insertion.FenAjoutFacture;

public class GestionFenAjoutEntreprise implements ActionListener{

	private FenAjoutEntreprise fenAjoutEntreprise;
	
	public GestionFenAjoutEntreprise(FenAjoutEntreprise fenEntreprise) {
		this.fenAjoutEntreprise = fenEntreprise;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Entreprise !");
					this.fenAjoutEntreprise.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER une Entreprise !");
					try {
						DefaultTableModel modeleTable = null;
						
						if (this.fenAjoutEntreprise.getFenPrecedent() instanceof FenAjoutFacture) {
							FenAjoutFacture fenAjoutFacture = (FenAjoutFacture) this.fenAjoutEntreprise.getFenPrecedent();
							 modeleTable = (DefaultTableModel) fenAjoutFacture.getTabMesEntreprise().getModel();
						}
						else if (this.fenAjoutEntreprise.getFenPrecedent() instanceof FenAjoutAssurance) {
							FenAjoutAssurance fenAjoutAssurance = (FenAjoutAssurance) this.fenAjoutEntreprise.getFenPrecedent();
							modeleTable = (DefaultTableModel) fenAjoutAssurance.getTabMesEntreprise().getModel();
						}
						else {
							System.out.println("Fenetre precedent pas trouvé break forcé !");
							break;
						}

						String siret = (String) fenAjoutEntreprise.getSIRET();					
						String nom = (String) fenAjoutEntreprise.getNom();
						String adresse = (String) fenAjoutEntreprise.getAdresse();
						String codepostal = (String) fenAjoutEntreprise.getCodePostal();
						String ville = (String) fenAjoutEntreprise.getVille();
						String mail = (String) fenAjoutEntreprise.getMail();
						String telephone = (String) fenAjoutEntreprise.getTelephone();
						String iban = (String) fenAjoutEntreprise.getIBAN();

						DaoEntreprise daoEntreprise = new DaoEntreprise(CictOracleDataSource.getInstance().getConnection());
						
						Entreprise entreprise = new Entreprise(siret, nom, adresse, codepostal, ville, mail, telephone, iban);
						//daoEntreprise.create(entreprise);
						
						String []EngrEntreprise = {siret, nom};
						modeleTable.addRow(EngrEntreprise);
						
						fenAjoutEntreprise.dispose();
						
					}catch (Exception ex) {
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
