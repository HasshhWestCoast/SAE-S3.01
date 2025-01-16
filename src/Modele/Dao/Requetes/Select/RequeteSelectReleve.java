package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Releve;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectReleve extends Requete<Releve> {
	   
	@Override
	 public String requete() {
	     return "SELECT * FROM SAE_RELEVÉ ";
	 }
	
	 @Override
	 public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	 }
	 
	 @Override
	 public void parametres(PreparedStatement prSt, Releve donnee) throws SQLException {
	 }

}
