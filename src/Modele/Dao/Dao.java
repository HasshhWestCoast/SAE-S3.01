package Modele.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface générique pour définir les opérations de base (CRUD) sur une entité.
 *
 * @param <T> Le type d'entité manipulée par le DAO.
 */
public interface Dao<T> {

    /**
     * Crée une nouvelle instance de l'entité dans la base de données.
     *
     * @param t L'entité à créer.
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    void create(T t) throws SQLException;

    /**
     * Recherche une entité dans la base de données en fonction de son identifiant.
     *
     * @param id Les paramètres d'identification nécessaires pour rechercher l'entité.
     * @return L'entité correspondante si elle est trouvée, sinon `null`.
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    T findById(String... id) throws SQLException;

    /**
     * Met à jour une instance existante de l'entité dans la base de données.
     *
     * @param t L'entité mise à jour.
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    void update(T t) throws SQLException;

    /**
     * Récupère toutes les instances de l'entité de la base de données.
     *
     * @return Une liste contenant toutes les entités.
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    List<T> findAll() throws SQLException;

    /**
     * Supprime une instance de l'entité de la base de données.
     *
     * @param t L'entité à supprimer.
     * @throws SQLException Si une erreur survient lors de l'exécution de la requête SQL.
     */
    void delete(T t) throws SQLException;

}
