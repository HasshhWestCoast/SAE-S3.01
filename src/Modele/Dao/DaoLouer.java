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
import Modele.Dao.Requetes.Select.RequeteSelectLouer;
import Modele.Dao.Requetes.Select.RequeteSelectLouerById;
import Modele.Dao.Requetes.Insert.RequeteInsertLouer;

public class DaoLouer extends DaoModele<Louer> implements Dao<Louer>{

	private static Iterateur<Louer> it;

	public DaoLouer(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Louer t) throws SQLException {		
	}
	
	@Override
	public void create(Louer louer) throws SQLException {
	    RequeteInsertLouer requete = new RequeteInsertLouer();
	    try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
	        requete.parametres(prSt, louer);
	        prSt.executeUpdate();
	        System.out.println("Insertion réussie pour le louer !");
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de l'ajout d'une location : " + e.getMessage());
	        throw e;
	    }
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
	    
	    java.sql.Date dateSortie = curseur.getDate("date_Sortie");
	    String date_Sortie = new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateSortie);
	    
	    String bail = curseur.getString("bail");
	    String etat_lieux = curseur.getString("etat_lieux");
	    
	    String nbMois = curseur.getString("nb_Mois");
	    int nb_Mois = Integer.parseInt(nbMois);
	    
	    String loyePayer = curseur.getString("loyer_payer");
	    int loye_Payer = Integer.parseInt(loyePayer);
	    
	    String loyerMensTTCString = curseur.getString("loyer_mens_ttc");
	    double loyerMensTTC = Double.parseDouble(loyerMensTTCString);
	    
	    String provision_chargeMoisTTC = curseur.getString("provisions_chargesMois_TTC");
	    double provision_chargeMois_TTC = Double.parseDouble(provision_chargeMoisTTC);
	    
	    String cautionTTC = curseur.getString("caution_TTC");
	    double caution_TTC = Double.parseDouble(cautionTTC);
	    
	    String montantReelPaye = curseur.getString("montant_reel_payer");
	    double montant_Reel_Paye = Double.parseDouble(montantReelPaye);
	    
	    String Id_Bien = curseur.getString("Id_Bien");
	    String DateDerniereRegularisation = curseur.getString("Date_Derniere_Reg");
	    DaoBien daoBien = new DaoBien(this.connexion);
	    Bien bien = daoBien.findById(Id_Bien);
	    
	    String Id_Locataire = curseur.getString("Id_Locataire");
	    
	    DaoLocataire daoLocataire = new DaoLocataire(this.connexion);
	    Locataire locataire = daoLocataire.findById(Id_Locataire);
	    
	    
	    String iccID = curseur.getString("icc");
	    
	    DaoICC daoICC = new DaoICC(this.connexion);
	    ICC icc = daoICC.findById(iccID);
	    
	    return new Louer(date_Debut, date_Sortie, DateDerniereRegularisation, nb_Mois, loye_Payer, loyerMensTTC, provision_chargeMois_TTC, caution_TTC, bail, etat_lieux, montant_Reel_Paye, locataire, icc, bien);
	}
	
	public static Iterateur<Louer> getIterateurLouer() {
        return it;
    }

    public static void setIterateurLouer(Iterateur<Louer> iterateur) {
        it = iterateur;
    }
}

