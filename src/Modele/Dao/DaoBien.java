package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.Dao.Requetes.Select.RequeteSelectBien;
import Modele.Dao.Requetes.Select.RequeteSelectBienById;
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
