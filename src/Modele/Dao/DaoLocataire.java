package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Locataire;
import Modele.Dao.Requetes.RequeteSelectLocataire;
import Modele.Dao.Requetes.RequeteSelectLocataireById;

public class DaoLocataire extends DaoModele<Locataire> implements Dao<Locataire>{

	private static Iterateur<Locataire> it;

	public DaoLocataire(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Locataire t) throws SQLException {		
	}
	
	@Override
	public void create(Locataire t) throws SQLException {		
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

