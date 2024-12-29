package Modele.Dao;

import java.sql.SQLException;
import java.util.List;


public interface Dao<T>  {
    
	void create(T t) throws SQLException ;
    
    T findById(String... id) throws SQLException;
    
    void update(T t) throws SQLException;
    
    List<T> findAll() throws SQLException;
    
     void delete(T t) throws SQLException;

}
