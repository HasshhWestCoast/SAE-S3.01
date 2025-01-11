package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Louer;

public class RequeteSelectLouerById extends Requete<Louer> {
	   
	@Override
	 public String requete() {
	     return "SELECT * FROM Sae_Louer " +
	            "WHERE Id_bien = ? "+ 
	             "AND Id_Locataire = ? " + 
	    		 "AND Date_debut = ? ";
	 }
	
	 @Override
	 public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	     if (id.length == 3) {
	         prSt.setString(1, id[0]); 
	         prSt.setString(2, id[1]);
	         prSt.setString(3, id[2]);

	         
	     } else {
	         throw new IllegalArgumentException("Le nombre d'identifiants pour Louer est incorrect.");
	     }
	 }

}
