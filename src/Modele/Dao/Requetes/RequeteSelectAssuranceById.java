package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.assurance;

public class RequeteSelectAssuranceById extends Requete<assurance> {
   
	@Override
    public String requete() {
        return "SELECT * FROM assurance " +
               "WHERE numero_Police = ? ";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
        if (id.length == 1) {
            prSt.setString(1, id[0]); 
            
        } else {
            throw new IllegalArgumentException("Le nombre d'identifiants pour Assurance est incorrect.");
        }
    }

}