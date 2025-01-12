package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Logement;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteAssuranceByLogement extends Requete<Logement> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_assurance WHERE Id_Logement = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Logement logement) throws SQLException {
        prSt.setString(1, logement.getIdLogement());
    }
}
