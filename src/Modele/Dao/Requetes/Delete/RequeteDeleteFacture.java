package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Facture;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteFacture extends Requete<Facture> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_facture WHERE Id_Facture = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Facture facture) throws SQLException {
        prSt.setString(1, facture.getIdFacture());
    }
}
