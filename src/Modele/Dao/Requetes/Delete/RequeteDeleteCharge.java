package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Charge;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteCharge extends Requete<Charge> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_Charges WHERE Id_Charges = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Charge charge) throws SQLException {
        prSt.setString(1, charge.getIdCharge());
    }
}
