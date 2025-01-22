package Controleur.Afficher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Modele.Compteur;
import Modele.Releve;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoCompteur;
import Modele.Dao.DaoReleve;
import Modele.Dao.Iterateur;
import Vue.FenCompteurs;
import Vue.FenMesReleves;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutCompteur;

public class GestionFenCompteurs implements ActionListener, ListSelectionListener{
	
	private FenCompteurs fenComp;
	private DaoCompteur daoCompteur;
	private DaoReleve daoReleve;
	private Compteur compteur;

	public GestionFenCompteurs(FenCompteurs fenComp) throws SQLException {
		this.fenComp = fenComp;
		this.daoReleve = new DaoReleve(CictOracleDataSource.getInstance().getConnection());
		this.daoCompteur = new DaoCompteur(CictOracleDataSource.getInstance().getConnection());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String texte = ((RoundedButton) source).getText();

		if (texte != null) {
			switch (texte) {
				case "Annuler":
					System.out.println("Vous FERMEZ la page Compteurs !");
					this.fenComp.dispose();
					break;
				
				case "Afficher date relevé":
					System.out.println("Vous Afficher les relevé d'un compteur !");
		
					if (this.compteur == null) {
				        JOptionPane.showMessageDialog(
				            this.fenComp,
				            "Veuillez sélectionner un compteur d'abord !",
				            "Erreur",
				            JOptionPane.ERROR_MESSAGE
				        );
				        return;
				    }	
	
<<<<<<< HEAD
					try {
						//LA ICIIII
						FenMesReleves fenMesReleve = new FenMesReleves(this.compteur);
=======
					try {	
						FenMesReleves fenMesReleve = new FenMesReleves(this.compteur);
						
>>>>>>> c1f09023258610508133b9bf01bcd162c66afc9e
						
						fenComp.getLayeredPane().add(fenMesReleve);
		
						DefaultTableModel modeleTableReleve = (DefaultTableModel) fenMesReleve.getTabMesReleves().getModel();
						
					    List<Releve> mesReleves = this.daoReleve.findAllById(this.compteur.getIdCompteur());

	
					    modeleTableReleve.setRowCount(mesReleves.size());
	
					    for (int i = 0; i < mesReleves.size(); i++) {
					        Releve releve = mesReleves.get(i);
					        modeleTableReleve.setValueAt(releve.getCompteur().getIdCompteur(), i, 0);
					        modeleTableReleve.setValueAt(releve.getDateReleve(), i, 1);
					        modeleTableReleve.setValueAt(releve.getIndexReleve(), i, 2);
					    }

					fenMesReleve.setVisible(true);
					break;

					}catch (Exception e2) {
						System.out.println(e2.getMessage());  
						e2.printStackTrace();
					}	
					
				case "Ajouter":
					System.out.println("Vous charger la page ajouter un Compteur !");
					try {
						FenAjoutCompteur fenaddComp = null;	
						
						fenaddComp = new FenAjoutCompteur();
						
						fenComp.getLayeredPane().add(fenaddComp);					
						fenaddComp.setVisible(true);
					}catch (Exception e2) {
						e2.printStackTrace();
					}	
					break;
				
				case "Charger":
					DefaultTableModel modeleTableCompteur = (DefaultTableModel) fenComp.gettabMesCompteurs().getModel();	
					List<Compteur> mesDonnees = null;
					
					try {
						mesDonnees = this.daoCompteur.findAll();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
	
					Iterateur<Compteur> it = DaoCompteur.getIterateurCompteur();					
			        if (it == null) {
			            System.out.println("Itérateur non initialisé !");
			            break;
			        }
			        
					modeleTableCompteur.setRowCount(mesDonnees.size());  	
					int count = 0;
					while(it.hasNext() && count < mesDonnees.size()) {	
						Compteur compteur = it.next();						
						this.ecrireLigneTable(compteur, count);
						count++;
					}
					break;
					
					
				default:
					System.out.println("Action non reconnu !");
			}
		}else {
			System.out.println("Source non reconnu !");
		}
	}
	
	public void ecrireLigneTable(Compteur compteur, int numeroLigne) {
		DefaultTableModel modeleTable = (DefaultTableModel) this.fenComp.gettabMesCompteurs().getModel();

		modeleTable.setValueAt(compteur.getIdCompteur(), numeroLigne, 0);
		modeleTable.setValueAt(compteur.getTypeComp(), numeroLigne, 1);
		modeleTable.setValueAt(compteur.getImmeuble() != null  ? compteur.getImmeuble().getIdLogement() : "NA",  numeroLigne,  2);
		modeleTable.setValueAt(compteur.getBien().getIdBien(), numeroLigne, 3);
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		JTable tabCompteur = this.fenComp.gettabMesCompteurs();
		int selectedRow = tabCompteur.getSelectedRow();
		
		if (selectedRow > -1) {
			try {
				this.compteur = daoCompteur.findById(tabCompteur.getValueAt(selectedRow, 0).toString());
			}catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}
}
