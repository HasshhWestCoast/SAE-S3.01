package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Diagnostic;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectDiagnostic extends Requete<Diagnostic>{

	public String requete() {
		return "Select * from Sae_diagnostic ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, Diagnostic donnee) throws SQLException {
    }
}
