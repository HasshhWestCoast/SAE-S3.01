package Modele.Dao.Requetes.Delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Bien;
import Modele.Dao.Requetes.Requete;

public class RequeteDeleteChargeByBien extends Requete<Bien>{

	 @Override
	    public String requete() {
	        return "DELETE FROM Sae_Charge WHERE Id_Bien = ?";
	    }

	    @Override
	    public void parametres(PreparedStatement prSt, Bien bien) throws SQLException {
	        prSt.setString(1, bien.getIdBien());
	    }
}
