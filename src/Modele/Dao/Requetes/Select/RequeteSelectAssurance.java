package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.assurance;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectAssurance extends Requete<assurance>{

	public String requete() {
		return "Select * from Sae_assurance ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, assurance donnee) throws SQLException {
    }
}
