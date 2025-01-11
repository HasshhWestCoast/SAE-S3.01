package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import Modele.Logement;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoLogement;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutCompteur;
import Vue.Insertion.FenAjoutLogement;


public class GestionFenAjoutLogement implements ActionListener{

	private FenAjoutLogement fenAjoutLogement;
	
	public GestionFenAjoutLogement(FenAjoutLogement fenAjoutLogement) {
		this.fenAjoutLogement = fenAjoutLogement;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		FenAccueil fenAC = (FenAccueil) this.fenAjoutLogement.getTopLevelAncestor();
		
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout logement");
					this.fenAjoutLogement.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER un logement !");
					try {
						DefaultTableModel modeleTable = (DefaultTableModel) fenAC.getTabMesLogements().getModel();

						String IdLogement = (String) fenAjoutLogement.getIdLogement();					
						String SurfaceHabitableString = (String) fenAjoutLogement.getSurfaceHabitable();
						double SurfaceHabitable = Double.parseDouble(SurfaceHabitableString);
						String DateAcquisition = (String) fenAjoutLogement.getDateAcquisition();
						String TypeDeLogement = (String) fenAjoutLogement.getComboBoxTypeDeLogement();
						String NbPieceString = (String) fenAjoutLogement.getNbPiece();
						int NbPiece = Integer.parseInt(NbPieceString);
						String NumEtageString = (String) fenAjoutLogement.getNumEtage();
						int NumEtage = Integer.parseInt(NumEtageString);

						DaoLogement daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
						
						Logement logement = new Logement(IdLogement, SurfaceHabitable, DateAcquisition, TypeDeLogement, NbPiece, NumEtage);
						//daoLogement.create(logement);
						
						String []EngrLogement = {IdLogement, SurfaceHabitableString, DateAcquisition, TypeDeLogement, NbPieceString, NumEtageString};
						modeleTable.addRow(EngrLogement);
						
						fenAjoutLogement.dispose();
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}

					break;
					
				case "Ajouter un compteur":
					System.out.println("Vous OUVREZ la page ajout compteur");
				     FenAjoutCompteur fenAjoutCompteur = new FenAjoutCompteur();
					
	                fenAC.getLayeredPane().add(fenAjoutCompteur);
	                fenAjoutCompteur.setVisible(true);
	                fenAjoutCompteur.moveToFront();
					break;
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}

}
