package edu.kit.informatik.genkinger.olympicgames.containers;

/**
 *
 */
public abstract class Container {
    private String errorString = null;

    /**
     *
     * @return .
     */
    public String getErrorString() {
        return errorString;
    }

    /**
     *
     * @param errorString .
     */
    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}
