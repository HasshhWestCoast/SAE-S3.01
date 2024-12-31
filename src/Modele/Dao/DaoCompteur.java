package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.Compteur;
import Modele.Logement;
import Modele.Dao.Requetes.RequeteSelectCompteur;
import Modele.Dao.Requetes.RequeteSelectCompteurById;

public class DaoCompteur extends DaoModele<Compteur> implements Dao<Compteur>{

	private static Iterateur<Compteur> it;

	public DaoCompteur(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Compteur t) throws SQLException {		
	}
	
	@Override
	public void create(Compteur t) throws SQLException {		
	}
	
	@Override
	public void update(Compteur t) throws SQLException {
	}

	
	@Override
	public Compteur findById(String... id) throws SQLException {
		RequeteSelectCompteurById requete = new RequeteSelectCompteurById();
	    return super.findById(requete, id);
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
		
		java.sql.Date dateRelevé = curseur.getDate("date_Relevé");
	    String date_Relevé = new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateRelevé);
	    
	    String id_Compteur = curseur.getString("id_Compteur");
	    String typeComp = curseur.getString("typeComp");
	    
	    String indexCompteurString = curseur.getString("indexCompteur");
	    int indexCompteur = Integer.parseInt(indexCompteurString);
	    
	    String ID_Logement = curseur.getString("ID_Logement");
	    String ID_Bien = curseur.getString("ID_Bien");
	    
	    DaoLogement daoLogement = new DaoLogement(this.connexion);
	    Logement logement = daoLogement.findById(ID_Logement);

	    DaoBien daoBien = new DaoBien(this.connexion);
	    Bien bien = daoBien.findById(ID_Bien);
	    
	    
	    return new Compteur(id_Compteur, typeComp, indexCompteur, date_Relevé, bien, logement);
	}
	
	public static Iterateur<Compteur> getIterateurCompteur() {
        return it;
    }

    public static void setIterateurCompteur(Iterateur<Compteur> iterateur) {
        it = iterateur;
    }
}
