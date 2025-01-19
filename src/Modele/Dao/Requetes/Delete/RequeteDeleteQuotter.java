package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Quotter;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteQuotter extends Requete<Quotter> {

    @Override
    public String requete() {
        return "DELETE FROM sae_quotter WHERE Id_Logement = ? AND Type_Quotite = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Quotter quotter) throws SQLException {
        prSt.setString(1, quotter.getLogement().getIdLogement());
        prSt.setString(2, quotter.getQuotite().getTypeQuotite());
    }
}
