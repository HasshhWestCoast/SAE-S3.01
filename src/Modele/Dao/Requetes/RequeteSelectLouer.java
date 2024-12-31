package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Louer;

public class RequeteSelectLouer  extends Requete<Louer>{

	public String requete() {
		return "Select * from Sae_louer ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, Louer donnee) throws SQLException {
    }
}