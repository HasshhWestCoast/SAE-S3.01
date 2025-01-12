package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modele.ICC;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertICC extends Requete<ICC> {

    @Override
    public String requete() {
        return "INSERT INTO Sae_ICC (ICC, annee, trimestre, indice) VALUES (?, ?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, ICC icc) throws SQLException {
        prSt.setInt(1, icc.getIcc());
        prSt.setString(2, icc.getAnnee());
        prSt.setString(3, icc.getTrimestre());
        prSt.setDouble(4, icc.getIndice());
    }
}
