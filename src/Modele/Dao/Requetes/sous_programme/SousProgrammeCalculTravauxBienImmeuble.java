package Modele.Dao.Requetes.sous_programme;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe pour appeler la fonction PL/SQL `CalculTravauxBienImmeuble`.
 */
public class SousProgrammeCalculTravauxBienImmeuble implements SousProgramme<String> {

    @Override
    public String appelSousProgramme() {
        // Requête pour appeler la fonction `CalculTravauxBienImmeuble`
        return "{ ? = call CalculTravauxBienImmeuble(?) }";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... parametres) throws SQLException {
        // Vérifie que le paramètre d'entrée n'est pas null
        if (parametres == null || parametres.length == 0 || parametres[0] == null) {
            throw new SQLException("L'identifiant du bien (p_Id_Bien) doit être spécifié.");
        }
        prSt.setString(1, parametres[0]); // Paramètre d'entrée (p_Id_Bien)
    }

    @Override
    public void parametres(PreparedStatement prSt, String idBien) throws SQLException {
        // Vérifie que l'identifiant du bien n'est pas null
        if (idBien == null) {
            throw new SQLException("L'identifiant du bien (p_Id_Bien) doit être spécifié.");
        }
        prSt.setString(1, idBien); // Paramètre d'entrée (p_Id_Bien)
    }

    @Override
    public void parametresSequence(CallableStatement prSt, String idBien) throws SQLException {
        // Non utilisé ici, mais peut être implémenté si nécessaire
    }

    @Override
    public void parametresCalcul(CallableStatement st, String idBien) throws SQLException {
        // Prépare les paramètres pour la fonction PL/SQL
        if (idBien == null) {
            throw new SQLException("L'identifiant du bien (p_Id_Bien) doit être spécifié.");
        }
        st.registerOutParameter(1, java.sql.Types.NUMERIC); // Résultat de la fonction
        st.setString(2, idBien); // Paramètre d'entrée (p_Id_Bien)
    }
}
