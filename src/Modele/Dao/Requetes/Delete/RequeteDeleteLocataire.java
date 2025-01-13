package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Locataire;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteLocataire extends Requete<Locataire> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_locataire WHERE Id_Locataire = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Locataire locataire) throws SQLException {
        prSt.setString(1, locataire.getIdLocataire());
    }
}
