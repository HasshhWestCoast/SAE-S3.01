package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Bien;

public class RequeteSelectBienById extends Requete<Bien> {
	   
	@Override
	 public String requete() {
	     return "SELECT * FROM Bien " +
	            "WHERE Id_bien = ? ";
	 }
	
	 @Override
	 public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	     if (id.length == 1) {
	         prSt.setString(1, id[0]); 
	     } else {
	         throw new IllegalArgumentException("Le nombre d'identifiants pour Bien est incorrect.");
	     }
	 }

}
