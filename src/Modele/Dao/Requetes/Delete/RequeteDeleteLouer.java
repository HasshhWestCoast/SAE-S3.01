package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Louer;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteLouer extends Requete<Louer> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_louer WHERE Id_Locataire = ? AND Id_Bien = ? AND Date_debut = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Louer louer) throws SQLException {
        prSt.setString(1, louer.getLocataire().getIdLocataire());
        prSt.setString(2, louer.getBien().getIdBien());
        prSt.setString(3, louer.getDateDebut());
    }
}
