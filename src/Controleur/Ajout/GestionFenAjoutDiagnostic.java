package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import Modele.Bien;
import Modele.Diagnostic;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoDiagnostic;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutDiagnostic;

/**
 * Contrôleur pour gérer les actions liées à l'ajout d'un diagnostic pour un bien.
 */
public class GestionFenAjoutDiagnostic implements ActionListener {

    private FenAjoutDiagnostic fenAddDiag; 
    private Bien bien; 

    /**
     * Constructeur de la classe.
     *
     * @param fenAddDiag Fenêtre associée à l'ajout d'un diagnostic.
     */
    public GestionFenAjoutDiagnostic(FenAjoutDiagnostic fenAddDiag) {
        this.fenAddDiag = fenAddDiag;
        this.bien = null;
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
                    System.out.println("Vous FERMEZ la page ajout Facture !");
                    this.fenAddDiag.dispose();
                    break;

                case "Ajouter":
                    // Action pour ajouter un diagnostic
                    System.out.println("Vous Ajouter la page ajout Facture !");

                    // Vérification que tous les champs requis sont remplis
                    if (this.fenAddDiag.getDate().isEmpty() ||
                        this.fenAddDiag.getType().isEmpty() ||
                        this.fenAddDiag.getIdDiagnostic().isEmpty()) {
                        JOptionPane.showMessageDialog(
                            this.fenAddDiag,
                            "Veuillez remplir tous les champs requis !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    try {
                        // Récupération des données saisies par l'utilisateur
                        String IdDiagnostic = (String) fenAddDiag.getIdDiagnostic();
                        String Type = (String) fenAddDiag.getType();
                        String Date = (String) fenAddDiag.getDate();

                        // Association du diagnostic au bien sélectionné
                        bien = this.fenAddDiag.getPrecedent();

                        // Création d'une instance du DAO pour les diagnostics
                        DaoDiagnostic daoDiagnostic = new DaoDiagnostic(CictOracleDataSource.getInstance().getConnection());

                        // Création et insertion du diagnostic dans la base de données
                        Diagnostic diagnostic = new Diagnostic(IdDiagnostic, Date, Type, bien);
                        daoDiagnostic.create(diagnostic);

                        // Fermeture de la fenêtre après ajout
                        this.fenAddDiag.dispose();

                    } catch (Exception e2) {
                        // Gestion des erreurs et affichage des messages d'erreur dans la console
                        System.out.println(e2.getMessage());
                        e2.printStackTrace();
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
