package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Modele.Dao.CictOracleDataSource;
import Vue.FenAccueil;
import Vue.FenConnexion;
import Vue.RoundedButton;

public class GestionFenConnexion implements ActionListener{

	private FenConnexion fenCn;
	
	public GestionFenConnexion(FenConnexion fenCn) {
		this.fenCn = fenCn;
	}
	
	public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			String texte = ((RoundedButton) source).getText();
			
			switch (texte) {
			case "Annuler":
				System.out.println("bouton annuler");
				fenCn.dispose();
				break;
				
			case "Se connecter":
				System.out.println("bouton connecter");
				
				try {

					String login = fenCn.getValeurChLogin();
					String mdp = this.fenCn.getValeurPasswordField();
					
					CictOracleDataSource coBd = CictOracleDataSource.getInstance();
					coBd.creerAcces(login, mdp);					
					
					if (coBd.getConnection() != null) {
						FenAccueil fenAc = new FenAccueil();
						fenAc.getLayeredPane();
						fenAc.setVisible(true);
					}
					
					fenCn.dispose();

				}catch (SQLException ex) {
                    JOptionPane.showMessageDialog(fenCn, 
                            "Login ou mot de passe incorrect : " + ex.getMessage(), "Erreur de connexion", 
                            JOptionPane.ERROR_MESSAGE);
                }
				break;
			default:
				System.out.println("bouton non reconnu !");

		}
	}
}