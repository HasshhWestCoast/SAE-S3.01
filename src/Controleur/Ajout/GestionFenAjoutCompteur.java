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
import Modele.Compteur;
import Modele.Logement;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Modele.Dao.DaoCompteur;
import Modele.Dao.DaoLogement;
import Modele.Dao.Iterateur;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutCompteur;


public class GestionFenAjoutCompteur implements ActionListener, ListSelectionListener{

	private FenAjoutCompteur fenAjoutCompteur;
	private Bien bien;
	private Logement logement;
	private DaoLogement daoLogement;
	private DaoBien daoBien;

	
	public GestionFenAjoutCompteur(FenAjoutCompteur fenAjoutCompteur) throws SQLException {
		this.fenAjoutCompteur = fenAjoutCompteur;
		this.bien = null;
		this.logement = null;
		this.daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
		this.daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout compteur");
					this.fenAjoutCompteur.dispose();
					break;
				
				case "Ajouter":
					System.out.println("Vous AJOUTER un compteur au bien !");
					
					if (this.fenAjoutCompteur.getTextFieldIdCompteur().isEmpty()) {
				        JOptionPane.showMessageDialog(
				            this.fenAjoutCompteur,
				            "Veuillez remplir tous les champs !",
				            "Erreur",
				            JOptionPane.ERROR_MESSAGE
				        );
				        return;
				    }
					
					
				    try {
						String IdCompteur = (String) fenAjoutCompteur.getTextFieldIdCompteur();					
						String TypeCompteur = (String) fenAjoutCompteur.getComboBoxTypeComp();

						DaoCompteur daoCompteur = new DaoCompteur(CictOracleDataSource.getInstance().getConnection());
						
						Compteur compteur = new Compteur(IdCompteur, TypeCompteur, bien, logement);
						daoCompteur.create(compteur);
												
						fenAjoutCompteur.dispose();
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}			
					break;
					
				case "Charger":
					System.out.println("Vous CHARGER les Logements et Bien depuis AJOUT Compteur !");
					DefaultTableModel modeleTableLogement = (DefaultTableModel) this.fenAjoutCompteur.getTabMesLogements().getModel();
					DefaultTableModel modeleTableBien = (DefaultTableModel) this.fenAjoutCompteur.getTabMesBiens().getModel();

					try {
						List<Logement> mesDonneesLog = this.daoLogement.findAll();
		
						Iterateur<Logement> itL = DaoLogement.getIterateurLogement();
						
				        if (itL == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
				        modeleTableLogement.setRowCount(mesDonneesLog.size());  
						
						int countL = 0;
						while(itL.hasNext() && countL < mesDonneesLog.size()) {	
							Logement logement = itL.next();
							this.ecrireLigneTableLogement(logement, countL);
							countL++;
						}
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					
					try {
						List<Bien> mesDonnees = this.daoBien.findAll();
		
						Iterateur<Bien> itB = DaoBien.getIterateurBien();
						
				        if (itB == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
				        modeleTableBien.setRowCount(mesDonnees.size());  
						
						int countB = 0;
						while(itB.hasNext() && countB < mesDonnees.size()) {	
							Bien bien = itB.next();
							this.ecrireLigneTableBien(bien, countB);
							countB++;
						}
						
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

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JTable tabBiens = this.fenAjoutCompteur.getTabMesBiens();
		int selectedRowBien = tabBiens.getSelectedRow();
		if (selectedRowBien > -1) {
			try {
				this.bien = daoBien.findById(tabBiens.getValueAt(selectedRowBien, 0).toString());
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
			System.out.println("bien trouvé : " + bien);
		}		
		
		JTable tabLogement = this.fenAjoutCompteur.getTabMesLogements();
		int selectedRowLogement = tabLogement.getSelectedRow();
		if (selectedRowLogement > -1) {
			try {
				this.logement = daoLogement.findById(tabLogement.getValueAt(selectedRowLogement, 0).toString());
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
			System.out.println("logement trouvé : " + logement);
		}			
	}
	
	
	public void ecrireLigneTableLogement(Logement logement, int numeroLigne) {
		DefaultTableModel modeleTableLogement = (DefaultTableModel) this.fenAjoutCompteur.getTabMesLogements().getModel();

		modeleTableLogement.setValueAt(logement.getIdLogement(), numeroLigne, 0);
		modeleTableLogement.setValueAt(logement.getDateAcquisition(), numeroLigne, 1);
	}
	
	public void ecrireLigneTableBien(Bien bien, int numeroLigne) {
		DefaultTableModel modeleTableBien = (DefaultTableModel) this.fenAjoutCompteur.getTabMesBiens().getModel();

		modeleTableBien.setValueAt(bien.getIdBien(), numeroLigne, 0);
		modeleTableBien.setValueAt(bien.getTypeBien(), numeroLigne, 1);
	}

}
