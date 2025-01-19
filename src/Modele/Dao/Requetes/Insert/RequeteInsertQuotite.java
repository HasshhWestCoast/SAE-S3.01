package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modele.Quotite;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertQuotite extends Requete<Quotite> {

    @Override
    public String requete() {
        return "INSERT INTO SAE_Quotite (type_quotite) VALUES (?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Quotite quotite) throws SQLException {
        prSt.setString(1, quotite.getTypeQuotite());
    }
}
