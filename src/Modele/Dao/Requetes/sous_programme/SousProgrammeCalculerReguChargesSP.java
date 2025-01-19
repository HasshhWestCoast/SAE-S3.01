package Modele.Dao.Requetes.sous_programme;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe pour appeler la fonction PL/SQL `CalculerReguCharges_SP`.
 */
public class SousProgrammeCalculerReguChargesSP implements SousProgramme<String> {

    @Override
    public String appelSousProgramme() {
        // Requête pour appeler la fonction `CalculerReguCharges_SP`
        return "{ ? = call CalculerReguCharges_SP(?) }";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... parametres) throws SQLException {
        // Vérifie que le paramètre d'entrée n'est pas null
        if (parametres == null || parametres.length == 0 || parametres[0] == null) {
            throw new SQLException("L'entrée pour la fonction (p_Input) doit être spécifiée.");
        }
        prSt.setString(1, parametres[0]); // Paramètre d'entrée (p_Input)
    }

    @Override
    public void parametres(PreparedStatement prSt, String input) throws SQLException {
        // Vérifie que l'entrée unique n'est pas null
        if (input == null) {
            throw new SQLException("L'entrée pour la fonction (p_Input) doit être spécifiée.");
        }
        prSt.setString(1, input); // Paramètre d'entrée (p_Input)
    }

    @Override
    public void parametresSequence(CallableStatement prSt, String input) throws SQLException {
        // Non utilisé ici, peut être implémenté si nécessaire
    }

    @Override
    public void parametresCalcul(CallableStatement st, String input) throws SQLException {
        // Prépare les paramètres pour la fonction PL/SQL
        if (input == null) {
            throw new SQLException("L'entrée pour la fonction (p_Input) doit être spécifiée.");
        }
        st.registerOutParameter(1, java.sql.Types.NUMERIC); // Résultat de la fonction
        st.setString(2, input); // Paramètre d'entrée (p_Input)
    }
}
