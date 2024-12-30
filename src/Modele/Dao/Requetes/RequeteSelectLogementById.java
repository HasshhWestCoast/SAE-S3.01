package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Logement;

public class RequeteSelectLogementById extends Requete<Logement> {
	   
	@Override
    public String requete() {
        return "SELECT * FROM Logement " +
               "WHERE id_Logement = ? ";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
        if (id.length == 1) {
            prSt.setString(1, id[0]); 
            
        } else {
            throw new IllegalArgumentException("Le nombre d'identifiants pour Logement est incorrect.");
        }
    }
}