package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.Logement;
import Modele.Dao.Requetes.Select.RequeteSelectLogement;
import Modele.Dao.Requetes.Select.RequeteSelectLogementById;
import Modele.Dao.Requetes.Update.RequeteDetachFactureByLogement;
import Modele.Dao.Requetes.Delete.RequeteDeleteAssuranceByLogement;
import Modele.Dao.Requetes.Delete.RequeteDeleteCompteurByLogement;
import Modele.Dao.Requetes.Delete.RequeteDeleteLogement;
import Modele.Dao.Requetes.Delete.RequeteDeleteQuotterByLogement;
import Modele.Dao.Requetes.Delete.RequeteDeleteReleveByCompteur;
import Modele.Dao.Requetes.Insert.RequeteInsertLogement;

public class DaoLogement extends DaoModele<Logement> implements Dao<Logement>{

	private static Iterateur<Logement> it;

	public DaoLogement(Connection connexion) {
		super(connexion);
	}

	
	@Override
	public void delete(Logement logement) throws SQLException {
	   
		// Étape 1 : Supprimer les assurances associées
		RequeteDeleteAssuranceByLogement requeteDeleteAssurance = new RequeteDeleteAssuranceByLogement();
		PreparedStatement prStDeleteAssurance = connexion.prepareStatement(requeteDeleteAssurance.requete());
		requeteDeleteAssurance.parametres(prStDeleteAssurance, logement);
		prStDeleteAssurance.executeUpdate();
		System.out.println("Les assurances liées au logement ont été supprimées.");
		
		
		// Étape 2 : Supprimer les relevés associés au compteur lié au logement
		RequeteDeleteReleveByCompteur requeteDeleteReleve = new RequeteDeleteReleveByCompteur();
		PreparedStatement prStDeleteReleve = connexion.prepareStatement(requeteDeleteReleve.requete());
		requeteDeleteReleve.parametres(prStDeleteReleve, logement);
		prStDeleteReleve.executeUpdate();
		System.out.println("Les relevés liés au compteur ont été supprimés.");
		
		// Étape 3 : Supprimer le compteur associé au logement
		RequeteDeleteCompteurByLogement requeteDeleteCompteur = new RequeteDeleteCompteurByLogement();
		PreparedStatement prStDeleteCompteur = connexion.prepareStatement(requeteDeleteCompteur.requete());
		requeteDeleteCompteur.parametres(prStDeleteCompteur, logement);
		prStDeleteCompteur.executeUpdate();
		System.out.println("Le compteur associé au logement a été supprimé.");
		
		
		// Étape 4 : Détacher le logement des factures associées
		RequeteDetachFactureByLogement requeteDetachFacture = new RequeteDetachFactureByLogement();
		PreparedStatement prStDetachFacture = connexion.prepareStatement(requeteDetachFacture.requete());
		requeteDetachFacture.parametres(prStDetachFacture, logement);
		prStDetachFacture.executeUpdate();
		System.out.println("Logement détaché des factures pour le logement: " + logement.getIdLogement());
		
	    // Étape 5 : Supprimer les "Quotter" liés au logement
	    RequeteDeleteQuotterByLogement requeteDeleteQuotter = new RequeteDeleteQuotterByLogement();
	    try (PreparedStatement prStDeleteQuotter = connexion.prepareStatement(requeteDeleteQuotter.requete())) {
	        requeteDeleteQuotter.parametres(prStDeleteQuotter, logement);
	        prStDeleteQuotter.executeUpdate();
	        System.out.println("Les 'Quotter' liés au logement ont été supprimés.");
	    }
		
		// Étape 6 : Supprimer le logement
		RequeteDeleteLogement requeteDeleteLogement = new RequeteDeleteLogement();
		PreparedStatement prStDeleteLogement = connexion.prepareStatement(requeteDeleteLogement.requete());
		requeteDeleteLogement.parametres(prStDeleteLogement, logement);
		prStDeleteLogement.executeUpdate();
		System.out.println("Logement supprimé: " + logement.getIdLogement());

	}



	
	@Override
	public void create(Logement logement) throws SQLException {
	    RequeteInsertLogement requete = new RequeteInsertLogement();
	    try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
	        requete.parametres(prSt, logement);
	        prSt.executeUpdate();
	        System.out.println("Insertion réussie pour le logement !");
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de l'ajout d'un logement : " + e.getMessage());
	        throw e;
	    }
	}

	
	@Override
	public void update(Logement t) throws SQLException {
	}

	
	@Override
	public Logement findById(String... id) throws SQLException {
		RequeteSelectLogementById requete = new RequeteSelectLogementById();
	    return super.findById(requete, id);
	}


	@Override
	public List<Logement> findAll() throws SQLException {
		RequeteSelectLogement requete = new RequeteSelectLogement();
		
	    List<Logement> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoLogement.setIterateurLogement(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected Logement creerInstance(ResultSet curseur) throws SQLException {
		
	    java.sql.Date dateAcquisition = curseur.getDate("date_Acquisition");
	    String date_Acquisition = new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateAcquisition);
	    
	    String idLogement = curseur.getString("id_Logement");
	    
	    String surfaceHabitable = curseur.getString("surface_Habitable");
	    double surface_Habitable = Double.parseDouble(surfaceHabitable);
	    
	    String type_logement = curseur.getString("type_logement");
	    
	    String nbPieces = curseur.getString("nb_Pieces");
	    int nb_Pieces = Integer.parseInt(nbPieces);
	    
	    String numEtage = curseur.getString("num_Etage");
	    int num_Etage = Integer.parseInt(numEtage);
	    
	    String garageString = curseur.getString("garage");
	    int garage = Integer.parseInt(garageString);
	    
	    String IdBien = curseur.getString("Id_bien");
	    DaoBien daoBien = new DaoBien(this.connexion);
	    Bien bien = daoBien.findById(IdBien);
	    
	    return new Logement(idLogement, surface_Habitable, date_Acquisition, type_logement, nb_Pieces, num_Etage, garage, bien);
	}
	
	public static Iterateur<Logement> getIterateurLogement() {
        return it;
    }

    public static void setIterateurLogement(Iterateur<Logement> iterateur) {
        it = iterateur;
    }
}
