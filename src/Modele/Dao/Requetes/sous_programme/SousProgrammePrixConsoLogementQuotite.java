package Modele.Dao.Requetes.sous_programme;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe pour appeler la fonction PL/SQL `PrixConsoLogementQuotite`.
 */
public class SousProgrammePrixConsoLogementQuotite implements SousProgramme<String[]> {

    @Override
    public String appelSousProgramme() {
        // Requête pour appeler la fonction `PrixConsoLogementQuotite`
        return "{ ? = call PrixConsoLogementQuotite(?, ?) }";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... parametres) throws SQLException {
        // Vérifie que les paramètres ne sont pas null
        if (parametres == null || parametres.length < 2 || parametres[0] == null || parametres[1] == null) {
            throw new SQLException("Les identifiants du bien et du compteur doivent être spécifiés.");
        }
        prSt.setString(1, parametres[0]); // ID du bien
        prSt.setString(2, parametres[1]); // ID du compteur
    }

    @Override
    public void parametresSequence(CallableStatement prSt, String[] ids) throws SQLException {
        // Non utilisé ici, mais peut être implémenté si nécessaire
    }

    @Override
    public void parametresCalcul(CallableStatement st, String... parametres) throws SQLException {
        // Prépare les paramètres pour la fonction PL/SQL
        if (parametres == null || parametres.length < 2 || parametres[0] == null || parametres[1] == null) {
            throw new SQLException("Les identifiants du bien et du compteur doivent être spécifiés.");
        }
        st.registerOutParameter(1, java.sql.Types.NUMERIC); // Résultat de la fonction
        st.setString(2, parametres[0]); // ID du bien
        st.setString(3, parametres[1]); // ID du compteur
    }
}
