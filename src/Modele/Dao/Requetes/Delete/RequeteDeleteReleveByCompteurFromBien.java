package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Bien;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteReleveByCompteurFromBien extends Requete<Bien> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_relevé WHERE Id_Compteur IN (SELECT Id_Compteur FROM Sae_compteur WHERE Id_Bien = ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Bien bien) throws SQLException {
        prSt.setString(1, bien.getIdBien());
    }
}
