package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modele.Charge;

public class RequeteInsertCharge {

    public String requete() {
        return "INSERT INTO Sae_charge (id_charges, nom, montant_reel, montant_previsionnel, deductible, date_charge, id_bien) "
        		+ "VALUES (?, ?, ?, ?, ?, TO_DATE(?, 'DD/MM/YYYY'), ?)";
    }

    public void parametres(PreparedStatement prSt, Charge charge) throws SQLException {
        prSt.setString(1, charge.getIdCharge());
        prSt.setString(2, charge.getnom());
        prSt.setDouble(3, charge.getMontantReel());
        prSt.setDouble(4, charge.getMontantPrevisionnel());
        prSt.setInt(5, charge.isDeductible());
        prSt.setString(6, charge.getDateCharge());
        prSt.setString(7, charge.getBien().getIdBien());
    }
}
