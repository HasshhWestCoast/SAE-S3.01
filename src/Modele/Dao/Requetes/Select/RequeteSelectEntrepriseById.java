package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Entreprise;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectEntrepriseById extends Requete<Entreprise> {
	   
	@Override
	 public String requete() {
	     return "SELECT * FROM Sae_Entreprise " +
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
