package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.assurance;

public class RequeteSelectCreneau extends Requete<assurance>{

	public String requete() {
		return "Select * from assurance ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, assurance donnee) throws SQLException {
    }
}
