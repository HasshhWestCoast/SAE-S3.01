package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Quotter;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertQuotter extends Requete<Quotter> {

    @Override
    public String requete() {
        return "INSERT INTO sae_quotter (Id_Logement, Type_Quotite, pourcentage) VALUES (?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Quotter quotter) throws SQLException {
        prSt.setString(1, quotter.getLogement().getIdLogement());
        prSt.setString(2, quotter.getQuotite().getTypeQuotite());
        prSt.setInt(3, quotter.getPourcentage());
    }
}
