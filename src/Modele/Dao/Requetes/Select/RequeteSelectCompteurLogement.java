package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Compteur;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectCompteurLogement extends Requete<Compteur> {
	   
		@Override
	    public String requete() {
	        return "SELECT * FROM Sae_Compteur " +
	               "WHERE Id_logement is not null ";
	    }

	    @Override
	    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	    }
	    
	    @Override
	    public void parametres(PreparedStatement prSt, Compteur donnee) throws SQLException {
	    }

	}