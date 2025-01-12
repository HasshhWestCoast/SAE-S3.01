package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Facture;
import Modele.Dao.Requetes.Requete;


public class RequeteSelectFacture  extends Requete<Facture>{

	public String requete() {
		return "Select * from Sae_facture ";
	}

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }
    
    @Override
    public void parametres(PreparedStatement prSt, Facture donnee) throws SQLException {
    }
}

