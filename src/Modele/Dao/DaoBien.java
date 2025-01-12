package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.Charge;
import Modele.Facture;
import Modele.Logement;
import Modele.Louer;
import Modele.Dao.Requetes.Select.RequeteSelectBien;
import Modele.Dao.Requetes.Select.RequeteSelectBienById;
import Modele.Dao.Requetes.Insert.RequeteInsertBien;


public class DaoBien extends DaoModele<Bien> implements Dao<Bien> {

	private static Iterateur<Bien> it;

	public DaoBien(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Bien bien) throws SQLException {
	   /*
		try {
	        DaoCharge daoCharge = new DaoCharge(connexion);
	        List<Charge> charges = daoCharge.findByBien(bien.getIdBien());
	        for (Charge charge : charges) {
	            daoCharge.delete(charge);
	        }

	        DaoLogement daoLogement = new DaoLogement(connexion);
	        List<Logement> logements = daoLogement.findByBien(bien.getIdBien());
	        for (Logement logement : logements) {
	            daoLogement.delete(logement);
	        }

	        DaoFacture daoFacture = new DaoFacture(connexion);
	        List<Facture> factures = daoFacture.findByBien(bien.getIdBien());
	        for (Facture facture : factures) {
	            daoFacture.delete(facture);
	        }

	        DaoLouer daoLouer = new DaoLouer(connexion);
	        List<Louer> locations = daoLouer.findByBien(bien.getIdBien());
	        for (Louer location : locations) {
	            daoLouer.delete(location);
	        }

	        RequeteDeleteBien requete = new RequeteDeleteBien();
	        try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
	            requete.parametres(prSt, bien);
	            prSt.executeUpdate();
	            System.out.println("Suppression réussie pour le bien avec ID: " + bien.getIdBien());
	        }
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de la suppression du bien : " + e.getMessage());
	        throw e;
	    }
	    */
	}


	
	@Override
    public void create(Bien bien) throws SQLException {
        RequeteInsertBien requete = new RequeteInsertBien();
        try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
            requete.parametres(prSt, bien);
            prSt.executeUpdate();
            System.out.println("Insertion réussie pour le bien !");
        } catch (SQLException e) {
        	System.err.println("Erreur lors de l'ajout d'un bien : " + e.getMessage());
	        throw e;
        }
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
		
	    String periode_Construction = curseur.getString("periode_Construction");
	    
	    String id_Bien = curseur.getString("id_Bien");
	    String adresse = curseur.getString("adresse");
	    String ville = curseur.getString("ville");
	    String type_Bien = curseur.getString("type_Bien");
	    String codePostal = curseur.getString("codepostal");
	    
	    return new Bien(id_Bien, adresse, ville, type_Bien, codePostal, periode_Construction);
	}
	
	public static Iterateur<Bien> getIterateurBien() {
        return it;
    }

    public static void setIterateurBien(Iterateur<Bien> iterateur) {
        it = iterateur;
    }

}
