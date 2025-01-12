package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Entreprise;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectEntreprise extends Requete<Entreprise> {
		   
		@Override
		 public String requete() {
		     return "SELECT * FROM Sae_Entreprise ";
		 }
		
	    @Override
	    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	    }
	    
	    @Override
	    public void parametres(PreparedStatement prSt, Entreprise donnee) throws SQLException {
	    }
}
