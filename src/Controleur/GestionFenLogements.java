package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Modele.Logement;
import Modele.Louer;
import Modele.assurance;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoAssurance;
import Modele.Dao.DaoLogement;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.FenCompteursLogement;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutFacture;
import Vue.Insertion.FenAjoutLogement;


public class GestionFenLogements implements ActionListener{

	private FenAccueil fenAc;
	private DaoLogement daoLogement;
	
	public GestionFenLogements(FenAccueil fenAc) throws SQLException {
		this.fenAc = fenAc;
		this.daoLogement = new DaoLogement(CictOracleDataSource.getInstance().getConnection());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLogements().getModel();
		
		if (texte != null) {
			switch (texte) {
				case "Modifier":
					System.out.println("Vous MODIFIER une donnée dans Logement !");
					break;
					
				case "Supprimer":
					System.out.println("Vous SUPPRIMER une données dans Logement !");
					break;
				
				case "Archiver":
					System.out.println("Vous ARCHIVER une donnée prevenant de Logement !");
					break;
					
				case "Ajouter un diagnostic":
					System.out.println("Vous AJOUTER UN DIAGNOSTIC depuis Logement !");
					break;
					
				case "Charger":
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
					FenCompteursLogement fenCompMesLogements = new FenCompteursLogement();
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
					FenAjoutFacture fenAddFacture = new FenAjoutFacture();
					fenAc.getLayeredPane().add(fenAddFacture);
					fenAddFacture.setVisible(true);
					break;
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
	
	public void ecrireLigneTable(Logement logement, int numeroLigne) {
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesLogements().getModel();

		modeleTable.setValueAt(logement.getIdLogement(), numeroLigne, 0);
		modeleTable.setValueAt(logement.getSurfaceHabitable(), numeroLigne, 1);
		modeleTable.setValueAt(logement.getDateAcquisition(), numeroLigne, 2);		
		modeleTable.setValueAt(logement.getType_logement(), numeroLigne, 3);
		modeleTable.setValueAt(logement.getNbPieces(), numeroLigne, 4);
		modeleTable.setValueAt(logement.getNumEtage(), numeroLigne, 5);

	}

}
