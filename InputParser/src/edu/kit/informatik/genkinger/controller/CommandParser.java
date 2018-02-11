package edu.kit.informatik.genkinger.controller;

import java.util.List;

public class CommandParser {

    private List<CommandPrototype> prototypes;
    private StringInputInterface inputInterface;

    /**
     * Parses Commands from stdin using the Terminal class
     *
     * @param prototypes list of CommandPrototypes that the parser should search for
     */
    CommandParser(List<CommandPrototype> prototypes, StringInputInterface inputInterface) {
        this.inputInterface = inputInterface;
        this.prototypes = prototypes;
    }

    /**
     * waits for user input and tries to parse it accordingly
     *
     * @return command (can be invalidated)
     */
    public Command parseNext() {
        //TODO: refactor if statement to be more readable

        Command command = new Command("").invalidate("malformed command");

        String line = inputInterface.readLine();
        String[] parts = line.split("\\s+");
        if (parts.length < 1) {
            return command;
        } else {

            CommandPrototype prototype = findPrototypeFor(parts[0]);

            if (prototype != null) {

                command = new Command(parts[0]);

                if (prototype.getLayout().hasParameters()) {

                    List<CommandParameterType> types = prototype.getLayout().getParameterList();

                    String remainder = line.substring(parts[0].length()).trim();
                    String[] remainderParts = remainder.split(prototype.getLayout().getDelimiter());

                    if (remainderParts.length != types.size()) {
                        return new Command(parts[0]).invalidate("Invalid number of parameters for '" + parts[0] + "'");
                    }

                    int index = 0;
                    for (int i = 0; i < types.size() && index < remainderParts.length; i++) {
                        CommandParameterType type = types.get(i);

                        switch (type) {
                            case INT: {
                                try {
                                    int param = Integer.parseInt(remainderParts[index]);
                                    command.addIntegerParameter(param);
                                    index++;
                                } catch (NumberFormatException nfe) {
                                    return new Command(parts[0]).invalidate("parameter " + index + " invalid for '" + parts[0] + "'");
                                }
                            }
                            break;
                            case STRING: {
                                //TODO: how could this fail ?
                                command.addStringParameter(remainderParts[index]);
                                index++;
                            }
                            break;
                            case FLOAT: {
                                try {
                                    float param = Float.parseFloat(remainderParts[index]);
                                    command.addFloatParameter(param);
                                    index++;
                                } catch (NumberFormatException nfe) {
                                    return new Command(parts[0]).invalidate("parameter " + index + " invalid for '" + parts[0] + "'");
                                }
                            }
                            break;
                        }
                    }
                } else {
                    command = new Command(parts[0]);
                }
            } else {
                return new Command(parts[0]).invalidate("command '" + parts[0] + "' not found");
            }
        }

        return command;
    }

    private CommandPrototype findPrototypeFor(String command) {

        for (CommandPrototype p : prototypes) {
            if (p.getCommand().equals(command)) {
                return p;
            }
        }
        return null;
    }

}
