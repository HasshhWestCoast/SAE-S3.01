package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Locataire;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertLocataire extends Requete<Locataire> {

    @Override
    public String requete() {
        return "INSERT INTO Sae_locataire (id_Locataire, nom, prenom, telephone, mail, date_Naissance, collocataire) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Locataire locataire) throws SQLException {
        prSt.setString(1, locataire.getIdLocataire());
        prSt.setString(2, locataire.getNom());
        prSt.setString(3, locataire.getPrenom());
        prSt.setString(4, locataire.getTelephone());
        prSt.setString(5, locataire.getMail());
        prSt.setString(6, locataire.getDateNaissance());
        prSt.setInt(7, locataire.getColocataire());
    }
}
