package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Locataire;
import Modele.Dao.Requetes.Select.RequeteSelectLocataire;
import Modele.Dao.Requetes.Select.RequeteSelectLocataireById;
import Modele.Dao.Requetes.Delete.RequeteDeleteLocataire;
import Modele.Dao.Requetes.Delete.RequeteDeleteLouerByLocataire;
import Modele.Dao.Requetes.Delete.RequeteDeleteRetientByLocataire;
import Modele.Dao.Requetes.Insert.RequeteInsertLocataire;

public class DaoLocataire extends DaoModele<Locataire> implements Dao<Locataire>{

	private static Iterateur<Locataire> it;

	public DaoLocataire(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Locataire locataire) throws SQLException {
	    // Étape 1 : Supprimer les enregistrements associés dans Sae_retient
	    RequeteDeleteRetientByLocataire requeteDeleteRetient = new RequeteDeleteRetientByLocataire();
	    try (PreparedStatement prStDeleteRetient = connexion.prepareStatement(requeteDeleteRetient.requete())) {
	        requeteDeleteRetient.parametres(prStDeleteRetient, locataire);
	        prStDeleteRetient.executeUpdate();
	        System.out.println("Les enregistrements liés au locataire ont été supprimés de Sae_retient.");
	    }

	    // Étape 2 : Supprimer les locations associées
	    RequeteDeleteLouerByLocataire requeteDeleteLouer = new RequeteDeleteLouerByLocataire();
	    try (PreparedStatement prStDeleteLouer = connexion.prepareStatement(requeteDeleteLouer.requete())) {
	        requeteDeleteLouer.parametres(prStDeleteLouer, locataire);
	        prStDeleteLouer.executeUpdate();
	        System.out.println("Les locations liées au locataire ont été supprimées.");
	    }

	    // Étape 3 : Supprimer le locataire
	    RequeteDeleteLocataire requeteDeleteLocataire = new RequeteDeleteLocataire();
	    try (PreparedStatement prStDeleteLocataire = connexion.prepareStatement(requeteDeleteLocataire.requete())) {
	        requeteDeleteLocataire.parametres(prStDeleteLocataire, locataire);
	        prStDeleteLocataire.executeUpdate();
	        System.out.println("Le locataire a été supprimé avec succès.");
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de la suppression du locataire : " + e.getMessage());
	        throw e;
	    }
	}

	
	@Override
	public void create(Locataire locataire) throws SQLException {
	    RequeteInsertLocataire requete = new RequeteInsertLocataire();
	    try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
	        requete.parametres(prSt, locataire);
	        prSt.executeUpdate();
	        System.out.println("Insertion réussie pour le locataire !");
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de l'ajout du locataire : " + e.getMessage());
	        throw e;
	    }
	}

	
	@Override
	public void update(Locataire t) throws SQLException {
	}

	
	@Override
	public Locataire findById(String... id) throws SQLException {
		RequeteSelectLocataireById requete = new RequeteSelectLocataireById();
	    return super.findById(requete, id);
	}


	@Override
	public List<Locataire> findAll() throws SQLException {
		RequeteSelectLocataire requete = new RequeteSelectLocataire();
		
	    List<Locataire> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoLocataire.setIterateurLocataire(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected Locataire creerInstance(ResultSet curseur) throws SQLException {
		
	    java.sql.Date dateNaissance = curseur.getDate("date_Naissance");
	    String date_Naissance = new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateNaissance);
	    
	    String id_Locataire = curseur.getString("id_Locataire");
	    String nom = curseur.getString("nom");
	    String prenom = curseur.getString("prenom");
	    String telephone = curseur.getString("telephone");
	    String mail = curseur.getString("mail");
	    String colocataireString = curseur.getString("collocataire");
	    int colocataire = Integer.parseInt(colocataireString);
	    
	    return new Locataire(id_Locataire, nom, prenom, telephone, mail, date_Naissance, colocataire);
	}
	
	public static Iterateur<Locataire> getIterateurLocataire() {
        return it;
    }

    public static void setIterateurLocataire(Iterateur<Locataire> iterateur) {
        it = iterateur;
    }
}

