package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modele.Bien;
import Modele.Charge;
import Modele.Dao.Requetes.Select.RequeteSelectCharge;
import Modele.Dao.Requetes.Select.RequeteSelectChargeByBien;
import Modele.Dao.Requetes.Select.RequeteSelectChargeById;
import Modele.Dao.Requetes.Insert.RequeteInsertCharge;

public class DaoCharge extends DaoModele<Charge> implements Dao<Charge>{

	private static Iterateur<Charge> it;

	public DaoCharge(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Charge t) throws SQLException {		
	}
	
	@Override
	public void create(Charge charge) throws SQLException {
	    RequeteInsertCharge requete = new RequeteInsertCharge();
	    try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
	        requete.parametres(prSt, charge);
	        prSt.executeUpdate();
	        System.out.println("Insertion réussie pour la charge !");
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de l'ajout d'une charge : " + e.getMessage());
	        throw e;
	    }
	}
	
	@Override
	public void update(Charge t) throws SQLException {
	}

	public List<Charge> findByBien(String idBien) throws SQLException {
	    RequeteSelectChargeByBien requete = new RequeteSelectChargeByBien();
	    DaoBien daoBien = new DaoBien(connexion);
	   
	    try (PreparedStatement prSt = connexion.prepareStatement(requete.requete())) {
	        prSt.setString(1, idBien);
	        ResultSet rs = prSt.executeQuery();
	        List<Charge> charges = new ArrayList<>();
	        
	        while (rs.next()) {    	
	        	java.sql.Date dateCharge = rs.getDate("date_charge");
	    	    String date_Charge = new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateCharge);
	    	    	    	    	    	    	            
	    	    charges.add(new Charge(rs.getString("id_charges"), rs.getString("nom"), rs.getDouble("montant_reel"), rs.getDouble("montant_previsionnel"), rs.getInt("deductible"), date_Charge, daoBien.findById(rs.getString("Id_Bien"))));
	        }
	        return charges;
	    }
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

