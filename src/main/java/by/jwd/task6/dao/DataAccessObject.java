package by.jwd.task6.dao;

import by.jwd.task6.util.ArgumentValidationException;

import java.util.List;
import java.util.Optional;

public interface DataAccessObject<T> {

    Optional<T> find(int id) throws DaoException, ArgumentValidationException;

    void insert(T object) throws DaoException, ArgumentValidationException;

    void remove(T object) throws DaoException, ArgumentValidationException;

    void set(int id, T object) throws DaoException, ArgumentValidationException;

    List<T> findAll() throws DaoException;
}
