package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Modele.Bien;
import Modele.Charge;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoCharge;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutCharge;

public class GestionFenAjoutCharge implements ActionListener {

    private FenAjoutCharge fenAddCharge; 
    private Bien bien; 

    /**
     * Constructeur de la classe.
     *
     * @param fenAddCharge Fenêtre associée à l'ajout d'une charge.
     */
    public GestionFenAjoutCharge(FenAjoutCharge fenAddCharge) {
        this.fenAddCharge = fenAddCharge;
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
                    System.out.println("Vous FERMEZ la page ajout diagnostic !");
                    this.fenAddCharge.dispose();
                    break;

                case "Ajouter":
                    // Action pour ajouter une charge
                    System.out.println("Vous Ajouter une Charge depuis Bien !");

                    // Vérification que tous les champs requis sont remplis
                    if (this.fenAddCharge.getIDCharge().isEmpty() ||
                        this.fenAddCharge.getNom().isEmpty() ||
                        this.fenAddCharge.getMontantReel().isEmpty() ||
                        this.fenAddCharge.getMontantPrevisionel().isEmpty() ||
                        this.fenAddCharge.getDeductible().isEmpty() ||
                        this.fenAddCharge.getDateCharge().isEmpty()) {
                        JOptionPane.showMessageDialog(
                            this.fenAddCharge,
                            "Veuillez remplir tous les champs requis !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    try {
                        // Récupération des données saisies par l'utilisateur
                        String IdCharge = (String) fenAddCharge.getIDCharge();
                        String Nom = (String) fenAddCharge.getNom();
                        String MontantReelString = (String) fenAddCharge.getMontantReel();
                        String MontantPrevisionelString = (String) fenAddCharge.getMontantPrevisionel();
                        String DeductibleString = (String) fenAddCharge.getDeductible();
                        String DateCharge = (String) fenAddCharge.getDateCharge();

                        // Association de la charge au bien sélectionné
                        bien = this.fenAddCharge.getBien();

                        // Création d'une instance du DAO pour les charges
                        DaoCharge daoCharge = new DaoCharge(CictOracleDataSource.getInstance().getConnection());

                        // Création et insertion de la charge dans la base de données
                        Charge charge = new Charge(IdCharge, Nom, Double.parseDouble(MontantReelString), Double.parseDouble(MontantPrevisionelString), Integer.parseInt(DeductibleString), DateCharge,bien);
                        daoCharge.create(charge);

                        // Fermeture de la fenêtre après ajout
                        this.fenAddCharge.dispose();

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
