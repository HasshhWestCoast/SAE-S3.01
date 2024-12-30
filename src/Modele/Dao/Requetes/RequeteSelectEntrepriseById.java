package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Entreprise;

public class RequeteSelectEntrepriseById extends Requete<Entreprise> {
	   
	@Override
	 public String requete() {
	     return "SELECT * FROM Entreprise " +
	            "WHERE SIRET = ? ";
	 }
	
	 @Override
	 public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	     if (id.length == 1) {
	         prSt.setString(1, id[0]); 
	         
	     } else {
	         throw new IllegalArgumentException("Le nombre d'identifiants pour Entreprise est incorrect.");
	     }
	 }
}
