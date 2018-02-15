package edu.kit.informatik.genkinger.controller;

import org.omg.CORBA.DynAnyPackage.Invalid;

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

        Command command;

        String line = inputInterface.readLine();
        String[] parts = line.split("\\s+");
        if (parts.length < 1) {
            return new Command("").invalidate("Malformed command");
        } else {

            CommandPrototype prototype = findPrototypeFor(parts[0]);

            if (prototype != null) {

                command = new Command(parts[0]);

                String remainderWithoutTrim = line.substring(parts[0].length());
                String remainder = remainderWithoutTrim.trim();
                String[] remainderParts = remainder.split(prototype.getLayout().getDelimiter());

                if (prototype.getLayout().hasParameters()) {

                    List<CommandParameterType> types = prototype.getLayout().getParameterList();


                    if (remainderParts.length != types.size() || (StringUtils.countOccurrencesOf(remainder, prototype.getLayout().getDelimiter()) != types.size() - 1)) {
                        return new Command(parts[0]).invalidate("Invalid number of parameters for '" + parts[0] + "'");
                    }

                    int index = 0;
                    for (int i = 0; i < types.size(); i++) {
                        CommandParameterType type = types.get(i);

                        switch (type) {
                            case INT: {
                                try {
                                    int param = Integer.parseInt(remainderParts[index]);
                                    command.addIntegerParameter(param);
                                    index++;
                                } catch (NumberFormatException nfe) {
                                    return new Command(parts[0]).invalidate("Parameter " + index + " invalid for '" + parts[0] + "'");
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
                                    return new Command(parts[0]).invalidate("Parameter " + index + " invalid for '" + parts[0] + "'");
                                }
                            }
                            break;
                        }
                    }
                } else {
                    if (remainderWithoutTrim.length() != 0) {
                        command = new Command(parts[0]).invalidate("Invalid number of parameters for '" + parts[0] + "'");
                    } else {
                        command = new Command(parts[0]);
                    }
                }
            } else {
                return new Command(parts[0]).invalidate("Unknown command '" + parts[0] + "'");
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
