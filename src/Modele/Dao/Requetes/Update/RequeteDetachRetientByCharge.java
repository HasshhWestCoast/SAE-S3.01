package Modele.Dao.Requetes.Update;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Charge;
import Modele.Dao.Requetes.Requete;

public class RequeteDetachRetientByCharge extends Requete<Charge> {

    @Override
    public String requete() {
        return "UPDATE Sae_retient SET Id_Charges = NULL WHERE Id_Charges = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Charge charge) throws SQLException {
        prSt.setString(1, charge.getIdCharge());
    }
}
