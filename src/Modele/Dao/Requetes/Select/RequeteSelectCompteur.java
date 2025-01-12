package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Compteur;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectCompteur extends Requete<Compteur> {
	   
	@Override
	 public String requete() {
	     return "SELECT * FROM Sae_Compteur ";
	 }
	
	 @Override
	 public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	 }
	 
	 @Override
	 public void parametres(PreparedStatement prSt, Compteur donnee) throws SQLException {
	 }

}
