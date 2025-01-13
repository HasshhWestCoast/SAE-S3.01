package Modele.Dao.Requetes.Update;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.ICC;
import Modele.Dao.Requetes.Requete;

public class RequeteDetachLouerByIcc extends Requete<ICC> {

    @Override
    public String requete() {
        return "UPDATE Sae_louer SET ICC = NULL WHERE ICC = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, ICC icc) throws SQLException {
        prSt.setInt(1, icc.getIcc());
    }
}
