package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Entreprise;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteEntreprise extends Requete<Entreprise> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_entreprise WHERE SIRET = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Entreprise entreprise) throws SQLException {
        prSt.setString(1, entreprise.getSiret());
    }
}
