package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Impot;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteImpot extends Requete<Impot> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_Impot WHERE Id_Impot = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Impot impot) throws SQLException {
        prSt.setString(1, impot.getIdImpot());
    }
}
