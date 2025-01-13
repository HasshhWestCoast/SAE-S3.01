package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Compteur;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertCompteur extends Requete<Compteur> {

    @Override
    public String requete() {
        return "INSERT INTO Sae_compteur (id_Compteur, typeComp, ID_Logement, ID_Bien) "
        		+ "VALUES (?, ?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Compteur compteur) throws SQLException {
        prSt.setString(1, compteur.getIdCompteur());
        prSt.setString(2, compteur.getTypeComp());  

        if (compteur.getImmeuble() != null) {
            prSt.setString(3, compteur.getImmeuble().getIdLogement());         
        } else {
        	prSt.setNull(3, java.sql.Types.VARCHAR);
        }
        if (compteur.getBien() != null) {
            prSt.setString(4, compteur.getBien().getIdBien());
        }else {
            prSt.setNull(4, java.sql.Types.VARCHAR);
        }
    }

}
