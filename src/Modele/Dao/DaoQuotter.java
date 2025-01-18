package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Logement;
import Modele.Quotite;
import Modele.Quotter;
import Modele.Dao.Requetes.Delete.RequeteDeleteQuotter;
import Modele.Dao.Requetes.Insert.RequeteInsertQuotter;
import Modele.Dao.Requetes.Select.RequeteSelectQuotter;
import Modele.Dao.Requetes.Select.RequeteSelectQuotterById;

public class DaoQuotter extends DaoModele<Quotter> implements Dao<Quotter> {

	private static Iterateur<Quotter> it;

	public DaoQuotter(Connection connexion) {
		super(connexion);
	}

	@Override
	public void create(Quotter t) throws SQLException {
		 RequeteInsertQuotter requete = new RequeteInsertQuotter();
		    try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
		        requete.parametres(prSt, t);
		        prSt.executeUpdate();
		        System.out.println("Insertion réussie pour Quotter !");
		    } catch (SQLException e) {
		        System.err.println("Erreur lors de l'ajout d'un Quotter : " + e.getMessage());
		        throw e;
		    }
	}

	@Override
	public void delete(Quotter t) throws SQLException {
		RequeteDeleteQuotter requete = new RequeteDeleteQuotter();
        try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
            requete.parametres(prSt, t);
            prSt.executeUpdate();
            System.out.println("Suppression réussie pour un Quotter ! ");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression d'un Quotter : " + e.getMessage());
            throw e;
        }
	}

	@Override
	public void update(Quotter t) throws SQLException {
	}

	@Override
	public Quotter findById(String... id) throws SQLException {
		RequeteSelectQuotterById requete = new RequeteSelectQuotterById();
        return super.findById(requete, id);
	}
	
	@Override
	public List<Quotter> findAll() throws SQLException {
		RequeteSelectQuotter requete = new RequeteSelectQuotter();
        List<Quotter> list = find(requete);

        PreparedStatement prSt = connexion.prepareStatement(requete.requete());
        ResultSet rs = prSt.executeQuery();
        
        DaoQuotter.setIterateurQuotter(new Iterateur<Quotter>(rs, this));
        
        return list;
	}
	
	@Override
	protected Quotter creerInstance(ResultSet curseur) throws SQLException {
		
		int pourcentage = curseur.getInt("pourcentage");
		String IdLogement = curseur.getString("Id_Logement");
		String TypeQuotite = curseur.getString("Type_Quotite");
		Logement logement = new DaoLogement(this.connexion).findById(IdLogement);
		Quotite quotite = new DaoQuotite(this.connexion).findById(TypeQuotite);
		return new Quotter(pourcentage, logement, quotite);
	}
	
	public static Iterateur<Quotter> getIterateurQuotter() {
        return it;
    }

    public static void setIterateurQuotter(Iterateur<Quotter> iterateur) {
        it = iterateur;
    }

}
