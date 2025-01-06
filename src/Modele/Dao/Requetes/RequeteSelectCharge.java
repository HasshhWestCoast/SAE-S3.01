package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Charge;

public class RequeteSelectCharge extends Requete<Charge>{

	public String requete() {
		return "Select * from Sae_charge ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, Charge donnee) throws SQLException {
    }
}
