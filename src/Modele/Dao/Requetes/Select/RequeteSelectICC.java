package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.ICC;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectICC extends Requete<ICC>{

	public String requete() {
		return "Select * from Sae_icc ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, ICC donnee) throws SQLException {
    }
}
