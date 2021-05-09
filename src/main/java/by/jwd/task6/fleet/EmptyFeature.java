package by.jwd.task6.fleet;

import java.io.Serializable;

public class EmptyFeature implements Serializable, AircraftModelFeature {

    private static final long serialVersionUID = -7115613759716510945L;

    private static EmptyFeature instance;

    public EmptyFeature() {
    }

    public static synchronized EmptyFeature getInstance() {
        if (instance == null) {
            instance = new EmptyFeature();
        }
        return instance;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    protected Object readResolve() {
        return getInstance();
    }
}
