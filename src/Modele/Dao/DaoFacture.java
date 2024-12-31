package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.Entreprise;
import Modele.Facture;
import Modele.Logement;
import Modele.Dao.Requetes.RequeteSelectFacture;
import Modele.Dao.Requetes.RequeteSelectFactureById;

public class DaoFacture  extends DaoModele<Facture> implements Dao<Facture>{

	private static Iterateur<Facture> it;

	public DaoFacture(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Facture t) throws SQLException {		
	}
	
	@Override
	public void create(Facture t) throws SQLException {		
	}
	
	@Override
	public void update(Facture t) throws SQLException {
	}

	
	@Override
	public Facture findById(String... id) throws SQLException {
		RequeteSelectFactureById requete = new RequeteSelectFactureById();
	    return super.findById(requete, id);
	}


	@Override
	public List<Facture> findAll() throws SQLException {
		RequeteSelectFacture requete = new RequeteSelectFacture();
		
	    List<Facture> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoFacture.setIterateurFacture(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected Facture creerInstance(ResultSet curseur) throws SQLException {
		
	    java.sql.Date dateEmission = curseur.getDate("dateEmission");
	    String date_Emission = new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateEmission);
	    
	    java.sql.Date datePaiement = curseur.getDate("date_Paiement");
	    String date_Paiement = new java.text.SimpleDateFormat("dd/MM/yyyy").format(datePaiement);
	    
	    String Id_Facture = curseur.getString("Id_Facture");
	    String mode_Paiement = curseur.getString("mode_Paiement");
	    String numero_Devis = curseur.getString("numero_Devis");
	    String designation = curseur.getString("designation");
	    
	    String montantReelVerse = curseur.getString("montant_Reel_Verse");
	    double montant_Reel_Verse = Double.parseDouble(montantReelVerse);
	    
	    String montantString = curseur.getString("montant");
	    double montant = Double.parseDouble(montantString);
	    
	    String imputableLocataire = curseur.getString("imputable_Locataire");
	    double imputable_Locataire = Double.parseDouble(imputableLocataire);
	    
	    String acompteVerse = curseur.getString("acompte_Verse");
	    double acompte_Verse = Double.parseDouble(acompteVerse);
	    
	    String ID_Logement = curseur.getString("ID_Logement");
	    String ID_Bien = curseur.getString("ID_Bien");
	    String SIRET = curseur.getString("SIRET");
	    
	    DaoLogement daoLogement = new DaoLogement(this.connexion);
	    Logement logement = daoLogement.findById(ID_Logement);

	    DaoBien daoBien = new DaoBien(this.connexion);
	    Bien bien = daoBien.findById(ID_Bien);
	    
	    DaoEntreprise daoEntreprise = new DaoEntreprise(this.connexion);
	    Entreprise entreprise = daoEntreprise.findById(SIRET);
	    
	    return new Facture(Id_Facture, date_Emission, date_Paiement, mode_Paiement, numero_Devis, designation, montant_Reel_Verse, montant, imputable_Locataire, acompte_Verse, logement, bien, entreprise);
	}
	
	public static Iterateur<Facture> getIterateurFacture() {
        return it;
    }

    public static void setIterateurFacture(Iterateur<Facture> iterateur) {
        it = iterateur;
    }
}

