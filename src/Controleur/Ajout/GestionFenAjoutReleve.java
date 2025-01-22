package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Modele.Releve;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoReleve;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutReleves;

/**
 * Contrôleur pour gérer les actions liées à l'ajout d'un relevé.
 */
public class GestionFenAjoutReleve implements ActionListener {

    private FenAjoutReleves fenAddReleves; // Référence à la fenêtre d'ajout de relevé

    /**
     * Constructeur de la classe.
     *
     * @param fenAddReleves Fenêtre associée à l'ajout d'un relevé.
     * @throws SQLException En cas de problème de connexion à la base de données.
     */
    public GestionFenAjoutReleve(FenAjoutReleves fenAddReleves) throws SQLException {
        this.fenAddReleves = fenAddReleves;
    }

    /**
     * Gère les actions déclenchées par les boutons de l'interface utilisateur.
     *
     * @param e L'événement associé à l'action utilisateur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText(); // Récupération du texte du bouton cliqué

        if (texte != null) {
            switch (texte) {
                case "Annuler":
                    // Action pour fermer la fenêtre d'ajout
                    System.out.println("Vous FERMEZ la page ajout compteur");
                    this.fenAddReleves.dispose();
                    break;

                case "Ajouter":
                    // Ajouter un relevé pour un compteur
                    System.out.println("Vous AJOUTER un relevé pour un compteur !");
                    try {
                        // Récupération des données saisies par l'utilisateur
                        String DateReleve = (String) fenAddReleves.getDateReleves();
                        String IndexString = (String) fenAddReleves.getIndex();
                        int Index = Integer.parseInt(IndexString); // Conversion en entier

                        // Création du DAO pour les relevés
                        DaoReleve daoReleve = new DaoReleve(CictOracleDataSource.getInstance().getConnection());

                        // Création et insertion du relevé
                        Releve releve = new Releve(DateReleve, Index, fenAddReleves.getCompteur());
                        daoReleve.create(releve);

                        // Fermeture de la fenêtre après ajout
                        fenAddReleves.dispose();

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
