package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Charge;

public class RequeteSelectChargeById extends Requete<Charge> {
	   
		@Override
	    public String requete() {
	        return "SELECT * FROM Sae_charge " +
	               "WHERE id_charges = ? " + 
	        		"AND id_Bien = ?";
	    }

	    @Override
	    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	        if (id.length == 2) {
	            prSt.setString(1, id[0]); 
	            prSt.setString(2, id[1]); 
	            
	        } else {
	            throw new IllegalArgumentException("Le nombre d'identifiants pour Charge est incorrect.");
	        }
	    }

	}
