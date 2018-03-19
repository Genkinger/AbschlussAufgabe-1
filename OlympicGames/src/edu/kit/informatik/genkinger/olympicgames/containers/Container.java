package edu.kit.informatik.genkinger.olympicgames.containers;

public abstract class Container {
    private String errorString = null;

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}
