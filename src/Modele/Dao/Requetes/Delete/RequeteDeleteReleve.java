package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Releve;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteReleve extends Requete<Releve> {

    @Override
    public String requete() {
        return "DELETE FROM SAE_RELEVÉ WHERE date_releve = ? AND Id_Compteur = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Releve releve) throws SQLException {
        prSt.setString(1, releve.getDateReleve());
        prSt.setString(2, releve.getCompteur().getIdCompteur());
    }
}
