package work.oscarramos.java.jdbc.repository;

import java.util.List;

public interface Repositorio<T> {
    List<T> findAll();
    T findById(Long id);
    void save(T t);
    void delete(Long id);
}
