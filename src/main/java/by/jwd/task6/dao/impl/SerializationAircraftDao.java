package by.jwd.task6.dao.impl;

import by.jwd.task6.dao.AircraftDao;
import by.jwd.task6.dao.DaoException;
import by.jwd.task6.fleet.Aircraft;
import by.jwd.task6.util.ArgumentValidationException;
import by.jwd.task6.util.HashUtil;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SerializationAircraftDao<A extends Aircraft> implements AircraftDao<A> {

    /**
     * Aircraft serialization exception messages.
     */
    protected static final String CANNOT_CREATE_SOURCE_FILE_MESSAGE = "Cannot create new source file.";
    protected static final String CLASS_CAST_EXCEPTION_MESSAGE = "An error occurred from class casting.";
    protected static final String CLASS_NOT_FOUND_MESSAGE = "Class of a serialized object cannot be found.";
    protected static final String INVALID_ID_MESSAGE = "ID cannot be negative.";
    protected static final String IO_EXCEPTION_MESSAGE = "An error occurred while reading source file.";
    protected static final String NON_READABLE_SOURCE_FILE_MESSAGE = "Can not read source file.";
    protected static final String NULL_AIRCRAFT_MESSAGE = "Aircraft cannot be null.";
    protected static final String NULL_SOURCE_FILE_MESSAGE = "Source file cannot be null.";
    protected static final String UPDATING_FAIL_MESSAGE = "File does not contain this object.";

    private final File source;

    public SerializationAircraftDao(File source) throws DaoException, ArgumentValidationException {
        if (source == null) {
            throw new ArgumentValidationException(NULL_SOURCE_FILE_MESSAGE);
        }
        if (!source.exists()) {
            try {
                boolean fileCreatedSuccessfully = source.createNewFile();
                if (!fileCreatedSuccessfully) {
                    throw new DaoException(CANNOT_CREATE_SOURCE_FILE_MESSAGE);
                }
            } catch (IOException e) {
                throw new DaoException(CANNOT_CREATE_SOURCE_FILE_MESSAGE, e);
            }
        }
        if (!source.canRead()) {
            throw new ArgumentValidationException(NON_READABLE_SOURCE_FILE_MESSAGE);
        }
        this.source = source;
    }

    public File getSource() {
        return source;
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(source);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SerializationAircraftDao<?> that = (SerializationAircraftDao<?>) o;
        return source.equals(that.source);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "source=" + source +
               '}';
    }

    @Override
    public Optional<A> findById(int id) throws DaoException, ArgumentValidationException {
        if (id <= 0) {
            throw new ArgumentValidationException(INVALID_ID_MESSAGE);
        }
        List<A> aircrafts = findAll();
        int targetIndex = defineIndexById(id, aircrafts);
        if (targetIndex < 0) {
            return Optional.empty();
        }
        var target = aircrafts.get(targetIndex);
        return Optional.of(target);
    }

    @Override
    public void insert(A aircraft) throws DaoException, ArgumentValidationException {
        if (aircraft == null) {
            throw new ArgumentValidationException(NULL_AIRCRAFT_MESSAGE);
        }
        List<A> aircrafts = findAll();
        aircrafts.add(aircraft);
        updateFile(aircrafts);
    }

    @Override
    public void remove(A aircraft) throws DaoException, ArgumentValidationException {
        if (aircraft == null) {
            throw new ArgumentValidationException(NULL_AIRCRAFT_MESSAGE);
        }
        List<A> aircrafts = findAll();
        boolean removalResult = aircrafts.remove(aircraft);
        if (!removalResult) {
            throw new DaoException(UPDATING_FAIL_MESSAGE);
        }
        updateFile(aircrafts);
    }

    @Override
    public void set(int id, A replacingAircraft) throws DaoException, ArgumentValidationException {
        if (id <= 0) {
            throw new ArgumentValidationException(INVALID_ID_MESSAGE);
        }
        if (replacingAircraft == null) {
            throw new ArgumentValidationException(NULL_AIRCRAFT_MESSAGE);
        }
        List<A> aircrafts = findAll();
        int targetIndex = defineIndexById(id, aircrafts);
        if (targetIndex < 0) {
            throw new DaoException(UPDATING_FAIL_MESSAGE);
        }
        aircrafts.set(targetIndex, replacingAircraft);
        updateFile(aircrafts);
    }

    @Override
    @SuppressWarnings({"unchecked", "InfiniteLoopStatement"})
    public List<A> findAll() throws DaoException {
        List<A> aircrafts = new ArrayList<>();
        try (var inputStream = new ObjectInputStream(new FileInputStream(getSource()))) {
            while (true) {
                var aircraft = (A) inputStream.readObject();
                aircrafts.add(aircraft);
            }
        } catch (EOFException e) {
            return aircrafts;
        } catch (IOException e) {
            throw new DaoException(IO_EXCEPTION_MESSAGE, e);
        } catch (ClassCastException e) {
            throw new DaoException(CLASS_CAST_EXCEPTION_MESSAGE, e);
        } catch (ClassNotFoundException e) {
            throw new DaoException(CLASS_NOT_FOUND_MESSAGE, e);
        }
    }

    protected void updateFile(List<A> aircrafts) throws DaoException {
        Objects.requireNonNull(aircrafts);
        try (var outputStream = new ObjectOutputStream(new FileOutputStream(source))) {
            for (var object : aircrafts) {
                outputStream.writeObject(object);
            }
        } catch (IOException e) {
            throw new DaoException(IO_EXCEPTION_MESSAGE, e);
        }
    }

    protected int defineIndexById(int id, List<A> aircrafts) {
        int targetIndex = -1;
        for (var i = 0; i < aircrafts.size(); i++) {
            var candidate = aircrafts.get(i);
            if (id == candidate.getId()) {
                targetIndex = i;
                break;
            }
        }
        return targetIndex;
    }
}
