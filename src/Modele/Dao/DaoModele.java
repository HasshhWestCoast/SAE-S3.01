package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modele.Dao.Requetes.Requete;

public abstract class DaoModele<T> implements Dao<T> {

    protected Connection connexion;

    public DaoModele(Connection connexion) {
        this.connexion = connexion;
    }

    protected abstract T creerInstance(ResultSet curseur) throws SQLException;

    protected List<T> select(PreparedStatement prSt) throws SQLException {
        List<T> resultat = new ArrayList<>();
        try (ResultSet rs = prSt.executeQuery()) {
            while (rs.next()) {
                T instance = creerInstance(rs); 
                resultat.add(instance);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur : méthode select : " + ex.getMessage());
            throw ex;
        }
        return resultat;
    }

    public int miseAJour(Requete<T> req, T donnee) throws SQLException {
        int nbLigne = -1;
        try (PreparedStatement prSt = connexion.prepareStatement(req.requete())) {
            req.parametres(prSt, donnee);
            nbLigne = prSt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erreur lors de la méthode miseAJour : " + ex.getMessage());
            throw ex;
        }
        return nbLigne;
    }


    public List<T> find(Requete<T> req, String... id) throws SQLException {
        List<T> resultat = new ArrayList<>();
        try (PreparedStatement prSt = connexion.prepareStatement(req.requete())) {
        	req.parametres(prSt, id);
            resultat = select(prSt);
        } catch (SQLException ex) {
            throw ex;
        }
        return resultat;
    }


    public T findById(Requete<T> req, String... id) throws SQLException {
        List<T> resultats = find(req, id);
        return resultats.isEmpty() ? null : resultats.get(0);
    }
}
