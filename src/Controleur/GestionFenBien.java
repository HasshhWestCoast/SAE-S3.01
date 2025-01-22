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
import Vue.FenCompteurs;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutBien;
import Vue.Insertion.FenAjoutCharge;
import Vue.Insertion.FenAjoutDiagnostic;
import Vue.Insertion.FenAjoutFacture;
import rapport.CreerRapport;

public class GestionFenBien implements ActionListener, ListSelectionListener {

    private FenAccueil fenAc;
    private DaoBien daoBien;
    private Bien bien;
    
    /**
     * Initialise l'accès aux données et stocke la fenêtre principale.
     */
    public GestionFenBien(FenAccueil fenAc) throws SQLException {
        this.fenAc = fenAc;
        this.daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());
        this.bien = null;
    }
    
    /**
     * Déclenchée quand un bouton est cliqué. 
     * Traite différentes actions : Charger, Ajouter, Modifier, Supprimer, etc.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText();
        
        // Modèle du tableau où s'affichent les biens
        DefaultTableModel modeleTable = (DefaultTableModel) this.fenAc.getTabMesBiens().getModel();
        
        if (texte != null) {
            switch (texte) {
                case "Modifier":
                    // Lance la procédure de modification d'un bien (non implémenté)
                    System.out.println("Vous MODIFIER une donnée dans Bien !");
                    break;
                    
                case "Supprimer":
                    // Supprime le bien sélectionné et met à jour le tableau
                    System.out.println("Vous SUPPRIMER une données dans Bien !");
                    int ligneSelectionnee = this.fenAc.getTabMesBiens().getSelectedRow();
                    try {
                        // Récupère l'ID du bien sélectionné dans le tableau
                        String idBien = (String) this.fenAc.getTabMesBiens().getValueAt(ligneSelectionnee, 0);
                        Bien bien = this.daoBien.findById(idBien);
                        
                        // Suppression en base et retrait de la ligne dans le tableau
                        this.daoBien.delete(bien);
                        modeleTable.removeRow(ligneSelectionnee);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                
                case "Archiver":
                    // Marque le bien sélectionné comme archivé (non implémenté)
                    System.out.println("Vous ARCHIVER une donnée prevenant de Bien !");
                    if (this.bien == null) {
                        JOptionPane.showMessageDialog(
                            this.fenAc,
                            "Veuillez sélectionner un bien a archiver !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }  
                    DefaultTableModel modeleTableArchives = (DefaultTableModel) this.fenAc.getTabBienArchives().getModel();
                    
                    String[] EngrBien = {this.bien.getIdBien(), this.bien.getAdresse(), this.bien.getVille(), this.bien.getCodePostal(), this.bien.getTypeBien()};
                    modeleTableArchives.addRow(EngrBien);
                    
                    break;
                    
                case "Charger":
                    // Récupère et affiche tous les biens dans le tableau
                    System.out.println("Vous CHARGER les donnée dans Bien !");
                    try {
                        List<Bien> mesDonnees = this.daoBien.findAll();
                        Iterateur<Bien> it = DaoBien.getIterateurBien();
                        if (it == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }
                        // Adapte le nombre de lignes du tableau au nombre de biens récupérés
                        modeleTable.setRowCount(mesDonnees.size());
                        
                        // Parcourt l'itérateur pour remplir chaque ligne
                        int count = 0;
                        while (it.hasNext() && count < mesDonnees.size()) {
                            Bien bien = it.next();
                            this.ecrireLigneTable(bien, count);
                            count++;
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                    
                case "Afficher les compteurs":
                    // Ouvre la fenêtre qui affiche les compteurs liés aux biens
                    System.out.println("Vous AFFICHER LES COMPTEURS depuis Bien !");
                    FenCompteurs fenCompMesBien = null;
                    try {
                        fenCompMesBien = new FenCompteurs();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    fenAc.getLayeredPane().add(fenCompMesBien);
                    fenCompMesBien.setVisible(true);
                    break;
                    
                case "Ajouter un bien":
                    // Ouvre la fenêtre pour ajouter un nouveau bien
                    System.out.println("Vous AJOUTER UN BIEN dans Bien !");
                    FenAjoutBien fenAddBien = new FenAjoutBien();
                    fenAc.getLayeredPane().add(fenAddBien);
                    fenAddBien.setVisible(true);
                    break;
                    
                case "Generer un word":
                    
                    try {
                        // Appeler la méthode de génération du rapport
                        CreerRapport.genererRapportBien(daoBien.findAll());
                        JOptionPane.showMessageDialog(
                            this.fenAc,
                            "Rapport généré avec succès dans src/rapport/test.docx",
                            "Succès",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                            this.fenAc,
                            "Une erreur est survenue lors de la génération du rapport : " + ex.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        ex.printStackTrace();
                    }
                    break;

                    
                    
                    
                case "Ajouter des factures":
                    // Ouvre la fenêtre pour ajouter des factures au bien sélectionné
                    System.out.println("Vous AJOUTER DES FACTURES depuis Bien !");
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
                        e1.printStackTrace();
                    }
                    fenAc.getLayeredPane().add(fenAddFacture);
                    fenAddFacture.setVisible(true);
                    break;
                
                case "Ajouter un diagnostic":
                	System.out.println("Vous ajouter un diagnostic  depuis Bien !");
                	if (this.bien == null) {
                        JOptionPane.showMessageDialog(
                            this.fenAc,
                            "Veuillez sélectionner un bien !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                	FenAjoutDiagnostic fenAddDiag = null;
                   
                	fenAddDiag = new FenAjoutDiagnostic(this.bien);
                    
                    fenAc.getLayeredPane().add(fenAddDiag);
                    fenAddDiag.setVisible(true);
                	break;

                case "Ajouter une charge":
                	System.out.println("Vous ajouter une charge depuis Bien !");
                	if (this.bien == null) {
                        JOptionPane.showMessageDialog(
                            this.fenAc,
                            "Veuillez sélectionner un bien !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                	
                	FenAjoutCharge fenAddCharge = null;
                    
                	fenAddCharge = new FenAjoutCharge(this.bien);
                    
                    fenAc.getLayeredPane().add(fenAddCharge);
                    fenAddCharge.setVisible(true);
                	break;
                	
                default:
                    System.out.println("Action non reconnu !");
            }
        } else {
            System.out.println("Source non reconnu !");
        }
    }
    
    /**
     * Écrit les informations d'un bien dans une ligne du tableau.
     */
    public void ecrireLigneTable(Bien bien, int numeroLigne) {
        DefaultTableModel modeleTable = 
            (DefaultTableModel) this.fenAc.getTabMesBiens().getModel();
        
        // Alimente chaque colonne du tableau avec les attributs du bien
        modeleTable.setValueAt(bien.getIdBien(), numeroLigne, 0);
        modeleTable.setValueAt(bien.getAdresse(), numeroLigne, 1);
        modeleTable.setValueAt(bien.getVille(), numeroLigne, 2);
        modeleTable.setValueAt(bien.getCodePostal(), numeroLigne, 3);
        modeleTable.setValueAt(bien.getTypeBien(), numeroLigne, 4);
        modeleTable.setValueAt(bien.getPeriodeConstruction(), numeroLigne, 5);
    }

    /**
     * Appelée quand la sélection d'une ligne du tableau change : 
     * met à jour l'attribut <code>bien</code> avec l'élément choisi.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JTable tabBien = this.fenAc.getTabMesBiens();
        int selectedRow = tabBien.getSelectedRow();
        if (selectedRow > -1) {
            try {
                String id = tabBien.getValueAt(selectedRow, 0).toString();
                this.bien = daoBien.findById(id);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
