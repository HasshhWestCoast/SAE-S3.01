package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Entreprise;
import Modele.Dao.Requetes.RequeteSelectEntreprise;
import Modele.Dao.Requetes.RequeteSelectEntrepriseById;

public class DaoEntreprise extends DaoModele<Entreprise> implements Dao<Entreprise>{

	private static Iterateur<Entreprise> it;

	public DaoEntreprise(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Entreprise t) throws SQLException {		
	}
	
	@Override
	public void create(Entreprise t) throws SQLException {		
	}
	
	@Override
	public void update(Entreprise t) throws SQLException {
	}

	
	@Override
	public Entreprise findById(String... id) throws SQLException {
		RequeteSelectEntrepriseById requete = new RequeteSelectEntrepriseById();
	    return super.findById(requete, id);
	}


	@Override
	public List<Entreprise> findAll() throws SQLException {
		RequeteSelectEntreprise requete = new RequeteSelectEntreprise();
		
	    List<Entreprise> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoEntreprise.setIterateurCreneau(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected Entreprise creerInstance(ResultSet curseur) throws SQLException {
			    
	    String SIRET = curseur.getString("SIRET");
	    String nom = curseur.getString("nom");
	    String adresse = curseur.getString("adresse");
	    String codepostal = curseur.getString("codepostal");	    
	    String ville = curseur.getString("ville");	    
	    String mail = curseur.getString("mail");	    
	    String telephone = curseur.getString("telephone");	    
	    String iban = curseur.getString("iban");
	    
	    return new Entreprise(SIRET, nom, adresse, codepostal, ville, mail, telephone, iban);
	}
	
	public static Iterateur<Entreprise> getIterateurCreneau() {
        return it;
    }

    public static void setIterateurCreneau(Iterateur<Entreprise> iterateur) {
        it = iterateur;
    }
}
