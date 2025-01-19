package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Quotter;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectQuotterById extends Requete<Quotter> {

    @Override
    public String requete() {
        return "SELECT * FROM sae_quotter WHERE Id_Logement = ? AND Type_Quotite = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
        if (id.length == 2) {
            prSt.setString(1, id[0]); 
            prSt.setString(2, id[1]);
        } else {
            throw new IllegalArgumentException("Deux paramètres requis : Id_Logement et Type_Quotite.");
        }
    }
}
