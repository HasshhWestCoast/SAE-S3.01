package Modele.Dao.Requetes.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Louer;
import Modele.Dao.Requetes.Requete;

public class RequeteSelectDatePaiement extends Requete<Louer>{

	@Override
    public String requete() {
        return "SELECT Date_Paiement FROM SAE_Louer  l, SAE_Facture f" +
               "WHERE l.id_Bien = f.id_Bien " +
        	   "AND Id_bien = ?";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
        if (id.length == 1) {
            prSt.setString(1, id[0]); 
        }
    }

	
}
