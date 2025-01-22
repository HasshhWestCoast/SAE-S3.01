package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import Modele.Entreprise;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoEntreprise;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutAssurance;
import Vue.Insertion.FenAjoutEntreprise;
import Vue.Insertion.FenAjoutFacture;

/**
 * Contrôleur pour gérer les actions liées à l'ajout d'une entreprise.
 */
public class GestionFenAjoutEntreprise implements ActionListener {

    private FenAjoutEntreprise fenAjoutEntreprise; 

    /**
     * Constructeur de la classe.
     *
     * @param fenEntreprise Fenêtre associée à l'ajout d'une entreprise.
     */
    public GestionFenAjoutEntreprise(FenAjoutEntreprise fenEntreprise) {
        this.fenAjoutEntreprise = fenEntreprise;
    }

    /**
     * Gère les actions déclenchées par les boutons de l'interface utilisateur.
     *
     * @param e L'événement associé à l'action utilisateur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String texte = ((RoundedButton) source).getText(); // Récupère le texte du bouton cliqué

        if (texte != null) {
            switch (texte) {
                case "Annuler":
                    // Action pour fermer la fenêtre d'ajout
                    System.out.println("Vous FERMEZ la page ajout Entreprise !");
                    this.fenAjoutEntreprise.dispose();
                    break;

                case "Ajouter":
                    // Action pour ajouter une entreprise
                    System.out.println("Vous AJOUTER une Entreprise !");
                    try {
                        DefaultTableModel modeleTable = null;

                        // Détermine la fenêtre précédente pour récupérer le bon tableau
                        if (this.fenAjoutEntreprise.getFenPrecedent() instanceof FenAjoutFacture) {
                            FenAjoutFacture fenAjoutFacture = (FenAjoutFacture) this.fenAjoutEntreprise.getFenPrecedent();
                            modeleTable = (DefaultTableModel) fenAjoutFacture.getTabMesEntreprise().getModel();
                        } else if (this.fenAjoutEntreprise.getFenPrecedent() instanceof FenAjoutAssurance) {
                            FenAjoutAssurance fenAjoutAssurance = (FenAjoutAssurance) this.fenAjoutEntreprise.getFenPrecedent();
                            modeleTable = (DefaultTableModel) fenAjoutAssurance.getTabMesEntreprise().getModel();
                        } else {
                            System.out.println("Fenetre precedent pas trouvé break forcé !");
                            break;
                        }

                        // Récupération des données saisies par l'utilisateur
                        String siret = (String) fenAjoutEntreprise.getSIRET();
                        String nom = (String) fenAjoutEntreprise.getNom();
                        String adresse = (String) fenAjoutEntreprise.getAdresse();
                        String codepostal = (String) fenAjoutEntreprise.getCodePostal();
                        String ville = (String) fenAjoutEntreprise.getVille();
                        String mail = (String) fenAjoutEntreprise.getMail();
                        String telephone = (String) fenAjoutEntreprise.getTelephone();
                        String iban = (String) fenAjoutEntreprise.getIBAN();

                        // Création d'une instance du DAO pour les entreprises
                        DaoEntreprise daoEntreprise = new DaoEntreprise(CictOracleDataSource.getInstance().getConnection());

                        // Création et insertion de l'entreprise dans la base de données
                        Entreprise entreprise = new Entreprise(siret, nom, adresse, codepostal, ville, mail, telephone, iban);
                        daoEntreprise.create(entreprise);

                        // Ajout des informations dans le tableau de la fenêtre précédente
                        String[] EngrEntreprise = {siret, nom};
                        modeleTable.addRow(EngrEntreprise);

                        // Fermeture de la fenêtre après ajout
                        fenAjoutEntreprise.dispose();

                    } catch (Exception ex) {
                        // Gestion des erreurs et affichage des messages d'erreur dans la console
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
