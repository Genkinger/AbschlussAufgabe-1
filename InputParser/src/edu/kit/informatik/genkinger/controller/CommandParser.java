package edu.kit.informatik.genkinger.controller;

import edu.kit.informatik.StringUtils;

import java.util.List;

/**
 * This class is used to parse commands that are read in via a {@link StringInputInterface}.
 * It uses {@link CommandPrototype}s as a means of matching inputs.
 *
 * @author Lukas Genkinger
 * @see StringInputController
 * @see StringInputInterface
 * @see CommandPrototype
 * @see Command
 */
public class CommandParser {

    private List<CommandPrototype> prototypes;
    private StringInputInterface inputInterface;

    /**
     * Constructs a {@link CommandParser} from a list of CommandPrototypes that are used to match inputs against.
     * It uses the specified {@link StringInputInterface} for getting input.
     *
     * @param prototypes     list of {@link CommandPrototype}s the parser should search for
     * @param inputInterface the {@link StringInputInterface} to use for reading in input
     */
    CommandParser(List<CommandPrototype> prototypes, StringInputInterface inputInterface) {
        this.inputInterface = inputInterface;
        this.prototypes = prototypes;
    }

    /**
     * Parses inputs that are read in via the {@link StringInputInterface}.
     * This method blocks until input is available.
     *
     * @return a {@link Command} if the read input is a valid command.
     * The {@link Command} is invalidated with {@link Command#invalidate(String)} otherwise
     */
    public Command nextCommand() {
        //TODO: split this into multiple mehtod calls

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

                    if (remainderParts.length != types.size()
                            || (StringUtils.countOccurrencesOf(remainder,
                            prototype.getLayout().getDelimiter()) != types.size() - 1)) {
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
                                    return new Command(parts[0])
                                            .invalidate("Parameter at " + index + " is invalid for '" + parts[0] + "'");
                                }
                            }
                            break;
                            case STRING: {
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
                                    return new Command(parts[0])
                                            .invalidate("Parameter at " + index + " is invalid for '" + parts[0] + "'");
                                }
                            }
                            break;
                            default:
                                return new Command(parts[0])
                                        .invalidate("Invalid parameter type in command '" + parts[0] + "'");
                        }
                    }
                } else {
                    if (remainderWithoutTrim.length() != 0) {
                        command = new Command(parts[0])
                                .invalidate("Invalid number of parameters for '" + parts[0] + "'");
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
