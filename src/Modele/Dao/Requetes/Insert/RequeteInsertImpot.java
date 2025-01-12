package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Impot;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertImpot extends Requete<Impot> {

    @Override
    public String requete() {
        return "INSERT INTO Sae_impot (id_Impot, montant, annee) VALUES (?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Impot impot) throws SQLException {
        prSt.setString(1, impot.getIdImpot());
        prSt.setDouble(2, impot.getMontant());
        prSt.setString(3, impot.getAnnee());
    }
}
