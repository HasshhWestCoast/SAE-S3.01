package Modele.Dao.Requetes.sous_programme;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe pour appeler la fonction PL/SQL `CalculChargesReellesTotal`.
 */
public class SousProgrammeCalculChargesReellesTotal implements SousProgramme<String> {

    @Override
    public String appelSousProgramme() {
        // Requête pour appeler la fonction `CalculChargesReellesTotal`
        return "{ ? = call CalculChargesReellesTotal(?) }";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... parametres) throws SQLException {
        // Associe les paramètres sous forme de chaînes
        if (parametres.length == 0 || parametres[0] == null) {
            throw new SQLException("L'identifiant du bien (p_Id_Bien) doit être spécifié.");
        }
        prSt.setString(1, parametres[0]); // ID du bien
    }

    @Override
    public void parametres(PreparedStatement prSt, String idBien) throws SQLException {
        // Associe le paramètre via une chaîne (ID du bien)
        if (idBien == null) {
            throw new SQLException("L'identifiant du bien (p_Id_Bien) doit être spécifié.");
        }
        prSt.setString(1, idBien); // ID du bien
    }

    @Override
    public void parametresSequence(CallableStatement prSt, String idBien) throws SQLException {
        // Non utilisé ici, mais peut être implémenté si nécessaire
    }

    @Override
    public void parametresCalcul(CallableStatement st, String idBien) throws SQLException {
        // Associe les paramètres pour la fonction avec un CallableStatement
        st.registerOutParameter(1, java.sql.Types.NUMERIC); // Résultat de la fonction
        if (idBien == null) {
            throw new SQLException("L'identifiant du bien (p_Id_Bien) doit être spécifié.");
        }
        st.setString(2, idBien); // ID du bien
    }
}
