package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.ICC;
import Modele.Dao.Requetes.RequeteSelectICC;
import Modele.Dao.Requetes.RequeteSelectICCById;
import Modele.Dao.Requetes.Insert.RequeteInsertICC;


public class DaoICC extends DaoModele<ICC> implements Dao<ICC>{

	private static Iterateur<ICC> it;

	public DaoICC(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(ICC t) throws SQLException {		
	}
	
	@Override
	public void create(ICC icc) throws SQLException {
	    RequeteInsertICC requete = new RequeteInsertICC();
	    try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
	        requete.parametres(prSt, icc);
	        prSt.executeUpdate();
	        System.out.println("Insertion réussie pour l'ICC !");
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de l'ajout d'un ICC : " + e.getMessage());
	        throw e;
	    }
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
		
		String iccString = curseur.getString("ICC");
	    int icc = Integer.parseInt(iccString);
	    
	    String annee = curseur.getString("annee");
	    String trimestre = curseur.getString("trimestre");
	    
	    String indiceString = curseur.getString("indice");
	    double indice = Double.parseDouble(indiceString);
	        
	    return new ICC(icc, annee, trimestre, indice);
	}
	
	public static Iterateur<ICC> getIterateurICC() {
        return it;
    }

	
	
    public static void setIterateurICC(Iterateur<ICC> iterateur) {
        it = iterateur;
    }

}

