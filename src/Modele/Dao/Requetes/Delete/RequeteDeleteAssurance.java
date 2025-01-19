package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.assurance;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteAssurance extends Requete<assurance> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_assurance WHERE numero_Police = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, assurance assurance) throws SQLException {
        prSt.setString(1, assurance.getNuméroPolice());
    }
}
