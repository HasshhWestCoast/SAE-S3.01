package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Bien;

public class RequeteSelectBienById extends Requete<Bien> {
	   
	@Override
	 public String requete() {
	     return "SELECT * FROM Bien " +
	            "WHERE Id_bien = ? "+ 
	    		 "AND id_Logement = ?";
	 }
	
	 @Override
	 public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	     if (id.length == 2) {
	         prSt.setString(1, id[0]); 
	         prSt.setString(2, id[1]);
	     } else {
	         throw new IllegalArgumentException("Le nombre d'identifiants pour Bien est incorrect.");
	     }
	 }

}
