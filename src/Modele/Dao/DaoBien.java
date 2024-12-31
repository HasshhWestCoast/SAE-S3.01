package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.Logement;
import Modele.Dao.Requetes.RequeteSelectBien;
import Modele.Dao.Requetes.RequeteSelectBienById;


public class DaoBien extends DaoModele<Bien> implements Dao<Bien> {

	private static Iterateur<Bien> it;

	public DaoBien(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Bien t) throws SQLException {		
	}
	
	@Override
	public void create(Bien t) throws SQLException {		
	}
	
	@Override
	public void update(Bien t) throws SQLException {
	}

	
	@Override
	public Bien findById(String... id) throws SQLException {
		RequeteSelectBienById requete = new RequeteSelectBienById();
	    return super.findById(requete, id);
	}


	@Override
	public List<Bien> findAll() throws SQLException {
		RequeteSelectBien requete = new RequeteSelectBien();
		
	    List<Bien> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoBien.setIterateurBien(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected Bien creerInstance(ResultSet curseur) throws SQLException {
		
	    java.sql.Date periodeConstruction = curseur.getDate("periode_Construction");
	    String periode_Construction = new java.text.SimpleDateFormat("dd/MM/yyyy").format(periodeConstruction);
	    
	    String id_Bien = curseur.getString("id_Bien");
	    String adresse = curseur.getString("adresse");
	    String ville = curseur.getString("ville");
	    String type_Bien = curseur.getString("type_Bien");
	    String codePostal = curseur.getString("codepostal");
	    
	    String surfaceHabitable = curseur.getString("surface_Habitable");
	    double surface_Habitable = Double.parseDouble(surfaceHabitable);

	    String ID_Logement = curseur.getString("ID_Logement");
	    
	    DaoLogement daoLogement = new DaoLogement(this.connexion);
	    Logement logement = daoLogement.findById(ID_Logement);

	    return new Bien(id_Bien, surface_Habitable, adresse, ville, type_Bien, codePostal, periode_Construction, logement);
	}
	
	public static Iterateur<Bien> getIterateurBien() {
        return it;
    }

    public static void setIterateurBien(Iterateur<Bien> iterateur) {
        it = iterateur;
    }

}
