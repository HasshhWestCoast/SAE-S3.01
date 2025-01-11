package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modele.Entreprise;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertEntreprise extends Requete<Entreprise> {

    @Override
    public String requete() {
        return "INSERT INTO Sae_Entreprise (SIRET, nom, adresse, codepostal, ville, mail, telephone, iban) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Entreprise entreprise) throws SQLException {
        prSt.setString(1, entreprise.getSiret());
        prSt.setString(2, entreprise.getNom());
        prSt.setString(3, entreprise.getAdresse());
        prSt.setString(4, entreprise.getcodepostal());
        prSt.setString(5, entreprise.getVille());
        prSt.setString(6, entreprise.getMail());
        prSt.setString(7, entreprise.getTelephone());
        prSt.setString(8, entreprise.getIban());
    }
}
