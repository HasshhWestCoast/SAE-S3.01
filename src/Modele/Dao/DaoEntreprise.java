package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Entreprise;
import Modele.Dao.Requetes.Select.RequeteSelectEntreprise;
import Modele.Dao.Requetes.Select.RequeteSelectEntrepriseById;
import Modele.Dao.Requetes.Delete.RequeteDeleteEntreprise;
import Modele.Dao.Requetes.Insert.RequeteInsertEntreprise;

public class DaoEntreprise extends DaoModele<Entreprise> implements Dao<Entreprise>{

	private static Iterateur<Entreprise> it;

	public DaoEntreprise(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Entreprise entreprise) throws SQLException {
	    String siret = entreprise.getSiret();

	    DaoAssurance daoAssurance = new DaoAssurance(connexion);
	    daoAssurance.deleteByEntreprise(siret);

	    DaoFacture daoFacture = new DaoFacture(connexion);
	    daoFacture.deleteByEntreprise(siret);

	    RequeteDeleteEntreprise requete = new RequeteDeleteEntreprise();
	    try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
	        requete.parametres(prSt, entreprise);
	        prSt.executeUpdate();
	        System.out.println("Suppression réussie pour l'entreprise avec SIRET: " + siret);
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de la suppression de l'entreprise : " + e.getMessage());
	        throw e;
	    }
	}


	
	@Override
	public void create(Entreprise entreprise) throws SQLException {
	    RequeteInsertEntreprise requete = new RequeteInsertEntreprise();
	    try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
	        requete.parametres(prSt, entreprise);
	        prSt.executeUpdate();
	        System.out.println("Entreprise ajoutée avec succès !");
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de l'ajout de l'entreprise : " + e.getMessage());
	        throw e;
	    }
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
	    
	    DaoEntreprise.setIterateurEntreprise(new Iterateur<>(resultSet, this));

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
	
	public static Iterateur<Entreprise> getIterateurEntreprise() {
        return it;
    }

    public static void setIterateurEntreprise(Iterateur<Entreprise> iterateur) {
        it = iterateur;
    }
}
