package Modele.Dao.Requetes.Update;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modele.Entreprise;
import Modele.Dao.Requetes.Requete;

public class RequeteUpdateFactureDetachEntreprise extends Requete<Entreprise> {

    @Override
    public String requete() {
        return "UPDATE Sae_facture SET SIRET = NULL WHERE SIRET = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Entreprise entreprise) throws SQLException {
        prSt.setString(1, entreprise.getSiret());
    }
}
