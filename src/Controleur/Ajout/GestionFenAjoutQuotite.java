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

public class GestionFenAjoutQuotite implements ActionListener{

	private FenAjoutQuotite fenAddQuot;
	private Logement logement;

	
	public GestionFenAjoutQuotite(FenAjoutQuotite fenAddQuot) {
		this.fenAddQuot = fenAddQuot;
		this.logement = null;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
        String texte = ((RoundedButton) source).getText();
        
        if (texte != null) {   	
            switch (texte) {
	            case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Facture !");
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

}
