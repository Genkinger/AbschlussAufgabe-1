package edu.kit.informatik.genkinger.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is used in conjunction with a {@link CommandPrototype} and a {@link StringInputController}.
 * It represents the "layout" of a command. (i.e. the sequence of {@link CommandParameterType}s expected)
 *
 * @author Lukas Genkinger
 * @see CommandPrototype
 * @see StringInputController
 */
public class CommandLayout {

    private List<CommandParameterType> parameters = new ArrayList<>();
    private String delimiter = ";";
    private boolean hasParameters = false;

    /**
     * Constructs a {@link CommandLayout} with the default delimiter (<code>";"</code>).
     */
    public CommandLayout() {

    }

    /**
     * Constructs a {@link CommandLayout} with the given delimiter.
     *
     * @param delimiter the delimiter to separate parameters.
     */
    public CommandLayout(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Adds a {@link CommandParameterType} to the layout
     *
     * @param type the {@link CommandParameterType} to add to the layout
     * @return this
     */
    public CommandLayout addParameter(CommandParameterType type) {
        hasParameters = true;
        parameters.add(type);
        return this;
    }

    /**
     * Returns a list of {@link CommandParameterType}s
     * in the order in which they were added with {@link #addParameter(CommandParameterType)}.
     *
     * @return the list of {@link CommandParameterType}s in the layout
     */
    public List<CommandParameterType> getParameterList() {
        return parameters;
    }

    /**
     * Returns <code>true</code> if any (i.e. more than 0) parameters were added
     * with {@link #addParameter(CommandParameterType)}
     *
     * @return <code>true</code> if any parameters were added
     */
    public boolean hasParameters() {
        return hasParameters;
    }

    /**
     * Returns the delimiter with which the parameters are separated.
     *
     * @return the specified delimiter
     */
    public String getDelimiter() {
        return delimiter;
    }
}
