package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Louer;
import Modele.Dao.Requetes.Requete;

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