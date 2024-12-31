package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.ICC;
import Modele.Dao.Requetes.RequeteSelectICC;
import Modele.Dao.Requetes.RequeteSelectICCById;


public class DaoICC extends DaoModele<ICC> implements Dao<ICC>{

	private static Iterateur<ICC> it;

	public DaoICC(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(ICC t) throws SQLException {		
	}
	
	@Override
	public void create(ICC t) throws SQLException {		
	}
	
	@Override
	public void update(ICC t) throws SQLException {
	}

	
	@Override
	public ICC findById(String... id) throws SQLException {
		RequeteSelectICCById requete = new RequeteSelectICCById();
	    return super.findById(requete, id);
	}

	@Override
	public List<ICC> findAll() throws SQLException {
		RequeteSelectICC requete = new RequeteSelectICC();
		
	    List<ICC> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoICC.setIterateurICC(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected ICC creerInstance(ResultSet curseur) throws SQLException {
		
	    String annee = curseur.getString("annee");
	    String trimestre = curseur.getString("trimestre");
	    
	    String indiceString = curseur.getString("indice");
	    int indice = Integer.parseInt(indiceString);
	        
	    return new ICC(annee, trimestre, indice);
	}
	
	public static Iterateur<ICC> getIterateurICC() {
        return it;
    }

    public static void setIterateurICC(Iterateur<ICC> iterateur) {
        it = iterateur;
    }

}

