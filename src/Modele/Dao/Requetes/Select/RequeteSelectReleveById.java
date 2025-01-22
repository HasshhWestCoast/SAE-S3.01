package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Dao.Requetes.Requete;
import Modele.Releve;

public class RequeteSelectReleveById extends Requete<Releve> {

    @Override
    public String requete() {
        return "SELECT * FROM SAE_RELEVÉ " + 
        		"WHERE date_relevé = ? AND Id_Compteur = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
        if (id.length != 2) {
            throw new IllegalArgumentException("RequeteSelectReleveById nécessite deux paramètres : date_releve et Id_Compteur");
        }
        prSt.setString(1, id[0]); 
        prSt.setString(2, id[1]); 
    }
}
