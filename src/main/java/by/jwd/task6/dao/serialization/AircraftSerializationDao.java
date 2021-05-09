package by.jwd.task6.dao.serialization;

import by.jwd.task6.dao.DaoException;
import by.jwd.task6.fleet.AbstractAircraftModel;
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

public class AircraftSerializationDao<M extends AbstractAircraftModel<?>> extends SerializationDao<Aircraft<M>> {

    /**
     * Aircraft serialization exception messages.
     */
    protected static final String NULL_AIRCRAFT_MESSAGE = "Aircraft cannot be null.";
    protected static final String UPDATING_FAIL_MESSAGE = "File does not contain this aircraft.";

    public AircraftSerializationDao(File source) throws DaoException, IllegalArgumentException {
        super(source);
    }

    @Override
    public Optional<Aircraft<M>> find(int id) throws DaoException, ClassNotFoundException {
        ValidationUtil.validateArgument(id, (n) -> n >= 0, INVALID_ID_MESSAGE);
        List<Aircraft<M>> aircrafts = findAll();
        int targetIndex = defineIndexById(id, aircrafts);
        if (targetIndex < 0) {
            return Optional.empty();
        }
        Aircraft<M> target = aircrafts.get(targetIndex);
        return Optional.of(target);
    }

    public void insert(Aircraft<M> aircraft) throws DaoException, ClassNotFoundException, IllegalArgumentException {
        ValidationUtil.validateArgument(aircraft, Objects::nonNull, NULL_AIRCRAFT_MESSAGE);
        List<Aircraft<M>> aircrafts = findAll();
        aircrafts.add(aircraft);
        updateFile(aircrafts);
    }

    @Override
    public void remove(Aircraft<M> aircraft) throws DaoException, ClassNotFoundException {
        ValidationUtil.validateArgument(aircraft, Objects::nonNull, NULL_AIRCRAFT_MESSAGE);
        List<Aircraft<M>> aircrafts = findAll();
        boolean removalResult = aircrafts.remove(aircraft);
        if (!removalResult) {
            throw new DaoException(UPDATING_FAIL_MESSAGE);
        }
        updateFile(aircrafts);
    }

    @Override
    public void set(int id, Aircraft<M> replacingAircraft) throws DaoException, ClassNotFoundException,
            IllegalArgumentException {
        ValidationUtil.validateArgument(replacingAircraft, Objects::nonNull, NULL_AIRCRAFT_MESSAGE);
        ValidationUtil.validateArgument(id, (n) -> n >= 0, INVALID_ID_MESSAGE);
        List<Aircraft<M>> aircrafts = findAll();
        int targetIndex = defineIndexById(id, aircrafts);
        if (targetIndex < 0) {
            throw new DaoException(UPDATING_FAIL_MESSAGE);
        }
        aircrafts.set(targetIndex, replacingAircraft);
        updateFile(aircrafts);
    }

    @SuppressWarnings({"unchecked", "InfiniteLoopStatement"})
    public List<Aircraft<M>> findAll() throws DaoException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(source))) {
            List<Aircraft<M>> aircrafts = new ArrayList<>();
            try {
                while (true) {
                    Aircraft<M> aircraft = (Aircraft<M>) inputStream.readObject();
                    aircrafts.add(aircraft);
                }
            } catch (EOFException e) {
                // Ok, end of file.
            }
            return aircrafts;
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            throw new DaoException(IO_EXCEPTION_MESSAGE, e);
        } catch (ClassCastException e) {
            throw new DaoException(CLASS_CAST_EXCEPTION_MESSAGE, e);
        }
    }

    private int defineIndexById(int id, List<Aircraft<M>> aircrafts) {
        int targetIndex = -1;
        for (int i = 0; i < aircrafts.size(); i++) {
            Aircraft<M> candidate = aircrafts.get(i);
            if (id == candidate.getId()) {
                targetIndex = i;
                break;
            }
        }
        return targetIndex;
    }
}
