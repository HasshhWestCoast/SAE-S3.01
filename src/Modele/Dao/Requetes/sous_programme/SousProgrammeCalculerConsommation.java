package Modele.Dao.Requetes.sous_programme;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe pour appeler la fonction PL/SQL `CalculerConsommation`.
 */
public class SousProgrammeCalculerConsommation implements SousProgramme<String> {

    @Override
    public String appelSousProgramme() {
        // Requête pour appeler la fonction `CalculerConsommation`
        return "{ ? = call CalculerConsommation(?) }";
    }

    @Override
    public void parametres(PreparedStatement prSt, String... parametres) throws SQLException {
        // Associe les paramètres sous forme de chaînes
        if (parametres.length == 0 || parametres[0] == null) {
            throw new SQLException("L'identifiant du compteur (p_Id_Compteur) doit être spécifié.");
        }
        prSt.setString(1, parametres[0]); // ID du compteur
    }

    @Override
    public void parametres(PreparedStatement prSt, String idCompteur) throws SQLException {
        // Associe le paramètre via une chaîne unique (ID du compteur)
        if (idCompteur == null) {
            throw new SQLException("L'identifiant du compteur (p_Id_Compteur) doit être spécifié.");
        }
        prSt.setString(1, idCompteur); // ID du compteur
    }

    @Override
    public void parametresSequence(CallableStatement prSt, String idCompteur) throws SQLException {
        // Non utilisé ici, mais peut être implémenté si nécessaire
    }

    @Override
    public void parametresCalcul(CallableStatement st, String idCompteur) throws SQLException {
        // Associe les paramètres pour la fonction avec un CallableStatement
        st.registerOutParameter(1, java.sql.Types.NUMERIC); // Résultat de la fonction
        if (idCompteur == null) {
            throw new SQLException("L'identifiant du compteur (p_Id_Compteur) doit être spécifié.");
        }
        st.setString(2, idCompteur); // ID du compteur
    }
}
