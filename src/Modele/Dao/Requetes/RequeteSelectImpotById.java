package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Impot;

public class RequeteSelectImpotById extends Requete<Impot>{

	@Override
    public String requete() {
        return "SELECT * FROM Impot " +
               "WHERE Id_Impot = ? ";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
        if (id.length == 1) {
            prSt.setString(1, id[0]); 
            
        } else {
            throw new IllegalArgumentException("Le nombre d'identifiants pour Impot est incorrect.");
        }
    }
}
