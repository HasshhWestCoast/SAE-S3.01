package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Impot;
import Modele.Dao.Requetes.RequeteSelectImpot;
import Modele.Dao.Requetes.RequeteSelectImpotById;

public class DaoImpot extends DaoModele<Impot> implements Dao<Impot>{

	private static Iterateur<Impot> it;

	public DaoImpot(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Impot t) throws SQLException {		
	}
	
	@Override
	public void create(Impot t) throws SQLException {		
	}
	
	@Override
	public void update(Impot t) throws SQLException {
	}

	
	@Override
	public Impot findById(String... id) throws SQLException {
		RequeteSelectImpotById requete = new RequeteSelectImpotById();
	    return super.findById(requete, id);
	}


	@Override
	public List<Impot> findAll() throws SQLException {
		RequeteSelectImpot requete = new RequeteSelectImpot();
		
	    List<Impot> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoImpot.setIterateurCreneau(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected Impot creerInstance(ResultSet curseur) throws SQLException {
			    
	    String id_Impot = curseur.getString("id_Impot");
	    String annee = curseur.getString("annee");
	    
	    String montantString = curseur.getString("montant");
	    double montant = Double.parseDouble(montantString);
	    
	    return new Impot(id_Impot, montant, annee);
	}
	
	public static Iterateur<Impot> getIterateurCreneau() {
        return it;
    }

    public static void setIterateurCreneau(Iterateur<Impot> iterateur) {
        it = iterateur;
    }
}
