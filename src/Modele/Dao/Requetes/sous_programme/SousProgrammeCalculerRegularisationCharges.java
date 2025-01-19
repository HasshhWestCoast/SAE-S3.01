package Modele.Dao.Requetes.sous_programme;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe pour appeler la fonction PL/SQL `CalculerRegularisationCharges`.
 */
public class SousProgrammeCalculerRegularisationCharges implements SousProgramme<String[]> {

    @Override
    public String appelSousProgramme() {
        // Requête pour appeler la fonction `CalculerRegularisationCharges`
        return "{ ? = call CalculerRegularisationCharges(?, ?, ?) }";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... parametres) throws SQLException {
        // Vérifie que les paramètres ne sont pas null ou incomplets
        if (parametres == null || parametres.length < 3 || parametres[0] == null || parametres[1] == null || parametres[2] == null) {
            throw new SQLException("Les identifiants du bien, du locataire, et la date de début doivent être spécifiés.");
        }
        prSt.setString(1, parametres[0]); // ID du bien
        prSt.setString(2, parametres[1]); // ID du locataire
        prSt.setString(3, parametres[2]); // Date de début
    }

    @Override
    public void parametresSequence(CallableStatement prSt, String[] ids) throws SQLException {
        // Non utilisé ici, peut être implémenté si nécessaire
    }

    @Override
    public void parametresCalcul(CallableStatement st, String[] ids) throws SQLException {
        // Prépare les paramètres pour la fonction PL/SQL
        if (ids == null || ids.length < 3 || ids[0] == null || ids[1] == null || ids[2] == null) {
            throw new SQLException("Les identifiants du bien, du locataire, et la date de début doivent être spécifiés.");
        }
        st.registerOutParameter(1, java.sql.Types.NUMERIC); // Résultat de la fonction
        st.setString(2, ids[0]); // ID du bien
        st.setString(3, ids[1]); // ID du locataire
        st.setString(4, ids[2]); // Date de début
    }
}
