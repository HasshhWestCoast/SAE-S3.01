package Modele.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Modele.Bien;
import Modele.Diagnostic;
import Modele.Dao.Requetes.RequeteSelectDiagnostic;
import Modele.Dao.Requetes.RequeteSelectDiagnosticById;


public class DaoDiagnostic  extends DaoModele<Diagnostic> implements Dao<Diagnostic>{

	private static Iterateur<Diagnostic> it;

	public DaoDiagnostic(Connection connexion) {
		super(connexion);
	}

	@Override
	public void delete(Diagnostic t) throws SQLException {		
	}
	
	@Override
	public void create(Diagnostic t) throws SQLException {		
	}
	
	@Override
	public void update(Diagnostic t) throws SQLException {
	}

	
	@Override
	public Diagnostic findById(String... id) throws SQLException {
		RequeteSelectDiagnosticById requete = new RequeteSelectDiagnosticById();
	    return super.findById(requete, id);
	}


	@Override
	public List<Diagnostic> findAll() throws SQLException {
		RequeteSelectDiagnostic requete = new RequeteSelectDiagnostic();
		
	    List<Diagnostic> list = find(requete);
	    
	    PreparedStatement prSt = connexion.prepareStatement(requete.requete());
	    ResultSet resultSet = prSt.executeQuery();
	    
	    DaoDiagnostic.setIterateurCreneau(new Iterateur<>(resultSet, this));

	    return list;
	}

	@Override
	protected Diagnostic creerInstance(ResultSet curseur) throws SQLException {
		
		java.sql.Date dateValidite = curseur.getDate("dateValidite");
	    String date_Validite = new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateValidite);
	    
	    String id_Diagnostic = curseur.getString("id_Diagnostic");
	    String type_Diagnostic = curseur.getString("type_Diagnostic");
	    
	    String Id_Bien = curseur.getString("Id_Bien");

	    DaoBien daoBien = new DaoBien(this.connexion);
	    Bien bien = daoBien.findById(Id_Bien);

	    return new Diagnostic(id_Diagnostic, date_Validite, type_Diagnostic, bien);
	}
	
	public static Iterateur<Diagnostic> getIterateurCreneau() {
        return it;
    }

    public static void setIterateurCreneau(Iterateur<Diagnostic> iterateur) {
        it = iterateur;
    }
}
