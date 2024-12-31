package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Diagnostic;

public class RequeteSelectDiagnosticById  extends Requete<Diagnostic> {
	   
		@Override
	    public String requete() {
	        return "SELECT * FROM Sae_diagnostic " +
	               "WHERE id_Diagnostic = ? " +
	        	   "AND Id_bien = ?";
	    }

	    @Override
	    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	        if (id.length == 2) {
	            prSt.setString(1, id[0]); 
	            prSt.setString(2, id[1]); 
	            
	        } else {
	            throw new IllegalArgumentException("Le nombre d'identifiants pour Diagnostic est incorrect.");
	        }
	    }

}