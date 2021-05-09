package by.jwd.task6.dao;

import java.util.List;
import java.util.Optional;

public interface DataAccessObject<T> {

    Optional<T> find(int id) throws Exception;

    void insert(T object) throws Exception;

    void remove(T object) throws Exception;

    void set(int id, T object) throws Exception;

    List<T> findAll() throws Exception;
}
