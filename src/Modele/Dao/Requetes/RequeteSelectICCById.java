package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.ICC;

public class RequeteSelectICCById  extends Requete<ICC> {
	   
		@Override
	    public String requete() {
	        return "SELECT * FROM Sae_icc " +
	               "WHERE annee = ? " +
	        	   "AND trimestre = ?";
	    }

	    @Override
	    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	        if (id.length == 2) {
	            prSt.setString(1, id[0]);
	            prSt.setString(2, id[1]); 
	            
	        } else {
	            throw new IllegalArgumentException("Le nombre d'identifiants pour ICC est incorrect.");
	        }
	    }

	}