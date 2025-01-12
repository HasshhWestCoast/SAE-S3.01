package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Facture;
import Modele.Dao.Requetes.Requete;


public class RequeteSelectFactureById extends Requete<Facture> {
	   
		@Override
	    public String requete() {
	        return "SELECT * FROM Sae_facture " +
	               "WHERE Id_Facture = ? ";

	    }

	    @Override
	    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	    	prSt.setString(1, id[0]);
	    }

	}