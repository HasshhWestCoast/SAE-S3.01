package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Logement;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectLogement extends Requete<Logement>{

	public String requete() {
		return "Select * from Sae_Logement ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, Logement donnee) throws SQLException {
    }
}
