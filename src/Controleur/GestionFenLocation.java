package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Modele.Locataire;
import Modele.Louer;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoLocataire;
import Modele.Dao.DaoLouer;
import Modele.Dao.Iterateur;
import Modele.Dao.Requetes.Select.RequeteSelectDatePaiement;
import Modele.Dao.Requetes.sous_programme.SousProgrammeCalculDuLoyer;
import Vue.FenAccueil;
import Vue.FenInfosLocataire;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutLocation;


public class GestionFenLocation implements ActionListener, ListSelectionListener {

	private FenAccueil fenAc;
	private DaoLouer daoLouer;
	private Louer louer;
	
	public GestionFenLocation(FenAccueil fenAc) throws SQLException {
		this.louer = null;
		this.fenAc = fenAc;
		this.daoLouer = new DaoLouer(CictOracleDataSource.getInstance().getConnection());
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLocations().getModel();

		
		if (texte != null) {
			switch (texte) {
				case "Charger":
					System.out.println("Vous Charger les donnée dans Location !");
					try {
						List<Louer> mesDonnees = this.daoLouer.findAll();
		
						Iterateur<Louer> it = DaoLouer.getIterateurLouer();
						
				        if (it == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
						modeleTable.setRowCount(mesDonnees.size());  
						
						int count = 0;
						while(it.hasNext() && count < mesDonnees.size()) {	
							Louer louer = it.next();
							this.ecrireLigneTable(louer, count);
							count++;
						}
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
					
				case "Supprimer":
					System.out.println("Vous SUPPRIMER une données dans Location !");
					int ligneSelectionnee = this.fenAc.getTabMesLocations().getSelectedRow();

					try {
						String IdLocataire = (String) this.fenAc.getTabMesLocations().getValueAt(ligneSelectionnee, 0);
						String IdBien = (String) this.fenAc.getTabMesLocations().getValueAt(ligneSelectionnee, 1);
						String DateDebut = (String) this.fenAc.getTabMesLocations().getValueAt(ligneSelectionnee, 3);

						Louer louer = this.daoLouer.findById(IdBien, IdLocataire, DateDebut);
						
						this.daoLouer.delete(louer);
						
						modeleTable.removeRow(ligneSelectionnee);
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
				
				case "Inserer":
					System.out.println("Vous INSERER une donnée dans Location !");
					
					FenAjoutLocation fenAddLocation = null;
					
					try {
						fenAddLocation = new FenAjoutLocation();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
					
					int width = fenAc.getLayeredPane().getWidth();
		            int height = fenAc.getLayeredPane().getHeight();
		            fenAddLocation.setBounds(50, 50, width - 100, height - 15);
					
					fenAc.getLayeredPane().add(fenAddLocation);
					fenAddLocation.setVisible(true);
					break;
					
				case "Mise à jour":
					System.out.println("Vous METTEZ A JOUR une donnée dans Location !");
					break;
					
				case "Informations locataire":
				    System.out.println("Vous ouvrez la page informations locataire !");
				    FenInfosLocataire fenInfoLocataire = null;
				    try {
				        fenInfoLocataire = new FenInfosLocataire();

				        // Récupération de la ligne sélectionnée
				        JTable tabLocations = this.fenAc.getTabMesLocations();
				        int selectedRow = tabLocations.getSelectedRow();

				        if (selectedRow > -1) {
				            // Récupérer l'IdLocataire depuis la première colonne
				            String IdLocataire = (String) tabLocations.getValueAt(selectedRow, 0);

				            // Utiliser le DAO pour récupérer les informations du locataire
				            DaoLocataire daoLocataire = new DaoLocataire(CictOracleDataSource.getInstance().getConnection());
				            Locataire locataire = daoLocataire.findById(IdLocataire);

				            if (locataire != null) {
				                // Remplir les champs dans FenLocataire avec les données du locataire
				                fenInfoLocataire.setTextFieldIdentifiant(locataire.getIdLocataire());
				                fenInfoLocataire.setTextFieldNom(locataire.getNom());
				                fenInfoLocataire.setTextFieldPrenom(locataire.getPrenom());
				                fenInfoLocataire.setTextFieldDateNaissance(locataire.getDateNaissance());
				                fenInfoLocataire.setTextFieldNumTelephone(locataire.getTelephone());
				                fenInfoLocataire.setTextFieldMail(locataire.getMail());

				                // Récupérer le bien lié au locataire depuis le tableau source
				                String bien = (String) tabLocations.getValueAt(selectedRow, 1); // Colonne 1 correspond à "Bien"

				                // Mettre à jour la colonne "Bien" dans le tableau de régularisation
				                fenInfoLocataire.getTabRegCharges().setValueAt(bien, 0, 0); // Ligne 0, Colonne 0 (Bien)
				                
				                // Récupérer la date  lié au locataire depuis le tableau source
				                String datedebut = (String) tabLocations.getValueAt(selectedRow, 3); // Colonne 1 correspond à "Bien"
				                

				                // Mettre à jour la colonne periode dans le tableau de régularisation
				                fenInfoLocataire.getTabRegCharges().setValueAt(datedebut, 0, 1); // Ligne 0, Colonne 1


				                System.out.println("Colonne 'Bien' mise à jour avec : " + bien);
				            } else {
				                System.out.println("Aucun locataire trouvé pour l'ID : " + IdLocataire);
				            }
				        } else {
				            System.out.println("Aucune ligne sélectionnée.");
				        }

				    } catch (SQLException e1) {
				        e1.printStackTrace();
				    }
				    this.fenAc.getLayeredPane().add(fenInfoLocataire);
				    fenInfoLocataire.setVisible(true);
				    break;



					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
	
	public void ecrireLigneTable(Louer louer, int numeroLigne) {
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLocations().getModel();

		modeleTable.setValueAt(louer.getLocataire().getIdLocataire(), numeroLigne, 0);
		modeleTable.setValueAt(louer.getBien().getIdBien(), numeroLigne, 1);
		modeleTable.setValueAt(louer.getBien().getTypeBien(), numeroLigne, 2);		
		modeleTable.setValueAt(louer.getDateDebut(), numeroLigne, 3);
		modeleTable.setValueAt(louer.getDateDerniereRegularisation(), numeroLigne, 4);

	}

	
	public String[] findOneByBien(String idBien) throws SQLException {
	    RequeteSelectDatePaiement requete = new RequeteSelectDatePaiement();
	    String[] facture = new String[3]; // Tableau pour stocker Date_Paiement, Date_Emission et montant_reel_payer

	    try (PreparedStatement prSt = CictOracleDataSource.getInstance().getConnection().prepareStatement(requete.requete())) {
	        requete.parametres(prSt, idBien);
	        try (ResultSet rs = prSt.executeQuery()) {
	            if (rs.next()) {
	                facture[0] = rs.getString("Date_Paiement");
	                facture[1] = rs.getString("Date_Emission");
	                facture[2] = String.valueOf(rs.getDouble("montant_reel_verse"));
	            } else {
	                System.out.println("Aucune facture trouvée pour le bien : " + idBien);
	                return null; 
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de la récupération de la facture pour le bien " + idBien + " : " + e.getMessage());
	        throw e;
	    }

	    return facture;
	}



	@Override
	public void valueChanged(ListSelectionEvent e) {
		JTable tabLocations = this.fenAc.getTabMesLocations();
		int selectedRow = tabLocations.getSelectedRow();
		
		if (selectedRow > -1) {
			try {
				String IdLocataire = (String) tabLocations.getValueAt(selectedRow, 0);
				String IdBien = (String) tabLocations.getValueAt(selectedRow, 1);
				String DateDebut = (String) tabLocations.getValueAt(selectedRow, 3);

				String res[] = findOneByBien(IdBien);
				
				String DatePaiement = "NA";
				String DateEmission = "NA";
				String montant_reel_payer = "NA";

				double montant_du = -1;
				if (res != null) {
				    DatePaiement = res[0] != null ? res[0] : "NA";
				    DateEmission = res[1] != null ? res[1] : "NA";
				    montant_reel_payer = res[2] != null ? res[2] : "NA";
				}
				
				this.louer = daoLouer.findById(IdBien, IdLocataire, DateDebut);
				
				if (!montant_reel_payer.equals("NA") && this.louer != null) {
					// SousProgrammeCalculDuLoyer sousProgrammeLoyer = new SousProgrammeCalculDuLoyer();
					// montant_du = appelSousProgrammeCalculDuLoyer(sousProgrammeLoyer, IdBien); 
					montant_du = this.louer.getLoyerMensTTC() - Double.parseDouble(montant_reel_payer);
					
					// Mettre à jour le champ "Restant dû"
					fenAc.settextFieldRestantDu(montant_du != -1 ? String.valueOf(montant_du) : "NA");
			    }

				fenAc.settextFieldDatePaiement(DatePaiement);		
				fenAc.settextFieldCaution(String.valueOf(louer.getCautionTTC()));
				fenAc.settextFieldDateEmission(DateEmission);
				fenAc.settextFieldMontantPayé(montant_reel_payer);
				fenAc.settextFieldProvisionSurCharges(String.valueOf(this.louer.getprovision_chargeMoisTTC()));
				fenAc.settextFieldLoyer(String.valueOf(this.louer.getLoyerMensTTC()));
				
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
	}
}
	
	private double appelSousProgrammeCalculDuLoyer(SousProgrammeCalculDuLoyer sousProgramme, String idBien) throws SQLException {
	    double restantLoyer = 0;

	    try (CallableStatement callableStatement = CictOracleDataSource.getInstance().getConnection().prepareCall(sousProgramme.appelSousProgramme())) {
	        // Paramétrer le CallableStatement
	        sousProgramme.parametresCalcul(callableStatement, idBien);

	        // Exécuter l'appel
	        callableStatement.execute();

	        // Récupérer le résultat
	        restantLoyer = callableStatement.getDouble(1); // Premier paramètre de sortie
	    } catch (SQLException e) {
	        System.out.println("Erreur lors de l'appel au sous-programme pour le restant du loyer : " + e.getMessage());
	        throw e;
	    }

	    return restantLoyer;
	}


}
