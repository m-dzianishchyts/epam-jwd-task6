package by.jwd.task6.dao.serialization;

import by.jwd.task6.dao.DaoException;
import by.jwd.task6.dao.DataAccessObject;
import by.jwd.task6.util.HashUtil;
import by.jwd.task6.util.ValidationUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Objects;

public abstract class SerializationDao<T> implements DataAccessObject<T> {

    /**
     * Serialization exception messages.
     */
    protected static final String CANNOT_CREATE_SOURCE_FILE_MESSAGE = "Cannot create new source file.";
    protected static final String CLASS_CAST_EXCEPTION_MESSAGE = "An error occurred from class casting.";
    protected static final String INVALID_ID_MESSAGE = "ID cannot be negative.";
    protected static final String IO_EXCEPTION_MESSAGE = "An error occurred while reading source file.";
    protected static final String NON_READABLE_SOURCE_FILE_MESSAGE = "Can not read source file.";
    protected static final String NULL_SOURCE_FILE_MESSAGE = "Source file cannot be null.";
    protected static final String SOURCE_FILE_NOT_FOUND_MESSAGE = "Source file does not exist.";

    protected final File source;

    public SerializationDao(File source) throws DaoException, IllegalArgumentException {
        ValidationUtil.validateArgument(source, Objects::nonNull, NULL_SOURCE_FILE_MESSAGE);
        if (!source.exists()) {
            try {
                source.createNewFile();
            } catch (IOException e) {
                throw new DaoException(CANNOT_CREATE_SOURCE_FILE_MESSAGE, e);
            }
        }
        ValidationUtil.validateArgument(source, File::canRead, NON_READABLE_SOURCE_FILE_MESSAGE);
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
        SerializationDao<?> that = (SerializationDao<?>) o;
        return source.equals(that.source);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "source=" + source +
               '}';
    }

    protected void updateFile(List<T> objects) throws DaoException {
        Objects.requireNonNull(objects);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(source))) {
            for (T object : objects) {
                outputStream.writeObject(object);
            }
        } catch (IOException e) {
            throw new DaoException(IO_EXCEPTION_MESSAGE, e);
        }
    }
}
