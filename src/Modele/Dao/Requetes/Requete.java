package Modele.Dao.Requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe abstraite représentant une requête SQL générique.
 * Elle est conçue pour être spécialisée par des classes concrètes
 * qui implémentent des requêtes spécifiques.
 *
 * @param <T> Le type de données manipulé par la requête.
 */
public abstract class Requete<T> {

    /**
     * Retourne la chaîne de la requête SQL à exécuter.
     *
     * @return La chaîne de la requête SQL.
     */
    public abstract String requete();

    /**
     * Configure les paramètres du `PreparedStatement` à l'aide d'un ou plusieurs identifiants.
     *
     * @param prSt L'objet `PreparedStatement` à configurer.
     * @param id Un ou plusieurs identifiants nécessaires pour la requête.
     * @throws SQLException Si une erreur survient lors de la configuration des paramètres SQL.
     */
    public void parametres(PreparedStatement prSt, String... id) throws SQLException {
        // Méthode par défaut, peut être redéfinie par des sous-classes pour gérer les paramètres.
    }

    /**
     * Configure les paramètres du `PreparedStatement` à l'aide d'une instance d'entité.
     *
     * @param prSt L'objet `PreparedStatement` à configurer.
     * @param donnee L'entité contenant les données nécessaires pour la requête.
     * @throws SQLException Si une erreur survient lors de la configuration des paramètres SQL.
     */
    public void parametres(PreparedStatement prSt, T donnee) throws SQLException {
        // Méthode par défaut, peut être redéfinie par des sous-classes pour gérer les paramètres.
    }
}
