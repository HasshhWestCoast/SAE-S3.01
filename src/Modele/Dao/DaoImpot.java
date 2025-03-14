package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Impot;
import Modele.Dao.Requetes.Select.RequeteSelectImpot;
import Modele.Dao.Requetes.Select.RequeteSelectImpotById;
import Modele.Dao.Requetes.Update.RequeteDetachImpotFromImposer;
import Modele.Dao.Requetes.Delete.RequeteDeleteImpot;
import Modele.Dao.Requetes.Insert.RequeteInsertImpot;

public class DaoImpot extends DaoModele<Impot> implements Dao<Impot>{

	private static Iterateur<Impot> it;

	public DaoImpot(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Impot impot) throws SQLException {
	    // Étape 1 : Mettre à jour les enregistrements associés dans Sae_Imposer
	    RequeteDetachImpotFromImposer requeteDetachImpot = new RequeteDetachImpotFromImposer();
	    try (PreparedStatement prStDetachImpot = connexion.prepareStatement(requeteDetachImpot.requete())) {
	        requeteDetachImpot.parametres(prStDetachImpot, impot);
	        prStDetachImpot.executeUpdate();
	        System.out.println("Les enregistrements liés dans Sae_Imposer ont été détachés.");
	    }

	    // Étape 2 : Supprimer l'impôt
	    RequeteDeleteImpot requeteDeleteImpot = new RequeteDeleteImpot();
	    try (PreparedStatement prStDeleteImpot = connexion.prepareStatement(requeteDeleteImpot.requete())) {
	        requeteDeleteImpot.parametres(prStDeleteImpot, impot);
	        prStDeleteImpot.executeUpdate();
	        System.out.println("L'impôt a été supprimé avec succès.");
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de la suppression de l'impôt : " + e.getMessage());
	        throw e;
	    }
	}

	
	@Override
	public void create(Impot impot) throws SQLException {
	    RequeteInsertImpot requete = new RequeteInsertImpot();
	    try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
	        requete.parametres(prSt, impot);
	        prSt.executeUpdate();
	        System.out.println("Insertion réussie pour l'impôt !");
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de l'ajout d'un impôt : " + e.getMessage());
	        throw e;
	    }
	}

	
	@Override
	public void update(Impot t) throws SQLException {
	}

	
	@Override
	public Impot findById(String... id) throws SQLException {
		RequeteSelectImpotById requete = new RequeteSelectImpotById();
	    return super.findById(requete, id);
	}


	@Override
	public List<Impot> findAll() throws SQLException {
		RequeteSelectImpot requete = new RequeteSelectImpot();
		
	    List<Impot> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoImpot.setIterateurImpot(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected Impot creerInstance(ResultSet curseur) throws SQLException {
			    
	    String id_Impot = curseur.getString("id_Impot");
	    String annee = curseur.getString("annee");
	    
	    String montantString = curseur.getString("montant");
	    double montant = Double.parseDouble(montantString);
	    
	    return new Impot(id_Impot, montant, annee);
	}
	
	public static Iterateur<Impot> getIterateurImpot() {
        return it;
    }

    public static void setIterateurImpot(Iterateur<Impot> iterateur) {
        it = iterateur;
    }
}
