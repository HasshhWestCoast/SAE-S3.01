package Modele.Dao.Requetes.sous_programme;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe pour appeler la fonction PL/SQL `CalculerPrixConsoLogement`.
 */
public class SousProgrammeCalculerPrixConsoLogement implements SousProgramme<String[]> {

    @Override
    public String appelSousProgramme() {
        // Requête pour appeler la fonction `CalculerPrixConsoLogement`
        return "{ ? = call CalculerPrixConsoLogement(?, ?) }";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... parametres) throws SQLException {
        if (parametres == null || parametres.length < 2 || parametres[0] == null || parametres[1] == null) {
            throw new SQLException("Les identifiants du logement et du compteur doivent être spécifiés.");
        }
        prSt.setString(1, parametres[0]); // ID du logement
        prSt.setString(2, parametres[1]); // ID du compteur
    }


    @Override
    public void parametresSequence(CallableStatement prSt, String[] ids) throws SQLException {
        // Non utilisé dans ce cas, peut être implémenté si nécessaire
    }

    @Override
    public void parametresCalcul(CallableStatement st, String[] ids) throws SQLException {
        // Prépare les paramètres pour la fonction PL/SQL
        if (ids == null || ids.length < 2 || ids[0] == null || ids[1] == null) {
            throw new SQLException("Les identifiants du logement et du compteur doivent être spécifiés.");
        }
        st.registerOutParameter(1, java.sql.Types.NUMERIC); // Résultat de la fonction
        st.setString(2, ids[0]); // ID du logement
        st.setString(3, ids[1]); // ID du compteur
    }
}
