package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.ICC;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteIcc extends Requete<ICC> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_ICC WHERE ICC = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, ICC icc) throws SQLException {
        prSt.setInt(1, icc.getIcc());
    }
}
