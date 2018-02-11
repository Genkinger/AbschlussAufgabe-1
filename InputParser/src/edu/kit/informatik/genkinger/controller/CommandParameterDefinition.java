package edu.kit.informatik.genkinger.controller;

import java.util.ArrayList;
import java.util.List;

public class CommandParameterDefinition {

    private List<CommandParameterType> parameters = new ArrayList<>();
    private String delimiter = ";";
    private boolean hasParameters = false;

    CommandParameterDefinition() {

    }

    CommandParameterDefinition(String delimiter) {
        this.delimiter = delimiter;
    }

    public CommandParameterDefinition addParameter(CommandParameterType type) {
        hasParameters = true;
        parameters.add(type);
        return this;
    }

    public List<CommandParameterType> getParameterList() {
        return parameters;
    }
    public boolean hasParameters(){
        return hasParameters;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
