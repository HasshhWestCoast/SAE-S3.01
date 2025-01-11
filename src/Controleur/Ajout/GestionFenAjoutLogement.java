package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Modele.Bien;
import Modele.Logement;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Modele.Dao.DaoLogement;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutCompteur;
import Vue.Insertion.FenAjoutLogement;


public class GestionFenAjoutLogement implements ActionListener, ListSelectionListener{

	private FenAjoutLogement fenAjoutLogement;
	private Bien bien;
	
	public GestionFenAjoutLogement(FenAjoutLogement fenAjoutLogement) {
		this.fenAjoutLogement = fenAjoutLogement;
		this.bien = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		FenAccueil fenAC = (FenAccueil) this.fenAjoutLogement.getTopLevelAncestor();
		DefaultTableModel modeleTableBien = (DefaultTableModel) this.fenAjoutLogement.getTabMesBiens().getModel();

		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout logement");
					this.fenAjoutLogement.dispose();
					break;
				
				case "Charger":
					System.out.println("Vous CHARGER les Biens depuis Logement !");
					try {
						DaoBien daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
								
						List<Bien> mesDonnees = daoBien.findAll();
		
						Iterateur<Bien> itB = DaoBien.getIterateurBien();
						
				        if (itB == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
				        modeleTableBien.setRowCount(mesDonnees.size());  
						
						int countL = 0;
						while(itB.hasNext() && countL < mesDonnees.size()) {	
							Bien bien = itB.next();
							this.ecrireLigneTableBien(bien, countL);
							countL++;
						}
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;	
					
				case "Ajouter":
					System.out.println("Vous AJOUTER un logement !");
					
					   if (this.fenAjoutLogement.getIdLogement().isEmpty() ||
						        this.fenAjoutLogement.getDateAcquisition().isEmpty() ||
						        this.fenAjoutLogement.getSurfaceHabitable().isEmpty()||
						        this.fenAjoutLogement.getSurfaceHabitable().isEmpty())
						    {
						        JOptionPane.showMessageDialog(
						            this.fenAjoutLogement,
						            "Veuillez remplir tous les champs requis !",
						            "Erreur",
						            JOptionPane.ERROR_MESSAGE
						        );
						        return;
						    }
					   
						 // Vérification des conditions
					    if (this.bien == null) {
					        JOptionPane.showMessageDialog(
					            this.fenAjoutLogement,
					            "Veuillez sélectionner un bien !",
					            "Erreur",
					            JOptionPane.ERROR_MESSAGE
					        );
					        return;
					    }
					
					
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
						Boolean garageBoolean = (Boolean) fenAjoutLogement.getcheckGarage();
						
						int garage;
						if (garageBoolean == false) {
							garage = 0;
						}else {
							garage = 1;
						}
						DaoLogement daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
						
						System.out.println("bien trouvé : " + bien);
						Logement logement = new Logement(IdLogement, SurfaceHabitable, DateAcquisition, TypeDeLogement, NbPiece, NumEtage, garage,bien);
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
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		JTable tabBiens = this.fenAjoutLogement.getTabMesBiens();
		int selectedRow = tabBiens.getSelectedRow();
		if (selectedRow > -1) {
			try {
				DaoBien daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
				this.bien = daoBien.findById(tabBiens.getValueAt(selectedRow, 0).toString());
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}
	
	public void ecrireLigneTableBien(Bien bien, int numeroLigne) {
		DefaultTableModel modeleTableBiens = (DefaultTableModel) this.fenAjoutLogement.getTabMesBiens().getModel();

		modeleTableBiens.setValueAt(bien.getIdBien(), numeroLigne, 0);
		modeleTableBiens.setValueAt(bien.getTypeBien(), numeroLigne, 1);
	}

}
