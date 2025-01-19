package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Quotter;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectQuotter extends Requete<Quotter> {

    @Override
    public String requete() {
        return "SELECT * FROM sae_quotter";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }

    @Override
    public void parametres(PreparedStatement prSt, Quotter donnee) throws SQLException {
    }
}
