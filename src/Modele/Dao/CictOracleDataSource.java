package Modele.Dao;

import oracle.jdbc.pool.OracleDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class CictOracleDataSource extends OracleDataSource{

	private static CictOracleDataSource instance;
	private Connection connexion;
	
	private CictOracleDataSource() throws SQLException {
		this.setURL("jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:etupre");
	}

	public static CictOracleDataSource getInstance() throws SQLException {
		if (instance == null) {
			instance = new CictOracleDataSource();
		}
		return instance;
	}
	
	public void creerAcces(String login, String mdp){
		try {
			CictOracleDataSource.getInstance().setPassword(mdp);
			CictOracleDataSource.getInstance().setUser(login);
			connexion = CictOracleDataSource.getInstance().getConnection();
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private Connection getConnectionBD() {
		return connexion; 
	}
	
	public void Deconnecter() throws SQLException {
		getConnectionBD().close();
		connexion = null;
	}
	
	
}
