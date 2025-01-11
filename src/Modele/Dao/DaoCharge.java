package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.Charge;
import Modele.Dao.Requetes.RequeteSelectCharge;
import Modele.Dao.Requetes.RequeteSelectChargeById;

public class DaoCharge extends DaoModele<Charge> implements Dao<Charge>{

	private static Iterateur<Charge> it;

	public DaoCharge(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Charge t) throws SQLException {		
	}
	
	@Override
	public void create(Charge t) throws SQLException {		
	}
	
	@Override
	public void update(Charge t) throws SQLException {
	}

	
	@Override
	public Charge findById(String... id) throws SQLException {
		RequeteSelectChargeById requete = new RequeteSelectChargeById();
	    return super.findById(requete, id);
	}


	@Override
	public List<Charge> findAll() throws SQLException {
		RequeteSelectCharge requete = new RequeteSelectCharge();
		
	    List<Charge> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoCharge.setIterateurCharge(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected Charge creerInstance(ResultSet curseur) throws SQLException {
		
	    java.sql.Date dateCharge = curseur.getDate("date_charge");
	    String date_Charge = new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateCharge);
	    
	    String idCharge = curseur.getString("id_charges");
	    String nom = curseur.getString("nom");	    
	    
	    String montantReel = curseur.getString("montant_reel");
	    double montant_reel = Double.parseDouble(montantReel);

	    String montantPrevisionnel = curseur.getString("montant_previsionnel");
	    double montant_previsionnel = Double.parseDouble(montantPrevisionnel);
	    
	    String deductibleString = curseur.getString("deductible");
	    int deductible = Integer.parseInt(deductibleString);
	    
	    String Id_Bien = curseur.getString("Id_Bien");
	    
	    DaoBien daoBien = new DaoBien(this.connexion);
	    Bien bien = daoBien.findById(Id_Bien);
	    
	    return new Charge(idCharge, nom, montant_reel, montant_previsionnel, deductible, date_Charge, bien);
	}
	
	public static Iterateur<Charge> getIterateurCharge() {
        return it;
    }

    public static void setIterateurCharge(Iterateur<Charge> iterateur) {
        it = iterateur;
    }

}

