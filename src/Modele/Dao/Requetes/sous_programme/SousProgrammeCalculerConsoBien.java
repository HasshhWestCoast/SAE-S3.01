package Modele.Dao.Requetes.sous_programme;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe pour appeler la fonction PL/SQL `CalculerConsoBien_SP`.
 */
public class SousProgrammeCalculerConsoBien implements SousProgramme<String> {

    @Override
    public String appelSousProgramme() {
        // Requête pour appeler la fonction `CalculerConsoBien_SP`
        return "{ ? = call CalculerConsoBien_SP(?) }";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... parametres) throws SQLException {
        // Associe les paramètres sous forme de chaînes
        if (parametres.length == 0 || parametres[0] == null) {
            throw new SQLException("Le paramètre d'entrée (p_Input) doit être spécifié.");
        }
        prSt.setString(1, parametres[0]); // Entrée unique sous forme de chaîne
    }

    @Override
    public void parametres(PreparedStatement prSt, String input) throws SQLException {
        // Associe le paramètre via une chaîne unique (p_Input)
        if (input == null) {
            throw new SQLException("Le paramètre d'entrée (p_Input) doit être spécifié.");
        }
        prSt.setString(1, input); // Entrée unique sous forme de chaîne
    }

    @Override
    public void parametresSequence(CallableStatement prSt, String input) throws SQLException {
        // Non utilisé ici, mais peut être implémenté si nécessaire
    }

    @Override
    public void parametresCalcul(CallableStatement st, String input) throws SQLException {
        // Associe les paramètres pour la fonction avec un CallableStatement
        st.registerOutParameter(1, java.sql.Types.NUMERIC); // Résultat de la fonction
        if (input == null) {
            throw new SQLException("Le paramètre d'entrée (p_Input) doit être spécifié.");
        }
        st.setString(2, input); // Entrée unique sous forme de chaîne
    }
}
