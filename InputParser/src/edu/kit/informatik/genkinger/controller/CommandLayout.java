package edu.kit.informatik.genkinger.controller;

import java.util.ArrayList;
import java.util.List;

public class CommandLayout {

    private List<CommandParameterType> parameters = new ArrayList<>();
    private String delimiter = ";";
    private boolean hasParameters = false;

    /**
     * Constructs a CommandLayout with the default delimiter (";")
     */
    public CommandLayout() {

    }

    /**
     * Constructs a CommandLayout with a given delimiter
     *
     * @param delimiter separates parameters
     */
    public CommandLayout(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Adds a type to the layout
     *
     * @param type added to the layout
     * @return this
     */
    public CommandLayout addParameter(CommandParameterType type) {
        hasParameters = true;
        parameters.add(type);
        return this;
    }

    /**
     * Returns a list of ParameterTypes in the order in which they were specified
     *
     * @return list of ParameterTypes
     */
    public List<CommandParameterType> getParameterList() {
        return parameters;
    }

    /**
     * @return true if the command has parameters
     */
    public boolean hasParameters() {
        return hasParameters;
    }

    /**
     * @return delimiter that was specified
     */
    public String getDelimiter() {
        return delimiter;
    }
}
