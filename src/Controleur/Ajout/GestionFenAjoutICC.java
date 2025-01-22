package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Modele.ICC;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoICC;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutICC;

/**
 * Contrôleur pour gérer les actions liées à l'ajout d'un ICC (Indice de Construction).
 */
public class GestionFenAjoutICC implements ActionListener {

    private FenAjoutICC fenAjoutICC; 

    /**
     * Constructeur de la classe.
     *
     * @param fenAjoutICC Fenêtre associée à l'ajout d'un ICC.
     */
    public GestionFenAjoutICC(FenAjoutICC fenAjoutICC) {
        this.fenAjoutICC = fenAjoutICC;
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

        // Récupération du modèle de table pour les ICC
        DefaultTableModel modeleTableIcc = (DefaultTableModel) fenAjoutICC.getFenAjoutLocation().getTabMesICC().getModel();

        if (texte != null) {
            switch (texte) {
                case "Annuler":
                    // Action pour fermer la fenêtre d'ajout
                    System.out.println("Vous FERMEZ la page ajout ICC !");
                    this.fenAjoutICC.dispose();
                    break;

                case "Ajouter":
                    // Action pour ajouter un ICC
                    System.out.println("Vous AJOUTER un ICC !");

                    try {
                        // Récupération des données saisies par l'utilisateur
                        String iccString = (String) fenAjoutICC.getIDIcc();
                        int Idicc = Integer.parseInt(iccString); // Conversion en entier
                        String annee = (String) fenAjoutICC.getAnnee();
                        String trimestre = (String) fenAjoutICC.getTrimestre();
                        String indiceString = (String) fenAjoutICC.getIndice();
                        double indice = Double.parseDouble(indiceString); // Conversion en double

                        // Création d'une instance du DAO pour les ICC
                        DaoICC daoICC = new DaoICC(CictOracleDataSource.getInstance().getConnection());

                        // Création et insertion de l'ICC dans la base de données
                        ICC icc = new ICC(Idicc, annee, trimestre, indice);
                        daoICC.create(icc);

                        // Ajout des données dans le tableau des ICC
                        String[] EngrICC = {iccString, annee, trimestre, indiceString};
                        modeleTableIcc.addRow(EngrICC);

                        // Fermeture de la fenêtre après ajout
                        fenAjoutICC.dispose();

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
