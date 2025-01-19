package Modele.Dao.Requetes.sous_programme;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe pour appeler la fonction PL/SQL `PartieVariableConso`.
 */
public class SousProgrammePartieVariableConso implements SousProgramme<String> {

    @Override
    public String appelSousProgramme() {
        // Requête pour appeler la fonction `PartieVariableConso`
        return "{ ? = call PartieVariableConso(?) }";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... parametres) throws SQLException {
        // Vérifie que le paramètre d'entrée n'est pas null
        if (parametres == null || parametres.length == 0 || parametres[0] == null) {
            throw new SQLException("L'identifiant du compteur (p_Id_Compteur) doit être spécifié.");
        }
        prSt.setString(1, parametres[0]); // Paramètre d'entrée (p_Id_Compteur)
    }

    @Override
    public void parametres(PreparedStatement prSt, String idCompteur) throws SQLException {
        // Vérifie que l'identifiant du compteur n'est pas null
        if (idCompteur == null) {
            throw new SQLException("L'identifiant du compteur (p_Id_Compteur) doit être spécifié.");
        }
        prSt.setString(1, idCompteur); // Paramètre d'entrée (p_Id_Compteur)
    }

    @Override
    public void parametresSequence(CallableStatement prSt, String idCompteur) throws SQLException {
        // Non utilisé ici, mais peut être implémenté si nécessaire
    }

    @Override
    public void parametresCalcul(CallableStatement st, String idCompteur) throws SQLException {
        // Prépare les paramètres pour la fonction PL/SQL
        if (idCompteur == null) {
            throw new SQLException("L'identifiant du compteur (p_Id_Compteur) doit être spécifié.");
        }
        st.registerOutParameter(1, java.sql.Types.NUMERIC); // Résultat de la fonction
        st.setString(2, idCompteur); // Paramètre d'entrée (p_Id_Compteur)
    }
}
