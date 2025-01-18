package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.Entreprise;
import Modele.Logement;
import Modele.assurance;
import Modele.Dao.Requetes.Select.RequeteSelectAssuranceById;
import Modele.Dao.Requetes.Insert.RequeteInsertAssurance;
import Modele.Dao.Requetes.Select.RequeteSelectAssurance;

public class DaoAssurance extends DaoModele<assurance> implements Dao<assurance>{

	private static Iterateur<assurance> it;

	public DaoAssurance(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(assurance t) throws SQLException {
		// Fait depuis Entreprise ou Logement
	}
	
	@Override
	public void create(assurance donnee) throws SQLException {
	    RequeteInsertAssurance requete = new RequeteInsertAssurance();
	    try (PreparedStatement prSt = this.connexion.prepareStatement(requete.requete())) {
	        requete.parametres(prSt, donnee);
	        prSt.executeUpdate();
	    } catch (SQLException e) {
	        throw new SQLException("Erreur lors de la création d'une assurance : " + e.getMessage(), e);
	    }
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
		RequeteSelectAssurance requete = new RequeteSelectAssurance();
		
	    List<assurance> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoAssurance.setIterateurAssurance(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected assurance creerInstance(ResultSet curseur) throws SQLException {
		
	    java.sql.Date dateéchéance = curseur.getDate("date_echeance");
	    
	    String date_échéance = new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateéchéance);
	    String numero_Police = curseur.getString("numero_police");
	    String montantString = curseur.getString("montant");
	    float montant = Float.parseFloat(montantString);
	    
	    String SIRET = curseur.getString("SIRET");
	    String ID_Logement = curseur.getString("ID_Logement");
	    String ID_Bien = curseur.getString("ID_Bien");

	    
	    String protectionJuridiqueString = curseur.getString("Protection_Juridique");
	    double protectionJuridique = Double.parseDouble(protectionJuridiqueString);
	    
	    DaoLogement daoLogement = new DaoLogement(this.connexion);
	    Logement logement = daoLogement.findById(ID_Logement);

	    DaoEntreprise daoEntreprise = new DaoEntreprise(this.connexion);
	    Entreprise entreprise = daoEntreprise.findById(SIRET);
	    
	    Bien bien = new DaoBien(this.connexion).findById(ID_Bien);
	    
	    return new assurance(numero_Police, montant, date_échéance, protectionJuridique, logement, entreprise, bien);
	}
	
	public static Iterateur<assurance> getIterateurAssurance() {
        return it;
    }

    public static void setIterateurAssurance(Iterateur<assurance> iterateur) {
        it = iterateur;
    }
}
