package by.jwd.task6.dao;

import by.jwd.task6.fleet.Aircraft;
import by.jwd.task6.util.ArgumentValidationException;

import java.util.List;
import java.util.Optional;

public interface AircraftDao<A extends Aircraft> {

    Optional<A> findById(int id) throws DaoException, ArgumentValidationException;

    void insert(A aircraft) throws DaoException, ArgumentValidationException;

    void remove(A aircraft) throws DaoException, ArgumentValidationException;

    void set(int id, A aircraft) throws DaoException, ArgumentValidationException;

    List<A> findAll() throws DaoException;
}
