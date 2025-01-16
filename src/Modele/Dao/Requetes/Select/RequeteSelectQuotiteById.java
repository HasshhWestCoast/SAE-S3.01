package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Quotite;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectQuotiteById extends Requete<Quotite> {

    @Override
    public String requete() {
        return "SELECT * FROM Quotite WHERE type_quotite = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
        if (id.length == 1) {
            prSt.setString(1, id[0]);
        } else {
            throw new IllegalArgumentException("Nombre d'identifiants incorrect pour Quotite.");
        }
    }

    @Override
    public void parametres(PreparedStatement prSt, Quotite donnee) throws SQLException {
        // Pas utilisé dans ce cas
    }
}
