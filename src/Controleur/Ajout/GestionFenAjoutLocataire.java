package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Modele.Locataire;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoLocataire;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutLocataire;

/**
 * Contrôleur pour gérer les actions liées à l'ajout d'un locataire.
 */
public class GestionFenAjoutLocataire implements ActionListener {

    private FenAjoutLocataire fenAjoutLoc; 

    /**
     * Constructeur de la classe.
     *
     * @param fenAjoutLoc Fenêtre associée à l'ajout d'un locataire.
     */
    public GestionFenAjoutLocataire(FenAjoutLocataire fenAjoutLoc) {
        this.fenAjoutLoc = fenAjoutLoc;
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

        // Récupération du modèle de table pour les locataires
        DefaultTableModel modeleTableLocataire = (DefaultTableModel) fenAjoutLoc.getFenAjoutLocation().getTabMesLocataires().getModel();

        if (texte != null) {
            switch (texte) {
                case "Annuler":
                    // Action pour fermer la fenêtre d'ajout
                    System.out.println("Vous FERMEZ la page ajout Locataire !");
                    this.fenAjoutLoc.dispose();
                    break;

                case "Ajouter":
                    // Action pour ajouter un locataire
                    System.out.println("Vous AJOUTER une Locataire !");

                    try {
                        // Récupération des données saisies par l'utilisateur
                        String IdLocataire = (String) fenAjoutLoc.getIDLocataire();
                        String Nom = (String) fenAjoutLoc.getNom();
                        String Prenom = (String) fenAjoutLoc.getPrenom();
                        String Telephone = (String) fenAjoutLoc.getTelephone();
                        String Mail = (String) fenAjoutLoc.getMail();
                        String DateDeNaissance = (String) fenAjoutLoc.getDateNaissance();

                        // Conversion du statut colocataire en entier (0 ou 1)
                        boolean ColocataireString = (boolean) fenAjoutLoc.getcheckLoyerPayer();
                        int colocataire = ColocataireString ? 1 : 0;

                        // Création d'une instance du DAO pour les locataires
                        DaoLocataire daoLocataire = new DaoLocataire(CictOracleDataSource.getInstance().getConnection());

                        // Création et insertion du locataire dans la base de données
                        Locataire locataire = new Locataire(IdLocataire, Nom, Prenom, Telephone, Mail, DateDeNaissance, colocataire);
                        daoLocataire.create(locataire);

                        // Ajout des données dans le tableau des locataires
                        String[] EngrLocataire = {IdLocataire, Nom};
                        modeleTableLocataire.addRow(EngrLocataire);

                        // Fermeture de la fenêtre après ajout
                        fenAjoutLoc.dispose();

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
