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

import Modele.Bien;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.FenCompteursBien;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutBien;
import Vue.Insertion.FenAjoutFacture;

public class GestionFenBien implements ActionListener, ListSelectionListener{

	private FenAccueil fenAc;
	private DaoBien daoBien;
	private Bien bien;
	
	public GestionFenBien(FenAccueil fenAc) throws SQLException {
		this.fenAc = fenAc;
		this.daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
		this.bien = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesBiens().getModel();
		
		if (texte != null) {
			switch (texte) {
				case "Modifier":
					System.out.println("Vous MODIFIER une donnée dans Bien !");
					break;
					
				case "Supprimer":
					System.out.println("Vous SUPPRIMER une données dans Bien !");
					break;
				
				case "Archiver":
					System.out.println("Vous ARCHIVER une donnée prevenant de Bien !");
					break;
					
				case "Charger":
					System.out.println("Vous CHARGER les donnée dans Bien !");
					try {
						List<Bien> mesDonnees = this.daoBien.findAll();
						Iterateur<Bien> it = DaoBien.getIterateurBien();
						
				        if (it == null) {
				            System.out.println("Itérateur non initialisé !");
				            break;
				        }
						modeleTable.setRowCount(mesDonnees.size());  
						
						int count = 0;
						while(it.hasNext() && count < mesDonnees.size()) {	
							Bien bien = it.next();
							this.ecrireLigneTable(bien, count);
							count++;
						}
						
					}catch (SQLException ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
					break;
					
				case "Afficher les compteurs":
					System.out.println("Vous AFFICHER LES COMPTEURS depuis Bien !");
					
					FenCompteursBien fenCompMesBien = null;
					
					try {
						fenCompMesBien = new FenCompteursBien();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
					
					fenAc.getLayeredPane().add(fenCompMesBien);
					fenCompMesBien.setVisible(true);
					break;
					
				case "Ajouter un bien":
					System.out.println("Vous AJOUTER UN BIEN dans Bien !");
					FenAjoutBien fenAddBien = new FenAjoutBien();
					fenAc.getLayeredPane().add(fenAddBien);
					fenAddBien.setVisible(true);
					break;
					
				case "Ajouter des factures":
					System.out.println("Vous AJOUTER DES FACTURES depuis Bien !");
					
					 // Vérification des conditions
				    if (this.bien == null) {
				        JOptionPane.showMessageDialog(
				            this.fenAc,
				            "Veuillez sélectionner un bien !",
				            "Erreur",
				            JOptionPane.ERROR_MESSAGE
				        );
				        return;
				    }
					
					FenAjoutFacture fenAddFacture = null;
					
					try {
						fenAddFacture = new FenAjoutFacture(this.bien);
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
					
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
	
	public void ecrireLigneTable(Bien bien, int numeroLigne) {
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesBiens().getModel();

		modeleTable.setValueAt(bien.getIdBien(), numeroLigne, 0);
		modeleTable.setValueAt(bien.getAdresse(), numeroLigne, 1);
		modeleTable.setValueAt(bien.getVille(), numeroLigne, 2);		
		modeleTable.setValueAt(bien.getCodePostal(), numeroLigne, 3);
		modeleTable.setValueAt(bien.getTypeBien(), numeroLigne, 4);
		modeleTable.setValueAt(bien.getPeriodeConstruction(), numeroLigne, 5);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JTable tabBien = this.fenAc.getTabMesBiens();
		int selectedRow = tabBien.getSelectedRow();
		
		if (selectedRow > -1) {
			try {
				this.bien = daoBien.findById(tabBien.getValueAt(selectedRow, 0).toString());
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}
	
}
