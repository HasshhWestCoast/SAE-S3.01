package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Diagnostic;

public class RequeteSelectDiagnostic extends Requete<Diagnostic>{

	public String requete() {
		return "Select * from diagnostic ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, Diagnostic donnee) throws SQLException {
    }
}
