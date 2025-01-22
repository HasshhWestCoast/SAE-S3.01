package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Compteur;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteCompteur extends Requete<Compteur> {

    @Override
    public String requete() {
        return "DELETE FROM Sae_compteur WHERE Id_Compteur = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, Compteur compteur) throws SQLException {
        prSt.setString(1, compteur.getIdCompteur());
    }
}
