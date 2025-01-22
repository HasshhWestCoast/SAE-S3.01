package Controleur.Ajout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Modele.Logement;
import Modele.Quotite;
import Modele.Quotter;
import Modele.Dao.CictOracleDataSource;
import Modele.Dao.DaoQuotite;
import Modele.Dao.DaoQuotter;
import Vue.RoundedButton;
import Vue.Insertion.FenAjoutQuotite;

/**
 * Contrôleur pour gérer les actions liées à l'ajout de quotités pour un logement.
 */
public class GestionFenAjoutQuotite implements ActionListener {

    private FenAjoutQuotite fenAddQuot; 
    private Logement logement; 

    /**
     * Constructeur de la classe.
     *
     * @param fenAddQuot Fenêtre associée à l'ajout d'une quotité.
     */
    public GestionFenAjoutQuotite(FenAjoutQuotite fenAddQuot) {
        this.fenAddQuot = fenAddQuot;
        this.logement = null;
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
<<<<<<< HEAD
	            case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Quotite !");
					this.fenAddQuot.dispose();
					break;
					
	            case "Ajouter":
					System.out.println("Vous Ajouter une Quotite à un Logement !");
					
					if (this.fenAddQuot.getTypeQuotite().isEmpty() ||
					        this.fenAddQuot.getPourcentage().isEmpty())
					    {
					        JOptionPane.showMessageDialog(
					            this.fenAddQuot,
					            "Veuillez remplir tous les champs requis !",
					            "Erreur",
					            JOptionPane.ERROR_MESSAGE
					        );
					        return;
					    }
					
					try {
				        String tpeQuotite = (String) fenAddQuot.getTypeQuotite();                    
				        String PourcentageString = (String) fenAddQuot.getPourcentage();
				        int Pourcentage = Integer.parseInt(PourcentageString);
				        
				        logement = this.fenAddQuot.getMonLogement();
				        DaoQuotite daoQuotite = new DaoQuotite(CictOracleDataSource.getInstance().getConnection());
				        
				        Quotite quot = new Quotite(tpeQuotite);
				        daoQuotite.create(quot);
				        
				        DaoQuotter daoQuotter = new DaoQuotter(CictOracleDataSource.getInstance().getConnection());
				        Quotter quotter = new Quotter(Pourcentage, logement, quot);
				        daoQuotter.create(quotter);
				        
				        this.fenAddQuot.dispose();
				        
					}catch (Exception e2) {
						System.out.println(e2.getMessage());
						e2.printStackTrace();
					}
					break;
					
	            default:
					System.out.println("Action non reconnu !");
					
            }
        }else {
    		System.out.println("Source non reconnu !");
    	}	
}
=======
                case "Annuler":
                    // Action pour fermer la fenêtre
                    System.out.println("Vous FERMEZ la page ajout Facture !");
                    this.fenAddQuot.dispose();
                    break;
>>>>>>> d5f0cb3890b9bac0bead2e5121d44c49e14c47f3

                case "Ajouter":
                    // Ajouter une quotité au logement
                    System.out.println("Vous Ajouter une Quotite à un Logement !");

                    // Vérification des champs requis
                    if (this.fenAddQuot.getTypeQuotite().isEmpty() || this.fenAddQuot.getPourcentage().isEmpty()) {
                        JOptionPane.showMessageDialog(
                            this.fenAddQuot,
                            "Veuillez remplir tous les champs requis !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    try {
                        // Récupération des données saisies
                        String tpeQuotite = (String) fenAddQuot.getTypeQuotite();
                        String PourcentageString = (String) fenAddQuot.getPourcentage();
                        int Pourcentage = Integer.parseInt(PourcentageString);

                        // Association du logement
                        logement = this.fenAddQuot.getMonLogement();

                        // Création de la quotité
                        DaoQuotite daoQuotite = new DaoQuotite(CictOracleDataSource.getInstance().getConnection());
                        Quotite quot = new Quotite(tpeQuotite);
                        daoQuotite.create(quot);

                        // Création de la liaison (quotité et logement)
                        DaoQuotter daoQuotter = new DaoQuotter(CictOracleDataSource.getInstance().getConnection());
                        Quotter quotter = new Quotter(Pourcentage, logement, quot);
                        daoQuotter.create(quotter);

                        // Fermeture de la fenêtre après ajout
                        this.fenAddQuot.dispose();

                    } catch (Exception e2) {
                        // Gestion des erreurs
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
