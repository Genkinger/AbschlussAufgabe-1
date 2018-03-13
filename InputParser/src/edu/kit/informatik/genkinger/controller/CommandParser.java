package edu.kit.informatik.genkinger.controller;


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
class CommandParser {

    private List<CommandPrototype> prototypes;
    private StringInputInterface inputInterface;

    private String currentLineFull;
    private String currentLineRemainder;
    private String currentCommandString;
    private int currentParameterIndex;
    private ErrorCode errorCode = ErrorCode.OK;
    private Command currentCommand;


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
    Command next() {
        currentLineFull = inputInterface.readLine();

        if (!firstStage()) {
            switch (errorCode) {
                case UNKNOWN_COMMAND:
                    return new Command(currentCommandString)
                            .invalidate("Unknown command '" + currentCommandString + "'");
                case MALFORMED_COMMAND:
                    return new Command(currentCommandString)
                            .invalidate("Malformed command");
                case INVALID_PARAMETER:
                    return new Command(currentCommandString)
                            .invalidate("Invalid parameter at index (" + currentParameterIndex
                                    + ") for command '" + currentCommandString + "'");
                case INVALID_PARAMETER_COUNT:
                    return new Command(currentCommandString)
                            .invalidate("Invalid number of parameters for command '" + currentCommandString + "'");
                case EXTRANEOUS_WHITESPACE:
                    return new Command(currentCommandString)
                            .invalidate("Extraneous whitespace in or around command");
                default:
                    break;
            }
        }

        return currentCommand;
    }

    private boolean firstStage() {

        if (!validateWhitespaceRules(currentLineFull)) {
            errorCode = ErrorCode.EXTRANEOUS_WHITESPACE;
            return false;
        }

        String[] parts = currentLineFull.split("\\s+");

        if (parts.length == 0) {
            errorCode = ErrorCode.MALFORMED_COMMAND;
            return false;
        }

        currentCommandString = parts[0];
        CommandPrototype prototype = findPrototypeFor(currentCommandString);

        if (prototype == null) {
            errorCode = ErrorCode.UNKNOWN_COMMAND;
            return false;
        }

        currentCommand = new Command(currentCommandString);
        currentLineRemainder = currentLineFull.substring(currentCommandString.length());

        if (!secondStage(prototype)) {
            return false;
        }

        return true;
    }

    private boolean secondStage(CommandPrototype prototype) {

        String[] parts = currentLineRemainder.trim().split(prototype.getLayout().getDelimiter());


        if (prototype.getLayout().hasParameters()) {

            if (parts.length != prototype.getLayout().getParameterList().size()) {
                errorCode = ErrorCode.INVALID_PARAMETER_COUNT;
                return false;
            }

            List<CommandParameterType> typeList = prototype.getLayout().getParameterList();
            currentParameterIndex = 0;
            for (CommandParameterType type : typeList) {
                switch (type) {
                    case INT:
                        try {
                            int parameter = Integer.parseInt(parts[currentParameterIndex]);
                            currentCommand.addIntegerParameter(parameter);
                            currentParameterIndex++;
                        } catch (NumberFormatException nfe) {
                            errorCode = ErrorCode.INVALID_PARAMETER;
                            return false;
                        }
                        break;
                    case FLOAT:
                        try {
                            float parameter = Float.parseFloat(parts[currentParameterIndex]);
                            currentCommand.addFloatParameter(parameter);
                            currentParameterIndex++;
                        } catch (NumberFormatException nfe) {
                            errorCode = ErrorCode.INVALID_PARAMETER;
                            return false;
                        }
                        break;
                    case STRING:
                        currentCommand.addStringParameter(parts[currentParameterIndex]);
                        currentParameterIndex++;
                        break;
                    default:
                        break;
                }
            }
        }

        return true;
    }

    private boolean validateWhitespaceRules(String str) {
        /*
          * Commands shouldn't:
          * 1. start with whitespace
          * 2. have more than one whitespace separating the command name from the parameter list
          * 3. have extraneous whitespace at the end of the parameter list
         */

        /* 1. */
        if (str.matches("^\\s+.*")) {
            return false;
        }

        /* 2. */
        String parts[] = str.split("\\s+");
        if (parts.length > 1) {
            String rem = str.substring(parts[0].length() + 1);
            if (rem.matches("^\\s+.*")) {
                return false;
            }
        }

        /* 3. */
        if (str.matches(".*\\s+$")) {
            return false;
        }


        return true;
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
