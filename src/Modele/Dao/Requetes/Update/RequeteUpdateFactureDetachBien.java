package Modele.Dao.Requetes.Update;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modele.Bien;
import Modele.Dao.Requetes.Requete;

public class RequeteUpdateFactureDetachBien extends Requete<Bien> {

    @Override
    public String requete() {
        return "UPDATE Sae_Facture SET Id_Bien = NULL WHERE Id_Bien = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Bien bien) throws SQLException {
        prSt.setString(1, bien.getIdBien());
    }
}
