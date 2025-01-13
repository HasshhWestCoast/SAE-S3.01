package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.Compteur;
import Modele.Logement;
import Modele.Dao.Requetes.Select.RequeteSelectCompteur;
import Modele.Dao.Requetes.Select.RequeteSelectCompteurById;
import Modele.Dao.Requetes.Select.RequeteSelectCompteurLogement;
import Modele.Dao.Requetes.Insert.RequeteInsertCompteur;
import Modele.Dao.Requetes.Select.RequeteSelectCompteurBien;

public class DaoCompteur extends DaoModele<Compteur> implements Dao<Compteur>{

	private static Iterateur<Compteur> it;

	public DaoCompteur(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Compteur t) throws SQLException {		
	}
	
	@Override
	public void create(Compteur compteur) throws SQLException {
	    RequeteInsertCompteur requete = new RequeteInsertCompteur();
	    try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
	        requete.parametres(prSt, compteur);
	        prSt.executeUpdate();
	        System.out.println("Insertion réussie pour le compteur !");
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de l'ajout d'un compteur : " + e.getMessage());
	        throw e;
	    }
	}

	
	@Override
	public void update(Compteur t) throws SQLException {
	}

	
	@Override
	public Compteur findById(String... id) throws SQLException {
		RequeteSelectCompteurById requete = new RequeteSelectCompteurById();
	    return super.findById(requete, id);
	}
	
	
	public  List<Compteur> findComptBien() throws SQLException {
		RequeteSelectCompteurBien requete = new RequeteSelectCompteurBien();
		
		List<Compteur> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoCompteur.setIterateurCompteur(new Iterateur<>(resultSet, this));

	    return list;
	}
	
	public  List<Compteur> findComptLogement() throws SQLException {
		RequeteSelectCompteurLogement requete = new RequeteSelectCompteurLogement();
		
		List<Compteur> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoCompteur.setIterateurCompteur(new Iterateur<>(resultSet, this));

	    return list;
	}


	@Override
	public List<Compteur> findAll() throws SQLException {
		RequeteSelectCompteur requete = new RequeteSelectCompteur();
		
	    List<Compteur> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoCompteur.setIterateurCompteur(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected Compteur creerInstance(ResultSet curseur) throws SQLException {
		
	    String id_Compteur = curseur.getString("id_Compteur");
	    String typeComp = curseur.getString("typeComp");
	    	    
	    String ID_Logement = curseur.getString("ID_Logement");
	    String ID_Bien = curseur.getString("ID_Bien");
	    
	    DaoLogement daoLogement = new DaoLogement(this.connexion);
	    Logement logement = daoLogement.findById(ID_Logement);

	    DaoBien daoBien = new DaoBien(this.connexion);
	    Bien bien = daoBien.findById(ID_Bien);
	    
	    
	    return new Compteur(id_Compteur, typeComp, bien, logement);
	}
	
	public static Iterateur<Compteur> getIterateurCompteur() {
        return it;
    }

    public static void setIterateurCompteur(Iterateur<Compteur> iterateur) {
        it = iterateur;
    }
}
