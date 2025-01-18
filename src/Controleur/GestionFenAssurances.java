package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import Modele.assurance;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoAssurance;
import Modele.Dao.Iterateur;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutAssurance;

/**
 * La classe <code>GestionFenAssurances</code> gère toutes les actions de 
 */
public class GestionFenAssurances implements ActionListener {

    // Référence à la fenêtre principale de l'application
    private FenAccueil fenAc;
    
    // Accès à l'objet DAO pour les opérations CRUD sur la table Assurance
    private DaoAssurance daoAssurance;

    /**
     * Constructeur de la classe GestionFenAssurances.
     */
    public GestionFenAssurances(FenAccueil fenAc) throws SQLException {
        // On récupère la référence à la FenAccueil
        this.fenAc = fenAc;
        // Initialisation du DAO pour les assurances
        this.daoAssurance = new DaoAssurance(CictOracleDataSource.getInstance().getConnection());
    }
    
    /**
     * Méthode déclenchée lorsqu'une action est réalisée sur l'interface.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Récupère l'objet source de l'événement
        Object source = e.getSource();
        // On caste en RoundedButton pour obtenir son texte
        String texte = ((RoundedButton) source).getText();
        
        // Récupère le modèle du tableau des assurances depuis fenAc
        DefaultTableModel modeleTable = 
            (DefaultTableModel) this.fenAc.getTabMesAssurances().getModel();

        // Vérifie que le texte du bouton n'est pas nul
        if (texte != null) {
            switch (texte) {
                case "Charger":
                    System.out.println("Vous CHARGER les données dans Assurance !");
                    try {
                        // Récupération de toutes les assurances en base
                        List<assurance> mesDonnees = this.daoAssurance.findAll();
                        
                        // On utilise l'itérateur du DaoAssurance pour parcourir ces données
                        Iterateur<assurance> it = DaoAssurance.getIterateurAssurance();
                        if (it == null) {
                            System.out.println("Itérateur non initialisé !");
                            break;
                        }
                        
                        // Ajuste la taille du tableau à la taille de la liste retournée
                        modeleTable.setRowCount(mesDonnees.size());

                        int count = 0;
                        // Parcours de l'itérateur pour remplir le tableau
                        while (it.hasNext() && count < mesDonnees.size()) { 
                            assurance ass = it.next();
                            this.ecrireLigneTable(ass, count);
                            count++;
                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;
                    
                case "Inserer":
                    System.out.println("Vous INSERER une donnée dans Assurances !");
                    FenAjoutAssurance fenAddAssurance = null;
                    
                    try {
                        // Instanciation de la fenêtre d'ajout d'assurances
                        fenAddAssurance = new FenAjoutAssurance();
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                        e1.printStackTrace();
                    }

                    // On définit la taille et la position de la fenêtre d'ajout
                    int width = fenAc.getLayeredPane().getWidth();
                    int height = fenAc.getLayeredPane().getHeight();
                    fenAddAssurance.setBounds(50, 50, width - 100, height - 100);

                    // On ajoute la fenêtre sur le layeredPane de la fenêtre d'accueil
                    fenAc.getLayeredPane().add(fenAddAssurance);
                    fenAddAssurance.setVisible(true);
                    break;
                    
                default:
                    System.out.println("Action non reconnu !");
            }
        } else {
            System.out.println("Source non reconnu !");
        }
    }
    
    /**
     * Méthode utilitaire pour écrire les données d'un objet 
     * dans une ligne du tableau.
     */
    public void ecrireLigneTable(assurance ass, int numeroLigne) {
        // Récupère le modèle de tableau depuis la fenêtre d'accueil
        DefaultTableModel modeleTable = 
            (DefaultTableModel) this.fenAc.getTabMesAssurances().getModel();

        // Remplit chaque cellule de la ligne avec les attributs de l'assurance
        modeleTable.setValueAt(ass.getNuméroPolice(), numeroLigne, 0);
        modeleTable.setValueAt(ass.getMontant(), numeroLigne, 1);
        modeleTable.setValueAt(ass.getDateEcheance(), numeroLigne, 2);
        modeleTable.setValueAt(ass.getEntreprise().getSiret(), numeroLigne, 3);

        // Vérifie si les objets Logement et Bien sont présents
        modeleTable.setValueAt(
            (ass.getLogement() != null) ? ass.getLogement().getIdLogement() : "NA", 
            numeroLigne, 4
        );
        modeleTable.setValueAt(
            (ass.getBien() != null) ? ass.getBien().getIdBien() : "NA", 
            numeroLigne, 5
        );
    }
}
