package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modele.Bien;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteQuotterByLogementFromBien extends Requete<Bien> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_Quotter " +
               "WHERE Id_Logement IN (SELECT Id_Logement FROM Sae_Logement WHERE Id_Bien = ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Bien bien) throws SQLException {
        prSt.setString(1, bien.getIdBien());
    }
}
