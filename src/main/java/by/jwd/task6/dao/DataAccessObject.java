package by.jwd.task6.dao;

import java.util.List;
import java.util.Optional;

public interface DataAccessObject<T> {

    Optional<T> find(int id) throws DaoException, IllegalArgumentException;

    void insert(T object) throws DaoException, IllegalArgumentException;

    void remove(T object) throws DaoException, IllegalArgumentException;

    void set(int id, T object) throws DaoException, IllegalArgumentException;

    List<T> findAll() throws DaoException;
}
