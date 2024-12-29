package Modele.Dao;

import java.sql.SQLException;
import java.util.List;

import Modele.assurance;

public class DaoAssurance implements Dao<assurance>{

	@Override
	public void create(assurance t) throws SQLException {		
	}

	@Override
	public assurance findById(String... id) throws SQLException {
		return null;
	}

	@Override
	public void update(assurance t) throws SQLException {
		
	}

	@Override
	public List<assurance> findAll() throws SQLException {
		return null;
	}

	@Override
	public void delete(assurance t) throws SQLException {		
	}

}
