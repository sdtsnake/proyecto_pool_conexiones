package work.oscarramos.java.jdbc.service;

import java.sql.SQLException;
import java.util.List;

public interface Servicio<T>{
    List<T> findAll() throws SQLException;
    T findById(Long id) throws SQLException;
    T save(T t) throws SQLException;
    void delete(Long id) throws SQLException;
}
