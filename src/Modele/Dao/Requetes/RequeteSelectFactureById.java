package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Facture;


public class RequeteSelectFactureById extends Requete<Facture> {
	   
		@Override
	    public String requete() {
	        return "SELECT * FROM Sae_facture " +
	               "WHERE Id_Facture = ? " +
	        	    "AND SIRET = ? " +
	                "AND Id_logement = ? " +
	        	    "AND Id_Bien = ?";
	    }

	    @Override
	    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	        if (id.length == 4) {
	            prSt.setString(1, id[0]);
	            prSt.setString(2, id[1]); 
	            prSt.setString(3, id[2]); 
	            prSt.setString(4, id[3]); 
	            
	        } else {
	            throw new IllegalArgumentException("Le nombre d'identifiants pour Facture est incorrect.");
	        }
	    }

	}