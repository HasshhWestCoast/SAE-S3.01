package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Modele.Bien;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoBien;
import Vue.FenAccueil;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutBien;

/**
 * Contrôleur pour gérer les actions liées à l'ajout d'un bien immobilier.
 */
public class GestionFenAjoutBien implements ActionListener {

    private FenAjoutBien fenAjoutBien; 

    /**
     * Constructeur de la classe.
     *
     * @param fenAjoutBien Fenêtre associée à l'ajout d'un bien.
     */
    public GestionFenAjoutBien(FenAjoutBien fenAjoutBien) {
        this.fenAjoutBien = fenAjoutBien;
    }

    /**
     * Gère les actions déclenchées par les boutons de l'interface utilisateur.
     *
     * @param e L'événement associé à l'action utilisateur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText(); // Texte du bouton cliqué

        // Récupération de la fenêtre principale
        FenAccueil fenAC = (FenAccueil) this.fenAjoutBien.getTopLevelAncestor();

        if (texte != null) {
            switch (texte) {
                case "Annuler":
                    // Action pour fermer la fenêtre d'ajout
                    System.out.println("Vous FERMEZ la page ajout Bien");
                    this.fenAjoutBien.dispose();
                    break;

                case "Ajouter":
                    // Action pour ajouter un nouveau bien
                    System.out.println("Vous AJOUTER un bien !");

                    // Vérification que tous les champs requis sont remplis
                    if (this.fenAjoutBien.getTextFieldIdBien().isEmpty() ||
                        this.fenAjoutBien.getTextFieldAdresse().isEmpty() ||
                        this.fenAjoutBien.getTextFieldVille().isEmpty() ||
                        this.fenAjoutBien.getTextFieldCodePostale().isEmpty() ||
                        this.fenAjoutBien.getTextFieldPeriodeConstruction().isEmpty()) {
                        JOptionPane.showMessageDialog(
                            this.fenAjoutBien,
                            "Veuillez remplir tous les champs requis !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    try {
                        // Modèle de la table des biens dans la fenêtre principale
                        DefaultTableModel modeleTable = (DefaultTableModel) fenAC.getTabMesBiens().getModel();

                        // Récupération des données saisies par l'utilisateur
                        String IdBien = (String) fenAjoutBien.getTextFieldIdBien();
                        String Adresse = (String) fenAjoutBien.getTextFieldAdresse();
                        String Ville = (String) fenAjoutBien.getTextFieldVille();
                        String TypeBien = (String) fenAjoutBien.getComboBoxTypeDeBien();
                        String CodePostal = (String) fenAjoutBien.getTextFieldCodePostale();
                        String PeriodeConstruction = (String) fenAjoutBien.getTextFieldPeriodeConstruction();

                        // Création d'une instance du DAO pour les biens
                        DaoBien daoBien = new DaoBien(CictOracleDataSource.getInstance().getConnection());

                        // Création d'une instance de Bien et insertion dans la base de données
                        Bien bien = new Bien(IdBien, Adresse, Ville, TypeBien, CodePostal, PeriodeConstruction);
                        daoBien.create(bien);

                        // Ajout du bien dans le modèle de la table
                        String[] EngrBien = {IdBien, Adresse, Ville, CodePostal, TypeBien, PeriodeConstruction};
                        modeleTable.addRow(EngrBien);

                        // Fermeture de la fenêtre après l'ajout
                        fenAjoutBien.dispose();

                    } catch (SQLException ex) {
                        // Gestion des erreurs SQL
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                    break;

                default:
                    // Message pour une action non reconnue
                    System.out.println("Action non reconnu !");
            }
        } else {
            // Message si la source de l'événement est invalide
            System.out.println("Source non reconnu !");
        }
    }
}
