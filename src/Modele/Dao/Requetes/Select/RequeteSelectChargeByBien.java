package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Dao.Requetes.Requete;

public class RequeteSelectChargeByBien extends Requete<Void> {

    @Override
    public String requete() {
        return "SELECT * FROM Sae_charge WHERE id_Bien = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {}
}
