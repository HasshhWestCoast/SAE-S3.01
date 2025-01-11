package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modele.assurance;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertAssurance extends Requete<assurance> {

    @Override
    public String requete() {
        return "INSERT INTO Sae_assurance (numero_Police, montant, Protection_juridique, date_echeance, siret, id_Logement) " +
               "VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, assurance donnee) throws SQLException {
        prSt.setString(1, donnee.getNuméroPolice());
        prSt.setDouble(2, donnee.getMontant());
        prSt.setDouble(3, donnee.getProtectionJuridique());
        prSt.setString(4, donnee.getDateEcheance());
        prSt.setString(5, donnee.getEntreprise().getSiret());
        System.out.println("ID Logement : " + donnee.getLogement().getIdLogement());
        prSt.setString(6, donnee.getLogement().getIdLogement());
    }
}
