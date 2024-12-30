package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Impot;

public class RequeteSelectImpot extends Requete<Impot>{

	public String requete() {
		return "Select * from Impot ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, Impot donnee) throws SQLException {
    }
}