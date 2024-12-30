package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Locataire;

public class RequeteSelectLocataire extends Requete<Locataire>{

	public String requete() {
		return "Select * from locataire ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, Locataire donnee) throws SQLException {
    }
}

