package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Compteur;

public class RequeteSelectCompteurById extends Requete<Compteur> {
	   
		@Override
	    public String requete() {
	        return "SELECT * FROM Ccompteur " +
	               "WHERE Id_Compteur = ? ";
	    }

	    @Override
	    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	        if (id.length == 1) {
	            prSt.setString(1, id[0]); 
	            
	        } else {
	            throw new IllegalArgumentException("Le nombre d'identifiants pour Compteur est incorrect.");
	        }
	    }

	}