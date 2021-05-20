package by.jwd.task6.dao.serialization;

import by.jwd.task6.dao.DaoException;
import by.jwd.task6.fleet.Aircraft;
import by.jwd.task6.util.ValidationUtil;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AircraftSerializationDao<A extends Aircraft> extends SerializationDao<A> {

    /**
     * Aircraft serialization exception messages.
     */
    protected static final String NULL_AIRCRAFT_MESSAGE = "Aircraft cannot be null.";

    public AircraftSerializationDao(File source) throws DaoException, IllegalArgumentException {
        super(source);
    }

    @Override
    public Optional<A> find(int id) throws DaoException {
        ValidationUtil.validateArgument(id, n -> n >= 0, INVALID_ID_MESSAGE);
        List<A> aircrafts = findAll();
        int targetIndex = defineIndexById(id, aircrafts);
        if (targetIndex < 0) {
            return Optional.empty();
        }
        var target = aircrafts.get(targetIndex);
        return Optional.of(target);
    }

    public void insert(A aircraft) throws DaoException, IllegalArgumentException {
        ValidationUtil.validateArgument(aircraft, Objects::nonNull, NULL_AIRCRAFT_MESSAGE);
        List<A> aircrafts = findAll();
        aircrafts.add(aircraft);
        updateFile(aircrafts);
    }

    @Override
    public void remove(A aircraft) throws DaoException {
        ValidationUtil.validateArgument(aircraft, Objects::nonNull, NULL_AIRCRAFT_MESSAGE);
        List<A> aircrafts = findAll();
        boolean removalResult = aircrafts.remove(aircraft);
        if (!removalResult) {
            throw new DaoException(UPDATING_FAIL_MESSAGE);
        }
        updateFile(aircrafts);
    }

    @Override
    public void set(int id, A replacingAircraft) throws DaoException, IllegalArgumentException {
        ValidationUtil.validateArgument(replacingAircraft, Objects::nonNull, NULL_AIRCRAFT_MESSAGE);
        ValidationUtil.validateArgument(id, n -> n >= 0, INVALID_ID_MESSAGE);
        List<A> aircrafts = findAll();
        int targetIndex = defineIndexById(id, aircrafts);
        if (targetIndex < 0) {
            throw new DaoException(UPDATING_FAIL_MESSAGE);
        }
        aircrafts.set(targetIndex, replacingAircraft);
        updateFile(aircrafts);
    }

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

    private int defineIndexById(int id, List<A> aircrafts) {
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
