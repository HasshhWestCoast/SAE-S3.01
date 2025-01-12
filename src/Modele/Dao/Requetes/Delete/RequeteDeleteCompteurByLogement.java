package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Logement;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteCompteurByLogement extends Requete<Logement> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_compteur WHERE Id_Logement = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Logement logement) throws SQLException {
        prSt.setString(1, logement.getIdLogement());
    }
}
