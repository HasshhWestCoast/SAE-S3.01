package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Dao.Requetes.Requete;
import Modele.Releve;

public class RequeteSelectReleveByIdCompteur extends Requete<Releve> {

    @Override
    public String requete() {
        return "SELECT Id_Compteur, date_RELEVÉ, indexCompteur FROM SAE_RELEVÉ " + 
        		"WHERE Id_Compteur = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
        if (id.length == 1) {
            prSt.setString(1, id[0]);
        } else {
            throw new IllegalArgumentException("Un seul paramètre attendu pour la requête.");
        }
    }

}
