package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Logement;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectLogementById extends Requete<Logement> {
	   
	@Override
    public String requete() {
        return "SELECT * FROM Sae_Logement " +
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