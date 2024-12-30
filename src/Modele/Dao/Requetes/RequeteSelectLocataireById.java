package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Locataire;

public class RequeteSelectLocataireById extends Requete<Locataire> {
	   
		@Override
	    public String requete() {
	        return "SELECT * FROM Locataire " +
	               "WHERE Id_Locataire = ? ";
	    }

	    @Override
	    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	        if (id.length == 1) {
	            prSt.setString(1, id[0]); 
	            
	        } else {
	            throw new IllegalArgumentException("Le nombre d'identifiants pour Locataire est incorrect.");
	        }
	    }

	}