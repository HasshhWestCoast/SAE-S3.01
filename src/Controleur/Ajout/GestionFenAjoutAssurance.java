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

import Modele.Entreprise;
import Modele.Logement;
import Modele.assurance;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoAssurance;
import Modele.Dao.DaoEntreprise;
import Modele.Dao.DaoLogement;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutAssurance;
import Vue.Insertion.FenAjoutEntreprise;
import Vue.Insertion.FenAjoutLogement;

public class GestionFenAjoutAssurance implements ActionListener, ListSelectionListener{

	private FenAjoutAssurance fenAjoutAssurance;
	private DaoEntreprise daoEntreprise;
	private DaoLogement daoLogement;
	private Logement logement;
	private Entreprise entreprise;

	
	public GestionFenAjoutAssurance(FenAjoutAssurance fenAjoutAssurance) throws SQLException {
		this.fenAjoutAssurance = fenAjoutAssurance;
		this.daoEntreprise = new DaoEntreprise(CictOracleDataSource.getInstance().getConnection());
		this.daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
		this.logement = null;
		this.entreprise = null;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		
		FenAccueil fenAC = (FenAccueil) this.fenAjoutAssurance.getTopLevelAncestor();
		DefaultTableModel modeleTableEntreprise = (DefaultTableModel) this.fenAjoutAssurance.getTabMesEntreprise().getModel();
		DefaultTableModel modeleTableLogement = (DefaultTableModel) this.fenAjoutAssurance.getTabMesLogements().getModel();

		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Assurance !");
					this.fenAjoutAssurance.dispose();
					break;
					
				case "Ajouter":
					System.out.println("Vous AJOUTER une donnée à Assurance !");
					
					   if (this.fenAjoutAssurance.getTextFieldNumeroPolice().isEmpty() ||
						        this.fenAjoutAssurance.getTextFieldMontant().isEmpty() ||
						        this.fenAjoutAssurance.getTextFieldDateEcheance().isEmpty())
						    {
						        JOptionPane.showMessageDialog(
						            this.fenAjoutAssurance,
						            "Veuillez remplir tous les champs requis !",
						            "Erreur",
						            JOptionPane.ERROR_MESSAGE
						        );
						        return;
						    }
					
					
					 // Vérification des conditions
				    if (this.entreprise == null || this.logement == null) {
				        JOptionPane.showMessageDialog(
				            this.fenAjoutAssurance,
				            "Veuillez sélectionner au moins un champ par tableau !",
				            "Erreur",
				            JOptionPane.ERROR_MESSAGE
				        );
				        return;
				    }
				    
				 
					
					
					try {
						DefaultTableModel modeleTable = (DefaultTableModel) fenAC.getTabMesAssurances().getModel();

						String NumeroPolice = (String) fenAjoutAssurance.getTextFieldNumeroPolice();					
						String MontantString = (String) fenAjoutAssurance.getTextFieldMontant();
						Float Montant = Float.parseFloat(MontantString);
						String DateEcheance = (String) fenAjoutAssurance.getTextFieldDateEcheance();
						
						DaoAssurance daoAssurance = new DaoAssurance(CictOracleDataSource.getInstance().getConnection());
						
						assurance assu = new assurance(NumeroPolice, Montant, DateEcheance, logement, entreprise);
						//daoAssurance.create(assu);
						
						String []EngrAssu = {NumeroPolice, MontantString, DateEcheance, entreprise.getSiret(), logement.getIdLogement()};
						modeleTable.addRow(EngrAssu);
						
						fenAjoutAssurance.dispose();
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}			
					break;
				case "Inserer L":
					System.out.println("Vous AJOUTER un Logement depuis Assurance !");
					FenAjoutLogement fenAddLog = new FenAjoutLogement();
	                fenAC.getLayeredPane().add(fenAddLog);
	                fenAddLog.setVisible(true);
	                fenAddLog.moveToFront();
					break;
					
				case "Inserer E":
					System.out.println("Vous AJOUTER une Entreprise depuis Assurance !");
					FenAjoutEntreprise fenAjoutEntreprise = new FenAjoutEntreprise();
	                fenAC.getLayeredPane().add(fenAjoutEntreprise);
	                fenAjoutEntreprise.setVisible(true);
	                fenAjoutEntreprise.moveToFront();
					break;
				
				case "Charger":
					System.out.println("Vous CHARGER les Logements et Entreprise depuis Assurance !");
					try {
						List<Logement> mesDonnees = this.daoLogement.findAll();
		
						Iterateur<Logement> itL = DaoLogement.getIterateurLogement();
						
				        if (itL == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
				        modeleTableLogement.setRowCount(mesDonnees.size());  
						
						int countL = 0;
						while(itL.hasNext() && countL < mesDonnees.size()) {	
							Logement logement = itL.next();
							this.ecrireLigneTableLogement(logement, countL);
							countL++;
						}
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					
					try {
						List<Entreprise> mesDonnees = this.daoEntreprise.findAll();
		
						Iterateur<Entreprise> it = DaoEntreprise.getIterateurEntreprise();
						
				        if (it == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
				        modeleTableEntreprise.setRowCount(mesDonnees.size());  
						
						int count = 0;
						while(it.hasNext() && count < mesDonnees.size()) {	
							Entreprise entreprise = it.next();
							this.ecrireLigneTableEntreprise(entreprise, count);
							count++;
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
	
	public void ecrireLigneTableEntreprise(Entreprise entreprise, int numeroLigne) {
		DefaultTableModel modeleTableEntreprise = (DefaultTableModel) this.fenAjoutAssurance.getTabMesEntreprise().getModel();

		modeleTableEntreprise.setValueAt(entreprise.getSiret(), numeroLigne, 0);
		modeleTableEntreprise.setValueAt(entreprise.getNom(), numeroLigne, 1);
	}
	
	public void ecrireLigneTableLogement(Logement logement, int numeroLigne) {
		DefaultTableModel modeleTableLogement = (DefaultTableModel) this.fenAjoutAssurance.getTabMesLogements().getModel();

		modeleTableLogement.setValueAt(logement.getIdLogement(), numeroLigne, 0);
		modeleTableLogement.setValueAt(logement.getDateAcquisition(), numeroLigne, 1);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		JTable tabEntreprise = this.fenAjoutAssurance.getTabMesEntreprise();
		int selectedRow = tabEntreprise.getSelectedRow();
		if (selectedRow > -1) {
			try {
				this.entreprise = daoEntreprise.findById(tabEntreprise.getValueAt(selectedRow, 0).toString());
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		
		JTable tabLogement = this.fenAjoutAssurance.getTabMesLogements();
		int selectedRowLog = tabLogement.getSelectedRow();
		if (selectedRowLog > -1) {
			try {
				this.logement = daoLogement.findById(tabLogement.getValueAt(selectedRowLog, 0).toString());
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}

}
