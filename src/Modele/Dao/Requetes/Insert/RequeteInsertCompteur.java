package Modele.Dao.Requetes.Insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Compteur;
import Modele.Dao.Requetes.Requete;

public class RequeteInsertCompteur extends Requete<Compteur> {

    @Override
    public String requete() {
        return "INSERT INTO Sae_compteur (id_Compteur, typeComp, indexCompteur, date_Releve, ID_Logement, ID_Bien) "
        		+ "VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void parametres(PreparedStatement prSt, Compteur compteur) throws SQLException {
        prSt.setString(1, compteur.getIdCompteur());
        prSt.setString(2, compteur.getTypeComp());
        prSt.setInt(3, compteur.getIndexCompteur());
        prSt.setString(4, compteur.getDateRelevé());
        if (compteur.getImmeuble() != null) {
            prSt.setString(5, compteur.getImmeuble().getIdLogement());
        } 
        else if (compteur.getBien() != null) {
            prSt.setString(5, compteur.getBien().getIdBien());
        } else {
        	prSt.setNull(5, java.sql.Types.VARCHAR);
        }
    }
}
