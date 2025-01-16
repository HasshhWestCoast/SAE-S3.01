package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Modele.Releve;
import Modele.Compteur;
import Modele.Dao.Requetes.Insert.RequeteInsertReleve;
import Modele.Dao.Requetes.Delete.RequeteDeleteReleve;
import Modele.Dao.Requetes.Select.RequeteSelectReleve;
import Modele.Dao.Requetes.Select.RequeteSelectReleveById;

public class DaoReleve extends DaoModele<Releve> implements Dao<Releve> {

    private static Iterateur<Releve> it;

    public DaoReleve(Connection connexion) {
        super(connexion);
    }

    @Override
    public void create(Releve releve) throws SQLException {
        RequeteInsertReleve requete = new RequeteInsertReleve();
        try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
            requete.parametres(prSt, releve);
            prSt.executeUpdate();
            System.out.println("Insertion réussie pour le relevé !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout d'un relevé : " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(Releve releve) throws SQLException {
        RequeteDeleteReleve requete = new RequeteDeleteReleve();
        try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
            requete.parametres(prSt, releve);
            prSt.executeUpdate();
            System.out.println("Suppression réussie pour le relevé avec DateReleve: " + releve.getDateReleve());
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du relevé : " + e.getMessage());
            throw e;
        }
    }
    
	@Override
	public void update(Releve t) throws SQLException {
	
	}

    @Override
    public Releve findById(String... id) throws SQLException {
        RequeteSelectReleveById requete = new RequeteSelectReleveById();
        return super.findById(requete, id);
    }

    @Override
    public List<Releve> findAll() throws SQLException {
    	RequeteSelectReleve requete = new RequeteSelectReleve();
        List<Releve> list = find(requete);

        PreparedStatement prSt = connexion.prepareStatement(requete.requete());
        ResultSet rs = prSt.executeQuery();
        
        DaoReleve.setIterateurReleve(new Iterateur<Releve>(rs, this));
        
        return list;
    }

    @Override
    protected Releve creerInstance(ResultSet curseur) throws SQLException {
	    java.sql.Date datereleve = curseur.getDate("date_RELEVÉ");
	    String date_releve = new java.text.SimpleDateFormat("dd/MM/yyyy").format(datereleve);
    	
	    int indexReleve = curseur.getInt("indexCompteur");
        String idCompteur = curseur.getString("Id_Compteur");
        Compteur compteur = new DaoCompteur(connexion).findById(idCompteur);
        
        return new Releve(date_releve, indexReleve, compteur);
    }

    public static Iterateur<Releve> getIterateurReleve() {
        return it;
    }

    public static void setIterateurReleve(Iterateur<Releve> iterateur) {
        it = iterateur;
    }

}
