package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Impot;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectImpotById extends Requete<Impot>{

	@Override
    public String requete() {
        return "SELECT * FROM Sae_Impot " +
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
