package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Entreprise;
import Modele.Dao.Requetes.Select.RequeteSelectEntreprise;
import Modele.Dao.Requetes.Select.RequeteSelectEntrepriseById;
import Modele.Dao.Requetes.Update.RequeteUpdateFactureDetachEntreprise;
import Modele.Dao.Requetes.Delete.RequeteDeleteAssuranceByEntreprise;
import Modele.Dao.Requetes.Delete.RequeteDeleteEntreprise;
import Modele.Dao.Requetes.Insert.RequeteInsertEntreprise;

public class DaoEntreprise extends DaoModele<Entreprise> implements Dao<Entreprise>{

	private static Iterateur<Entreprise> it;

	public DaoEntreprise(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Entreprise entreprise) throws SQLException {
	    // Étape 1 : Détacher les factures associées
	    RequeteUpdateFactureDetachEntreprise requeteUpdateFacture = new RequeteUpdateFactureDetachEntreprise();
	    try (PreparedStatement prStUpdateFacture = connexion.prepareStatement(requeteUpdateFacture.requete())) {
	        requeteUpdateFacture.parametres(prStUpdateFacture, entreprise);
	        prStUpdateFacture.executeUpdate();
	        System.out.println("Les factures liées à l'entreprise ont été détachées.");
	    }

	    // Étape 2 : Supprimer les assurances associées
	    RequeteDeleteAssuranceByEntreprise requeteDeleteAssurance = new RequeteDeleteAssuranceByEntreprise();
	    try (PreparedStatement prStDeleteAssurance = connexion.prepareStatement(requeteDeleteAssurance.requete())) {
	        requeteDeleteAssurance.parametres(prStDeleteAssurance, entreprise);
	        prStDeleteAssurance.executeUpdate();
	        System.out.println("Les assurances liées à l'entreprise ont été supprimées.");
	    }

	    // Étape 3 : Supprimer l'entreprise
	    RequeteDeleteEntreprise requeteDelete = new RequeteDeleteEntreprise();
	    try (PreparedStatement prStDelete = connexion.prepareStatement(requeteDelete.requete())) {
	        requeteDelete.parametres(prStDelete, entreprise);
	        prStDelete.executeUpdate();
	        System.out.println("L'entreprise avec SIRET: " + entreprise.getSiret() + " a été supprimée.");
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de la suppression de l'entreprise : " + e.getMessage());
	        throw e;
	    }
	}


	
	@Override
	public void create(Entreprise entreprise) throws SQLException {
	    RequeteInsertEntreprise requete = new RequeteInsertEntreprise();
	    try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
	        requete.parametres(prSt, entreprise);
	        prSt.executeUpdate();
	        System.out.println("Entreprise ajoutée avec succès !");
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de l'ajout de l'entreprise : " + e.getMessage());
	        throw e;
	    }
	}

	
	@Override
	public void update(Entreprise t) throws SQLException {
	}

	
	@Override
	public Entreprise findById(String... id) throws SQLException {
		RequeteSelectEntrepriseById requete = new RequeteSelectEntrepriseById();
	    return super.findById(requete, id);
	}


	@Override
	public List<Entreprise> findAll() throws SQLException {
		RequeteSelectEntreprise requete = new RequeteSelectEntreprise();
		
	    List<Entreprise> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoEntreprise.setIterateurEntreprise(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected Entreprise creerInstance(ResultSet curseur) throws SQLException {
			    
	    String SIRET = curseur.getString("SIRET");
	    String nom = curseur.getString("nom");
	    String adresse = curseur.getString("adresse");
	    String codepostal = curseur.getString("codepostal");	    
	    String ville = curseur.getString("ville");	    
	    String mail = curseur.getString("mail");	    
	    String telephone = curseur.getString("telephone");	    
	    String iban = curseur.getString("iban");
	    
	    return new Entreprise(SIRET, nom, adresse, codepostal, ville, mail, telephone, iban);
	}
	
	public static Iterateur<Entreprise> getIterateurEntreprise() {
        return it;
    }

    public static void setIterateurEntreprise(Iterateur<Entreprise> iterateur) {
        it = iterateur;
    }
}
