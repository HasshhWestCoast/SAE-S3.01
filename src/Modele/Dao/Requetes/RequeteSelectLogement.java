package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Logement;

public class RequeteSelectLogement extends Requete<Logement>{

	public String requete() {
		return "Select * from Logement ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, Logement donnee) throws SQLException {
    }
}
