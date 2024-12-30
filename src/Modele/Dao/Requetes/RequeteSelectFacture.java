package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Facture;


public class RequeteSelectFacture  extends Requete<Facture>{

	public String requete() {
		return "Select * from facture ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, Facture donnee) throws SQLException {
    }
}

