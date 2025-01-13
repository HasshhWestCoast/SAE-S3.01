package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Facture;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectDatePaiement extends Requete<Facture>{

	@Override
    public String requete() {
        return "SELECT Date_Paiement, Date_emission, montant_reel_verse "
        		+ "FROM SAE_Facture " +
               "WHERE Id_bien = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
        if (id.length == 1) {
            prSt.setString(1, id[0]); 
        }
    }

	
}
