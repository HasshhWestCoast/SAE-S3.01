package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.Dao.Requetes.Select.RequeteSelectBien;
import Modele.Dao.Requetes.Select.RequeteSelectBienById;
import Modele.Dao.Requetes.Update.RequeteUpdateFactureDetachBien;
import Modele.Dao.Requetes.Delete.RequeteDeleteAssuranceByLogementFromBien;
import Modele.Dao.Requetes.Delete.RequeteDeleteBien;
import Modele.Dao.Requetes.Delete.RequeteDeleteChargeByBien;
import Modele.Dao.Requetes.Delete.RequeteDeleteCompteurByBien;
import Modele.Dao.Requetes.Delete.RequeteDeleteDiagnosticByBien;
import Modele.Dao.Requetes.Delete.RequeteDeleteImposerByBien;
import Modele.Dao.Requetes.Delete.RequeteDeleteLogementByBien;
import Modele.Dao.Requetes.Delete.RequeteDeleteLouerByBien;
import Modele.Dao.Requetes.Delete.RequeteDeleteReleveByCompteurFromBien;
import Modele.Dao.Requetes.Insert.RequeteInsertBien;


public class DaoBien extends DaoModele<Bien> implements Dao<Bien> {

	private static Iterateur<Bien> it;

	public DaoBien(Connection connexion) {
		super(connexion);
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
	public void delete(Bien bien) throws SQLException {
	    // Étape 1 : Supprimer les locations associées
	    RequeteDeleteLouerByBien deleteLouerRequete = new RequeteDeleteLouerByBien();
	    try (PreparedStatement prSt = connexion.prepareStatement(deleteLouerRequete.requete())) {
	        deleteLouerRequete.parametres(prSt, bien);
	        prSt.executeUpdate();
	    }

	    // Étape 2 : Détacher les factures
	    RequeteUpdateFactureDetachBien detachFactureRequete = new RequeteUpdateFactureDetachBien();
	    try (PreparedStatement prSt = connexion.prepareStatement(detachFactureRequete.requete())) {
	        detachFactureRequete.parametres(prSt, bien);
	        prSt.executeUpdate();
	    }

	    // Étape 3 : Supprimer les charges
	    RequeteDeleteChargeByBien deleteChargeRequete = new RequeteDeleteChargeByBien();
	    try (PreparedStatement prSt = connexion.prepareStatement(deleteChargeRequete.requete())) {
	        deleteChargeRequete.parametres(prSt, bien);
	        prSt.executeUpdate();
	    }

	    // Étape 4 : Supprimer les diagnostics
	    RequeteDeleteDiagnosticByBien deleteDiagnosticRequete = new RequeteDeleteDiagnosticByBien();
	    try (PreparedStatement prSt = connexion.prepareStatement(deleteDiagnosticRequete.requete())) {
	        deleteDiagnosticRequete.parametres(prSt, bien);
	        prSt.executeUpdate();
	    }

	    // Étape 5 : Supprimer les impositions
	    RequeteDeleteImposerByBien deleteImposerRequete = new RequeteDeleteImposerByBien();
	    try (PreparedStatement prSt = connexion.prepareStatement(deleteImposerRequete.requete())) {
	        deleteImposerRequete.parametres(prSt, bien);
	        prSt.executeUpdate();
	    }

	    // Étape 6 : Supprimer les compteurs et leurs relevés
	    RequeteDeleteReleveByCompteurFromBien deleteReleveRequete = new RequeteDeleteReleveByCompteurFromBien();
	    try (PreparedStatement prSt = connexion.prepareStatement(deleteReleveRequete.requete())) {
	        deleteReleveRequete.parametres(prSt, bien);
	        prSt.executeUpdate();
	    }

	    RequeteDeleteCompteurByBien deleteCompteurRequete = new RequeteDeleteCompteurByBien();
	    try (PreparedStatement prSt = connexion.prepareStatement(deleteCompteurRequete.requete())) {
	        deleteCompteurRequete.parametres(prSt, bien);
	        prSt.executeUpdate();
	    }

	 // Étape 7 : Supprimer les assurances liées aux logements associés au bien
	    RequeteDeleteAssuranceByLogementFromBien deleteAssuranceRequete = new RequeteDeleteAssuranceByLogementFromBien();
	    try (PreparedStatement prSt = connexion.prepareStatement(deleteAssuranceRequete.requete())) {
	        deleteAssuranceRequete.parametres(prSt, bien);
	        prSt.executeUpdate();
	    }

	    // Étape 8 : Supprimer les logements associés au bien
	    RequeteDeleteLogementByBien deleteLogementRequete = new RequeteDeleteLogementByBien();
	    try (PreparedStatement prSt = connexion.prepareStatement(deleteLogementRequete.requete())) {
	        deleteLogementRequete.parametres(prSt, bien);
	        prSt.executeUpdate();
	    }
	    
	    // Étape 9 : Supprimer le bien
	    RequeteDeleteBien deleteBienRequete = new RequeteDeleteBien();
	    try (PreparedStatement prSt = connexion.prepareStatement(deleteBienRequete.requete())) {
	        deleteBienRequete.parametres(prSt, bien);
	        prSt.executeUpdate();
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
