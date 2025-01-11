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
import Modele.Entreprise;
import Modele.Facture;

import Modele.Logement;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoEntreprise;
import Modele.Dao.DaoFacture;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutEntreprise;
import Vue.Insertion.FenAjoutFacture;

public class GestionFenAjoutFacture implements ActionListener, ListSelectionListener{

	private FenAjoutFacture fenAjoutFacture;
	private DaoEntreprise daoEntreprise;
	
	private Logement logement;
	private Bien bien;
	private Entreprise entreprise;
	
	public GestionFenAjoutFacture(FenAjoutFacture fenAjoutFacture) throws SQLException {
		this.fenAjoutFacture = fenAjoutFacture;
		this.daoEntreprise = new DaoEntreprise(CictOracleDataSource.getInstance().getConnection());
		this.logement = null;
		this.bien = null;
		this.entreprise = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAjoutFacture.getTabMesEntreprise().getModel();


		FenAccueil fenAC = (FenAccueil) this.fenAjoutFacture.getTopLevelAncestor();
		
		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Facture !");
					this.fenAjoutFacture.dispose();
					break;
					
				case "Ajouter":
				    System.out.println("Vous AJOUTER une Facture !");
				    
				    // Vérification des conditions
				    if (this.entreprise == null) {
				        JOptionPane.showMessageDialog(
				            this.fenAjoutFacture,
				            "Veuillez sélectionner une entreprise !",
				            "Erreur",
				            JOptionPane.ERROR_MESSAGE
				        );
				        return;
				    }
				    
				    if (this.fenAjoutFacture.getTextFieldIdFacture().isEmpty() ||
				        this.fenAjoutFacture.getTextFieldDateEmission().isEmpty() ||
				        this.fenAjoutFacture.getTextFieldDatePaiement().isEmpty() ||
				        this.fenAjoutFacture.getTextFieldMontantReelVerse().isEmpty() ||
				        this.fenAjoutFacture.getTextFieldMontant().isEmpty()||
				    	this.fenAjoutFacture.getTextFieldNumeroDevis().isEmpty())
				    {
				        JOptionPane.showMessageDialog(
				            this.fenAjoutFacture,
				            "Veuillez remplir tous les champs requis !",
				            "Erreur",
				            JOptionPane.ERROR_MESSAGE
				        );
				        return;
				    }
				    
				    try {
				        DefaultTableModel modeleTableFacture = (DefaultTableModel) fenAC.getTabMesFactures().getModel();

				        String IdFacture = (String) fenAjoutFacture.getTextFieldIdFacture();                    
				        String DateEmission = (String) fenAjoutFacture.getTextFieldDateEmission();
				        String DatePaiement = (String) fenAjoutFacture.getTextFieldDatePaiement();
				        String ModePaiement = (String) fenAjoutFacture.getComboBoxModePaiement();
				        String NumeroDevis = (String) fenAjoutFacture.getTextFieldNumeroDevis();
				        String Designation = (String) fenAjoutFacture.getComboBoxDesignation();
				        String MontantReelVerseString = (String) fenAjoutFacture.getTextFieldMontantReelVerse();
				        double MontantReelVerse = Double.parseDouble(MontantReelVerseString);
				        String MontantString = (String) fenAjoutFacture.getTextFieldMontant();
				        double Montant = Double.parseDouble(MontantString);
				        Boolean ImputableLocataireString = (Boolean) fenAjoutFacture.getcheckImputableLocataire();
				        int ImputableLocataire = ImputableLocataireString ? 1 : 0;
				        String AcompteVerseString = (String) fenAjoutFacture.getTextFieldAcompteVersé();
				        double AcompteVerse = Double.parseDouble(AcompteVerseString);
				        
				        if (this.fenAjoutFacture.getPrecedent() instanceof Bien) {
				            bien = (Bien) this.fenAjoutFacture.getPrecedent();
				        } else if (this.fenAjoutFacture.getPrecedent() instanceof Logement) {
				            logement = (Logement) this.fenAjoutFacture.getPrecedent();
				        }
				        
				        DaoFacture daoFacture = new DaoFacture(CictOracleDataSource.getInstance().getConnection());
				        
				        Facture facture = new Facture(
				            IdFacture, DateEmission, DatePaiement, ModePaiement, NumeroDevis, 
				            Designation, MontantReelVerse, Montant, ImputableLocataire, AcompteVerse, logement, bien, entreprise
				        );
				        
				        // daoFacture.create(facture);
				        
				        String[] EngrFacture = {
				            IdFacture, DateEmission, DatePaiement, ModePaiement, Designation,
				            MontantReelVerseString, MontantString, Integer.toString(ImputableLocataire), AcompteVerseString
				        };
				        modeleTableFacture.addRow(EngrFacture);
				        
				        fenAjoutFacture.dispose();
				        
				    } catch (SQLException ex) {
				        System.out.println(ex.getMessage());
				        ex.printStackTrace();
				    }
				    break;

					
				case "Inserer":
					System.out.println("Vous AJOUTER une Entreprise depuis Facture !");
				    FenAjoutEntreprise fenAjoutEntreprise = new FenAjoutEntreprise();
	                fenAC.getLayeredPane().add(fenAjoutEntreprise);
	                fenAjoutEntreprise.setVisible(true);
	                fenAjoutEntreprise.moveToFront();
					break;
					
				case "Charger":
					System.out.println("Vous CHARGEZ les Entreprise depuis Factures !");
					try {
						List<Entreprise> mesDonnees = this.daoEntreprise.findAll();
		
						Iterateur<Entreprise> it = DaoEntreprise.getIterateurEntreprise();
						
				        if (it == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
						modeleTable.setRowCount(mesDonnees.size());  
						
						int count = 0;
						while(it.hasNext() && count < mesDonnees.size()) {	
							Entreprise entreprise = it.next();
							this.ecrireLigneTable(entreprise, count);
							count++;
						}
				        this.fenAjoutFacture.getTabMesEntreprise().getSelectionModel().addListSelectionListener(this);

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
	
	
	public void ecrireLigneTable(Entreprise entreprise, int numeroLigne) {
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAjoutFacture.getTabMesEntreprise().getModel();

		modeleTable.setValueAt(entreprise.getSiret(), numeroLigne, 0);
		modeleTable.setValueAt(entreprise.getNom(), numeroLigne, 1);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		JTable tabEntreprise = this.fenAjoutFacture.getTabMesEntreprise();
		
		int selectedRow = tabEntreprise.getSelectedRow();
		if (selectedRow > -1) {
			try {
				this.entreprise = daoEntreprise.findById(tabEntreprise.getValueAt(selectedRow, 0).toString());
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}
}
