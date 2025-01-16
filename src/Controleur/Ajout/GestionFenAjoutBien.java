package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Modele.Bien;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutBien;

public class GestionFenAjoutBien implements ActionListener{

	private FenAjoutBien fenAjoutBien;
	
	public GestionFenAjoutBien(FenAjoutBien fenAjoutBien) {
		this.fenAjoutBien = fenAjoutBien;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		FenAccueil fenAC = (FenAccueil) this.fenAjoutBien.getTopLevelAncestor();

		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Bien");
					this.fenAjoutBien.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER un bien !");
					
					
					  if (this.fenAjoutBien.getTextFieldIdBien().isEmpty() ||
						        this.fenAjoutBien.getTextFieldAdresse().isEmpty() ||
						        this.fenAjoutBien.getTextFieldVille().isEmpty() ||
						        this.fenAjoutBien.getTextFieldCodePostale().isEmpty() ||
						        this.fenAjoutBien.getTextFieldPeriodeConstruction().isEmpty())
						    {
						        JOptionPane.showMessageDialog(
						            this.fenAjoutBien,
						            "Veuillez remplir tous les champs requis !",
						            "Erreur",
						            JOptionPane.ERROR_MESSAGE
						        );
						        return;
						    }
					try {
						DefaultTableModel modeleTable = (DefaultTableModel) fenAC.getTabMesBiens().getModel();

						String IdBien = (String) fenAjoutBien.getTextFieldIdBien();					
						String Adresse = (String) fenAjoutBien.getTextFieldAdresse();
						String Ville = (String) fenAjoutBien.getTextFieldVille();
						String TypeBien = (String) fenAjoutBien.getComboBoxTypeDeBien();
						String CodePostal = (String) fenAjoutBien.getTextFieldCodePostale();
						String PeriodeConstruction = (String) fenAjoutBien.getTextFieldPeriodeConstruction();
						
						DaoBien daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
						
						Bien bien = new Bien(IdBien, Adresse, Ville, TypeBien, CodePostal, PeriodeConstruction);
						daoBien.create(bien);
						
						String []EngrBien = {IdBien, Adresse, Ville, CodePostal, TypeBien, PeriodeConstruction};
						modeleTable.addRow(EngrBien);
						
						fenAjoutBien.dispose();
						
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
