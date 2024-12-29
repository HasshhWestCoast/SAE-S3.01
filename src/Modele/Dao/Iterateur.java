package Modele.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterateur<T> implements Iterator<T>{

	private boolean rsNext;
	private DaoModele<T> dm;
	private ResultSet rs;
	
	public Iterateur(ResultSet rs, DaoModele<T> dm) throws SQLException{
		this.rsNext = rs.next();
		this.dm = dm;
		this.rs = rs;
	}
	
	@Override
	public boolean hasNext() {
		return rsNext;
	}

	@Override
	public T next() {
		 if (!hasNext()) {
	            throw new NoSuchElementException("Aucun autre élément disponible dans le ResultSet");
	     }
		 try {
			T instance = dm.creerInstance(rs);
			rsNext = rs.next();
			return instance;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
}

