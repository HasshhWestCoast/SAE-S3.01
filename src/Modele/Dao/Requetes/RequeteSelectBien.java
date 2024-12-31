package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Bien;

public class RequeteSelectBien extends Requete<Bien> {
	   
	@Override
	 public String requete() {
	     return "SELECT * FROM SAE_Bien ";
	 }
	
	 @Override
	 public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	 }
	 
	 @Override
	 public void parametres(PreparedStatement prSt, Bien donnee) throws SQLException {
	 }

}
