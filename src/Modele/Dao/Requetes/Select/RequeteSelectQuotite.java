package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Quotite;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectQuotite extends Requete<Quotite> {

    @Override
    public String requete() {
        return "SELECT * FROM Quotite";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
    }

    @Override
    public void parametres(PreparedStatement prSt, Quotite donnee) throws SQLException {
    }
}
