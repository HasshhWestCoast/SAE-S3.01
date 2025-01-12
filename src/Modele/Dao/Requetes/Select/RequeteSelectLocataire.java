package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Locataire;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectLocataire extends Requete<Locataire>{

	public String requete() {
		return "Select * from Sae_locataire ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, Locataire donnee) throws SQLException {
    }
}

