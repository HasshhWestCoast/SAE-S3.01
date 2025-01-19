package Modele.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Quotite;
import Modele.Dao.Requetes.Delete.RequeteDeleteQuotite;
import Modele.Dao.Requetes.Insert.RequeteInsertQuotite;
import Modele.Dao.Requetes.Select.RequeteSelectQuotite;
import Modele.Dao.Requetes.Select.RequeteSelectQuotiteById;


public class DaoQuotite extends DaoModele<Quotite> implements Dao<Quotite>{

	private static Iterateur<Quotite> it;

    public DaoQuotite(Connection connexion) {
        super(connexion);
    }

    
	@Override
	public void create(Quotite quotite) throws SQLException {
        RequeteInsertQuotite requete = new RequeteInsertQuotite();
        try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
            requete.parametres(prSt, quotite);
            prSt.executeUpdate();
            System.out.println("Insertion réussie pour la Quotite !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout d'une Quotite : " + e.getMessage());
            throw e;
        }
	}


    @Override
    public void delete(Quotite quotite) throws SQLException {
        RequeteDeleteQuotite requete = new RequeteDeleteQuotite();
        try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
            requete.parametres(prSt, quotite);
            prSt.executeUpdate();
            System.out.println("Suppression réussie pour la Quotite: " + quotite.getTypeQuotite());
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression d'une Quotite : " + e.getMessage());
            throw e;
        }
    }
    
    
	@Override
	public void update(Quotite t) throws SQLException {
		
	}


    @Override
    public Quotite findById(String... id) throws SQLException {
        RequeteSelectQuotiteById requete = new RequeteSelectQuotiteById();
        return super.findById(requete, id);
    }

    @Override
    public List<Quotite> findAll() throws SQLException {
    	RequeteSelectQuotite requete = new RequeteSelectQuotite();
        List<Quotite> list = find(requete);

        PreparedStatement prSt = connexion.prepareStatement(requete.requete());
        ResultSet rs = prSt.executeQuery();
        
        DaoQuotite.setIterateurQuotite(new Iterateur<Quotite>(rs, this));
        
        return list;
    }

    @Override
    protected Quotite creerInstance(ResultSet curseur) throws SQLException {
	    
        String typeQuotite = curseur.getString("type_quotite");      
        return new Quotite(typeQuotite);
    }

    public static Iterateur<Quotite> getIterateurQuotite() {
        return it;
    }

    public static void setIterateurQuotite(Iterateur<Quotite> iterateur) {
        it = iterateur;
    }

}
