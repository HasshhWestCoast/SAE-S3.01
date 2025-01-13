package Modele.Dao.Requetes.Update;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Impot;
import Modele.Dao.Requetes.Requete;

public class RequeteDetachImpotFromImposer extends Requete<Impot> {

    @Override
    public String requete() {
        return "UPDATE Sae_Imposer SET Id_Impot = NULL WHERE Id_Impot = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Impot impot) throws SQLException {
        prSt.setString(1, impot.getIdImpot());
    }
}
