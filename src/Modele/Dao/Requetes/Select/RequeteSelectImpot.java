package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Impot;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectImpot extends Requete<Impot>{

	public String requete() {
		return "Select * from Sae_Impot ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, Impot donnee) throws SQLException {
    }
}