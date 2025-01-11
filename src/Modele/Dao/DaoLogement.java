package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.Logement;
import Modele.Dao.Requetes.RequeteSelectLogement;
import Modele.Dao.Requetes.RequeteSelectLogementById;

public class DaoLogement extends DaoModele<Logement> implements Dao<Logement>{

	private static Iterateur<Logement> it;

	public DaoLogement(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Logement t) throws SQLException {		
	}
	
	@Override
	public void create(Logement t) throws SQLException {		
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
