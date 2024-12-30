package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.ICC;

public class RequeteSelectICC extends Requete<ICC>{

	public String requete() {
		return "Select * from icc ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, ICC donnee) throws SQLException {
    }
}
