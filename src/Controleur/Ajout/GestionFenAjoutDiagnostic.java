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

public class GestionFenAjoutDiagnostic  implements ActionListener{

	private FenAjoutDiagnostic fenAddDiag;
	private Bien bien;

	
	public GestionFenAjoutDiagnostic(FenAjoutDiagnostic fenAddDiag) {
		this.fenAddDiag = fenAddDiag;
		this.bien = null;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
        String texte = ((RoundedButton) source).getText();
        
        if (texte != null) {   	
            switch (texte) {
	            case "Annuler":
					System.out.println("Vous FERMEZ la page ajout Facture !");
					this.fenAddDiag.dispose();
					break;
					
	            case "Ajouter":
					System.out.println("Vous Ajouter la page ajout Facture !");
					
					if (this.fenAddDiag.getDate().isEmpty() ||
					        this.fenAddDiag.getType().isEmpty() ||
					        this.fenAddDiag.getIdDiagnostic().isEmpty())
					    {
					        JOptionPane.showMessageDialog(
					            this.fenAddDiag,
					            "Veuillez remplir tous les champs requis !",
					            "Erreur",
					            JOptionPane.ERROR_MESSAGE
					        );
					        return;
					    }
					
					try {
				        String IdDiagnostic = (String) fenAddDiag.getIdDiagnostic();                    
				        String Type = (String) fenAddDiag.getType();
				        String Date = (String) fenAddDiag.getDate();
				        
				        bien = this.fenAddDiag.getPrecedent();
				        DaoDiagnostic daoDiagnostic = new DaoDiagnostic(CictOracleDataSource.getInstance().getConnection());
				        
				        Diagnostic diagnostic = new Diagnostic(IdDiagnostic, Date, Type, bien);
				        daoDiagnostic.create(diagnostic);
				        this.fenAddDiag.dispose();
				        
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
