package by.jwd.task6.fleet;

import by.jwd.task6.util.HashUtil;
import by.jwd.task6.util.ValidationUtil;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Supplier;

public class Aircraft<M extends AbstractAircraftModel<?>> implements Serializable {

    /**
     * Aircraft own exception messages.
     */
    protected static final String INVALID_ID_MESSAGE = "Aircraft ID cannot be negative";
    protected static final String NULL_AIRCRAFT_MODEL_MESSAGE = "Aircraft model cannot be null";
    protected static final String NULL_AIRCRAFT_MODEL_SUPPLIER_MESSAGE = "Aircraft model supplier cannot be null";
    protected static final String NULL_AIRCRAFT_DOCUMENT_MESSAGE = "Aircraft document cannot be null";

    /**
     * Aircraft document exception messages.
     */
    protected static final String NULL_REGISTRATION_CODE_MESSAGE = "Registration code cannot be null";
    protected static final String NULL_NOTES_MESSAGE = "Notes cannot be null";

    private static final long serialVersionUID = 7814276293529027179L;

    private int id;
    private AircraftDocument aircraftDocument;
    private M model;

    public Aircraft(int id, M model, AircraftDocument aircraftDocument) throws IllegalArgumentException {
        this(id, model);
        ValidationUtil.validateArgument(aircraftDocument, Objects::nonNull, NULL_AIRCRAFT_DOCUMENT_MESSAGE);
        this.aircraftDocument = aircraftDocument;
    }

    public Aircraft(int id, M model) throws IllegalArgumentException {
        this(model);
        ValidationUtil.validateArgument(id, (n) -> n >= 0, INVALID_ID_MESSAGE);
        this.id = id;
    }

    public Aircraft(M model) throws IllegalArgumentException {
        ValidationUtil.validateArgument(model, Objects::nonNull, NULL_AIRCRAFT_MODEL_MESSAGE);
        this.model = model;
        aircraftDocument = new AircraftDocument();
    }

    public Aircraft(int id, Supplier<M> modelSupplier, AircraftDocument aircraftDocument)
            throws IllegalArgumentException {
        this(id, modelSupplier);
        ValidationUtil.validateArgument(aircraftDocument, Objects::nonNull, NULL_AIRCRAFT_DOCUMENT_MESSAGE);
        this.aircraftDocument = aircraftDocument;
    }

    public Aircraft(int id, Supplier<M> modelSupplier) throws IllegalArgumentException {
        this(modelSupplier);
        ValidationUtil.validateArgument(id, (n) -> n >= 0, INVALID_ID_MESSAGE);
        this.id = id;
    }

    public Aircraft(Supplier<M> modelSupplier) throws IllegalArgumentException {
        ValidationUtil.validateArgument(modelSupplier, Objects::nonNull, NULL_AIRCRAFT_MODEL_SUPPLIER_MESSAGE);
        model = modelSupplier.get();
        aircraftDocument = new AircraftDocument();
    }

    public int getId() {
        return id;
    }

    public AircraftDocument getAircraftDocument() {
        return aircraftDocument;
    }

    public void setAircraftDocument(AircraftDocument aircraftDocument) throws IllegalArgumentException {
        ValidationUtil.validateArgument(aircraftDocument, Objects::nonNull, NULL_AIRCRAFT_DOCUMENT_MESSAGE);
        this.aircraftDocument = aircraftDocument;
    }

    public M getModel() {
        return model;
    }

    public void setModel(M model) throws IllegalArgumentException {
        ValidationUtil.validateArgument(model, Objects::nonNull, NULL_AIRCRAFT_MODEL_MESSAGE);
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Aircraft<?> that = (Aircraft<?>) o;
        return id == that.id && aircraftDocument.equals(that.aircraftDocument) && model.equals(that.model);
    }

    @Override
    public int hashCode() {
        return HashUtil.hashFrom(id, aircraftDocument, model);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
               "id=" + id +
               ", aircraftDocument=" + aircraftDocument +
               ", model=" + model +
               '}';
    }
}
