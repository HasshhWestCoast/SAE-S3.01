package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Releve;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertReleve extends Requete<Releve> {

    @Override
    public String requete() {
        return "INSERT INTO SAE_RELEVÉ (date_releve, indexCompteur, Id_Compteur) VALUES (?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Releve releve) throws SQLException {
        prSt.setString(1, releve.getDateReleve());
        prSt.setInt(2, releve.getIndexReleve());
        prSt.setString(3, releve.getCompteur().getIdCompteur());
    }
}
