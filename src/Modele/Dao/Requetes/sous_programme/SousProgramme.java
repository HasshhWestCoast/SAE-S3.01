package Modele.Dao.Requetes.sous_programme;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Interface définissant un sous-programme SQL générique utilisé pour les appels stockés.
 *
 * @param <T> Type générique représentant l'entité associée au sous-programme.
 */
public interface SousProgramme<T> {

    /**
     * Retourne l'appel du sous-programme SQL sous forme de chaîne de caractères.
     *
     * @return Appel du sous-programme SQL.
     */
    String appelSousProgramme();

    /**
     * Paramètre la PreparedStatement avec les valeurs fournies dans le tableau de chaînes de caractères.
     *
     * @param prSt       PreparedStatement à paramétrer.
     * @param parametres Tableau de chaînes de caractères contenant les valeurs à paramétrer.
     * @throws SQLException Si une erreur SQL survient lors du paramétrage de la requête.
     */
    void parametres(PreparedStatement prSt, String... parametres) throws SQLException;

    /**
     * Paramètre la PreparedStatement avec les valeurs de l'entité donnée.
     *
     * @param prSt   PreparedStatement à paramétrer.
     * @param donnee Objet représentant l'entité pour laquelle les paramètres doivent être définis.
     * @throws SQLException Si une erreur SQL survient lors du paramétrage de la requête.
     */
    void parametres(PreparedStatement prSt, T donnee) throws SQLException;

    /**
     * Paramètre la CallableStatement avec les valeurs de l'entité donnée pour les requêtes sur les séquences.
     *
     * @param prSt   CallableStatement à paramétrer.
     * @param donnee Objet représentant l'entité pour laquelle les paramètres de séquence doivent être définis.
     * @throws SQLException Si une erreur SQL survient lors du paramétrage de la requête.
     */
    void parametresSequence(CallableStatement prSt, T donnee) throws SQLException;

    /**
     * Paramètre la CallableStatement avec les valeurs de l'entité donnée pour les calculs spécifiques.
     *
     * @param st     CallableStatement à paramétrer.
     * @param donnees Objet représentant l'entité pour laquelle les paramètres de calcul doivent être définis.
     * @throws SQLException Si une erreur SQL survient lors du paramétrage de la requête.
     */
    void parametresCalcul(CallableStatement st, T donnees) throws SQLException;
}
