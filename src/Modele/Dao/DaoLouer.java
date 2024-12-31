package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.ICC;
import Modele.Locataire;
import Modele.Louer;
import Modele.Dao.Requetes.RequeteSelectLouer;
import Modele.Dao.Requetes.RequeteSelectLouerById;

public class DaoLouer extends DaoModele<Louer> implements Dao<Louer>{

	private static Iterateur<Louer> it;

	public DaoLouer(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Louer t) throws SQLException {		
	}
	
	@Override
	public void create(Louer t) throws SQLException {		
	}
	
	@Override
	public void update(Louer t) throws SQLException {
	}

	
	@Override
	public Louer findById(String... id) throws SQLException {
		RequeteSelectLouerById requete = new RequeteSelectLouerById();
	    return super.findById(requete, id);
	}


	@Override
	public List<Louer> findAll() throws SQLException {
		RequeteSelectLouer requete = new RequeteSelectLouer();
		
	    List<Louer> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoLouer.setIterateurLouer(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected Louer creerInstance(ResultSet curseur) throws SQLException {
		
	    java.sql.Date dateDebut = curseur.getDate("date_Debut");
	    String date_Debut = new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateDebut);
	    
	    String bail = curseur.getString("bail");
	    String etat_lieux = curseur.getString("etat_lieux");
	    
	    String nbMois = curseur.getString("nb_Mois");
	    int nb_Mois = Integer.parseInt(nbMois);
	    
	    String loyePayer = curseur.getString("loye_Payer");
	    double loye_Payer = Double.parseDouble(loyePayer);
	    
	    String provision_chargeMoisTTC = curseur.getString("provision_chargeMois_TTC");
	    double provision_chargeMois_TTC = Double.parseDouble(provision_chargeMoisTTC);
	    
	    String cautionTTC = curseur.getString("caution_TTC");
	    double caution_TTC = Double.parseDouble(cautionTTC);
	    
	    String montantReelPaye = curseur.getString("montant_Reel_Paye");
	    double montant_Reel_Paye = Double.parseDouble(montantReelPaye);
	    
	    String Id_Bien = curseur.getString("Id_Bien");
	    
	    DaoBien daoBien = new DaoBien(this.connexion);
	    Bien bien = daoBien.findById(Id_Bien);
	    
	    String Id_Locataire = curseur.getString("Id_Locataire");
	    
	    DaoLocataire daoLocataire = new DaoLocataire(this.connexion);
	    Locataire locataire = daoLocataire.findById(Id_Locataire);
	    
	    
	    String annee = curseur.getString("annee");
	    String trimestre = curseur.getString("trimestre");
	    
	    DaoICC daoICC = new DaoICC(this.connexion);
	    ICC icc = daoICC.findById(annee, trimestre);
	    
	    return new Louer(date_Debut, nb_Mois, loye_Payer, provision_chargeMois_TTC, caution_TTC, bail, etat_lieux, montant_Reel_Paye, locataire, icc, bien);
	}
	
	public static Iterateur<Louer> getIterateurLouer() {
        return it;
    }

    public static void setIterateurLouer(Iterateur<Louer> iterateur) {
        it = iterateur;
    }
}

