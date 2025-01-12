package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Bien;

public class RequeteInsertBien extends Modele.Dao.Requetes.Requete<Bien> {

    @Override
    public String requete() {
        return "INSERT INTO Sae_Bien (id_Bien, adresse, ville, type_Bien, codepostal, periode_Construction) "
        		+ "VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Bien bien) throws SQLException {
    	prSt.setString(1, bien.getIdBien());
    	prSt.setString(2, bien.getAdresse());
    	prSt.setString(3, bien.getVille());
        prSt.setString(4, bien.getTypeBien());
        prSt.setString(5, bien.getCodePostal());
        prSt.setString(6, bien.getPeriodeConstruction());
    }
}
