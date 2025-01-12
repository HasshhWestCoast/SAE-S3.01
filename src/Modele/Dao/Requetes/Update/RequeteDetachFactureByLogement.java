package Modele.Dao.Requetes.Update;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Logement;
import Modele.Dao.Requetes.Requete;

public class RequeteDetachFactureByLogement extends Requete<Logement> {

    @Override
    public String requete() {
        return "UPDATE Sae_facture SET Id_Logement = NULL WHERE Id_Logement = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Logement logement) throws SQLException {
        prSt.setString(1, logement.getIdLogement());
    }
}
