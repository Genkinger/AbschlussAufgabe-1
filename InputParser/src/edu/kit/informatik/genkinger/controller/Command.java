package edu.kit.informatik.genkinger.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used in conjunction with a {@link StringInputController}.
 * It contains the parameters that make up a Command (populated by a {@link StringInputController})
 * Currently it supports parameters of type <code>Float</code>, <code>Integer</code> and <code>String</code>
 *
 * @author Lukas Genkinger
 * @see StringInputController
 * @see CommandParameterType
 */
public class Command {
    private String command;

    private List<Float> floats = new ArrayList<>();
    private List<Integer> integers = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    private boolean isValid = true;
    private String reasonForInvalidation;

    /**
     * Constructs a {@link Command} from its String representation.
     *
     * @param command the String representing the command
     */
    Command(String command) {
        this.command = command;
    }

    /**
     * Returns the String representation of the Command.
     *
     * @return the String representing the Command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns the String parameter at <code>index</code>.
     *
     * @param index the index to be fetched
     * @return the String parameter at <code>index</code>
     * @throws IndexOutOfBoundsException if the <code>index</code> doesn't exist
     */
    public String getStringParameter(int index) throws IndexOutOfBoundsException {
        return strings.get(index);
    }

    /**
     * Returns the Integer parameter at <code>index</code>.
     *
     * @param index the index to be fetched
     * @return the Integer parameter at <code>index</code>
     * @throws IndexOutOfBoundsException if the <code>index</code> doesn't exist
     */
    public int getIntegerParameter(int index) throws IndexOutOfBoundsException {
        return integers.get(index);
    }

    /**
     * Returns the Float parameter at <code>index</code>.
     *
     * @param index the index to be fetched
     * @return the Float parameter at <code>index</code>
     * @throws IndexOutOfBoundsException if the <code>index</code> doesn't exist
     */
    public float getFloatParameter(int index) throws IndexOutOfBoundsException {
        return floats.get(index);
    }

    /**
     * Adds the float <code>f</code> to the list of Float parameters.
     *
     * @param f the float to add to the list of parameters
     */
    public void addFloatParameter(float f) {
        floats.add(f);
    }

    /**
     * Adds the Integer <code>i</code> to the list of Integer parameters.
     *
     * @param i the int to add to the list of parameters
     */
    public void addIntegerParameter(int i) {
        integers.add(i);
    }

    /**
     * Adds the String <code>s</code> to the list of String parameters.
     *
     * @param s the String to add to the list of parameters
     */
    public void addStringParameter(String s) {
        strings.add(s);
    }

    /**
     * Invalidates the current command for the specified <code>reason</code>.
     *
     * @param reason the reason for the invalidation
     * @return <code>this</code>
     */
    public Command invalidate(String reason) {
        isValid = false;
        reasonForInvalidation = reason;
        return this;
    }

    /**
     * Returns <code>true</code> if the command is valid.
     *
     * @return <code>true</code> if command is valid. <code>false</code> otherwise.
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * Returns the reason for the invalidation if the command is invalid.
     *
     * @return the reason for the invalidation if the command is invalid. <code>null</code> otherwise
     */
    public String getReasonForInvalidation() {
        return reasonForInvalidation;
    }
}
