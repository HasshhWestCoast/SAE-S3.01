package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.assurance;
import Modele.Dao.Requetes.RequeteSelectAssuranceById;
import Modele.Dao.Requetes.RequeteSelectCreneau;

public class DaoAssurance extends DaoModele<assurance> implements Dao<assurance>{

	private static Iterateur<assurance> it;

	public DaoAssurance(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(assurance t) throws SQLException {		
	}
	
	@Override
	public void create(assurance t) throws SQLException {		
	}
	
	@Override
	public void update(assurance t) throws SQLException {
	}

	
	@Override
	public assurance findById(String... id) throws SQLException {
		RequeteSelectAssuranceById requete = new RequeteSelectAssuranceById();
	    return super.findById(requete, id);
	}


	@Override
	public List<assurance> findAll() throws SQLException {
		RequeteSelectCreneau requete = new RequeteSelectCreneau();
		
	    List<assurance> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoAssurance.setIterateurCreneau(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected assurance creerInstance(ResultSet curseur) throws SQLException {
		
	    java.sql.Date dateéchéance = curseur.getDate("date_échéance");
	    
	    String date_échéance = new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateéchéance);
	    
	    String numero_Police = curseur.getString("numero_police");
	    String montant = curseur.getString("montant");

	    /*
	    DaoLogement daoLogement = new DaoImmeuble(this.connexion);
	    Logement log = daoLogement.findById(idLogement);

	    DaoLogement daoMat = new DaoMat(this.connexion);
	    Mat matiere = daoMat.findById(idMat);
	     */
	    return new assurance(numero_Police, montant, date_échéance, null, null );
	}
	
	public static Iterateur<assurance> getIterateurCreneau() {
        return it;
    }

    public static void setIterateurCreneau(Iterateur<assurance> iterateur) {
        it = iterateur;
    }

}
