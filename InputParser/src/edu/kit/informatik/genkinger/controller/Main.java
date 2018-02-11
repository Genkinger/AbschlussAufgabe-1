package edu.kit.informatik.genkinger.controller;

import edu.kit.informatik.Terminal;

public class Main {
    public static void main(String[] args) {


        CommandPrototype quitCommand = new CommandPrototype("quit",
                new CommandParameterDefinition());
        CommandPrototype printCommand = new CommandPrototype("print",
                new CommandParameterDefinition().
                        addParameter(CommandParameterType.STRING).
                        addParameter(CommandParameterType.INT));

        Controller controller = new Controller(true);
        controller.attachDefaultAction((c, cmd) -> Terminal.printError(cmd.getReasonForInvalidation()));
        controller.attachActionToCommand(quitCommand, (c, cmd) -> {
            Terminal.printLine("Exiting...");
            c.stop();
        });
        controller.attachActionToCommand(printCommand, (c, cmd) -> {
            try {
                Terminal.printLine("STRING: " + cmd.getStringParameter(0));
                Terminal.printLine("INT: " + cmd.getIntegerParameter(0));
            } catch (IndexOutOfBoundsException e) {
                Terminal.printError("Index out of bounds...");
            }
        });
        controller.start();
    }

}
