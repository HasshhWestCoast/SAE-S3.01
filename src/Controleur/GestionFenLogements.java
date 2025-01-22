package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Modele.Logement;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoLogement;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.FenCompteurs;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutFacture;
import Vue.Insertion.FenAjoutLogement;

import Vue.Insertion.FenAjoutQuotite;

import rapport.CreerRapport;

/**
 * Classe contrôleur pour gérer les actions liées aux logements dans l'application.
 * Implémente ActionListener pour écouter les actions des boutons et ListSelectionListener
 * pour surveiller les changements de sélection dans la table.
 */

public class GestionFenLogements implements ActionListener, ListSelectionListener{

	private FenAccueil fenAc;
	private DaoLogement daoLogement;
	private Logement logement;
	
	/**
     * Constructeur.
     *
     * @param fenAc La fenêtre d'accueil associée à ce contrôleur.
     * @throws SQLException En cas de problème de connexion avec la base de données.
     */
	public GestionFenLogements(FenAccueil fenAc) throws SQLException {
		this.fenAc = fenAc;
		this.daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
		this.logement = null;
	}
	
	
	 /**
     * Gère les actions déclenchées par les boutons de l'interface utilisateur.
     *
     * @param e L'événement associé à l'action utilisateur.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLogements().getModel();
		
		if (texte != null) {
			switch (texte) {
					
				case "Supprimer":
                    // Supprime un logement sélectionné dans le tableau
					System.out.println("Vous SUPPRIMER une données dans Logement !");
					int ligneSelectionnee = this.fenAc.getTabMesLogements().getSelectedRow();

					try {
						String IdLogement = (String) this.fenAc.getTabMesLogements().getValueAt(ligneSelectionnee, 0);
						
						Logement logement = this.daoLogement.findById(IdLogement);
						
						this.daoLogement.delete(logement);
						
						modeleTable.removeRow(ligneSelectionnee);
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
					
				case "Générer un word":
				    System.out.println("Vous générez un rapport des Logements !");
				    try {
				        List<Logement> logements = daoLogement.findAll(); // Récupérer les logements depuis le DAO
				        CreerRapport.genererRapportLogement(logements); // Appeler la méthode pour générer le rapport
				        JOptionPane.showMessageDialog(
				            this.fenAc,
				            "Rapport des logements généré avec succès !",
				            "Succès",
				            JOptionPane.INFORMATION_MESSAGE
				        );
				    } catch (Exception ex) {
				        ex.printStackTrace();
				        JOptionPane.showMessageDialog(
				            this.fenAc,
				            "Erreur lors de la génération du rapport des logements.",
				            "Erreur",
				            JOptionPane.ERROR_MESSAGE
				        );
				    }
				    break;

				
				case "Archiver":
					System.out.println("Vous ARCHIVER une donnée prevenant de Logement !");
					
					 // Marque le logement sélectionné comme archivé 
                    System.out.println("Vous ARCHIVER une donnée prevenant de Bien !");
                    if (this.logement == null) {
                        JOptionPane.showMessageDialog(
                            this.fenAc,
                            "Veuillez sélectionner un logement a archiver !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }  
                    DefaultTableModel modeleTableArchives = (DefaultTableModel) this.fenAc.gettabLogementArchives().getModel();
                    
                    String[] EngrBien = {this.logement.getIdLogement(), String.valueOf(this.logement.getSurfaceHabitable()), this.logement.getDateAcquisition(), this.logement.getType_logement(), String.valueOf(this.logement.getNbPieces())};
                    modeleTableArchives.addRow(EngrBien);
                    
                    // Affiche un message de confirmation après l'archivage
                    JOptionPane.showMessageDialog(
                        this.fenAc,
                        "Vous avez archivé un logement.",
                        "Confirmation",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    
					break;
					
				case "Charger":
                    // Charge les données des logements dans le tableau
					System.out.println("Vous CHARGER les données dans Logement !");
					try {
						List<Logement> mesDonnees = this.daoLogement.findAll();
		
						Iterateur<Logement> it = DaoLogement.getIterateurLogement();
						
				        if (it == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
						modeleTable.setRowCount(mesDonnees.size());  
						
						int count = 0;
						while(it.hasNext() && count < mesDonnees.size()) {	
							Logement logement = it.next();
							this.ecrireLigneTable(logement, count);
							count++;
						}
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
					
				case "Afficher les compteurs":
					System.out.println("Vous AFFICHER LES COMPTEURS depuis Logement !");
				
					FenCompteurs fenCompMesLogements = null;
					
					try {
						fenCompMesLogements = new FenCompteurs();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
					
					fenAc.getLayeredPane().add(fenCompMesLogements);
					fenCompMesLogements.setVisible(true);
					break;
					
				case "Ajouter un logement":
					System.out.println("Vous AJOUTER UN LOGEMENT depuis Logement !");
					FenAjoutLogement fenAddLogements = new FenAjoutLogement();
					fenAc.getLayeredPane().add(fenAddLogements);
					fenAddLogements.setVisible(true);
					break;
					
				case "Ajouter des factures":
					System.out.println("Vous AJOUTER DES FACTURES depuis Logement !");
					
					 // Vérification des conditions
				    if (this.logement == null) {
				        JOptionPane.showMessageDialog(
				            this.fenAc,
				            "Veuillez sélectionner un logement !",
				            "Erreur",
				            JOptionPane.ERROR_MESSAGE
				        );
				        return;
				    }
					FenAjoutFacture fenAddFacture = null;
					
					try {
						fenAddFacture = new FenAjoutFacture(this.logement);
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
					
					fenAc.getLayeredPane().add(fenAddFacture);
					fenAddFacture.setVisible(true);
					break;
				case "Ajouter Quotite":
					System.out.println("Vous ouvrer la page ajout quotite depuis logement !");
					
					 // Vérification des conditions
				    if (this.logement == null) {
				        JOptionPane.showMessageDialog(
				            this.fenAc,
				            "Veuillez sélectionner un logement !",
				            "Erreur",
				            JOptionPane.ERROR_MESSAGE
				        );
				        return;
				    }
				    
					FenAjoutQuotite fenAddQuotite = null;
					
					fenAddQuotite = new FenAjoutQuotite(this.logement);
					
					fenAc.getLayeredPane().add(fenAddQuotite);
					fenAddQuotite.setVisible(true);
					
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
	
    /**
     * Insère les données d'un logement dans une ligne du tableau.
     *
     * @param logement Le logement à insérer.
     * @param numeroLigne Le numéro de la ligne où insérer les données.
     */
	
	public void ecrireLigneTable(Logement logement, int numeroLigne) {
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLogements().getModel();

		modeleTable.setValueAt(logement.getIdLogement(), numeroLigne, 0);
		modeleTable.setValueAt(logement.getSurfaceHabitable(), numeroLigne, 1);
		modeleTable.setValueAt(logement.getDateAcquisition(), numeroLigne, 2);		
		modeleTable.setValueAt(logement.getType_logement(), numeroLigne, 3);
		modeleTable.setValueAt(logement.getNbPieces(), numeroLigne, 4);
		modeleTable.setValueAt(logement.getNumEtage(), numeroLigne, 5);

	}
	
    /**
     * Gère les changements de sélection dans le tableau des logements.
     *
     * @param e L'événement de changement de sélection.
     */
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		JTable tabLogement = this.fenAc.getTabMesLogements();
		int selectedRow = tabLogement.getSelectedRow();
		
		if (selectedRow > -1) {
			try {
				this.logement = daoLogement.findById(tabLogement.getValueAt(selectedRow, 0).toString());
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}
	


}
