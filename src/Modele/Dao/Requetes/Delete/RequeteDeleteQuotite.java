package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Quotite;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteQuotite extends Requete<Quotite> {

    @Override
    public String requete() {
        return "DELETE FROM Quotite WHERE type_quotite = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Quotite quotite) throws SQLException {
        prSt.setString(1, quotite.getTypeQuotite());
    }
}
