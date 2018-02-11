package edu.kit.informatik.genkinger.controller;

public class CommandPrototype {
    private String command;
    private CommandParameterDefinition definition;

    CommandPrototype(String command, CommandParameterDefinition definition) {
        this.command = command;
        this.definition = definition;
    }

    public CommandParameterDefinition getParameterDefinition() {
        return definition;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public boolean equals(Object o) {
        CommandPrototype cp = (CommandPrototype) o;
        return cp.getCommand().equals(this.command);
    }
}
