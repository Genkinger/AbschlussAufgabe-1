package edu.kit.informatik.genkinger.controller;

public class Main {
    public static void main(String[] args) {

        TerminalWrapper tw = new TerminalWrapper();
        CommandPrototype quit = new CommandPrototype("quit", new CommandLayout());

        StringInputController controller = new StringInputController(tw);
        controller.attachActionToCommand(quit, (c, cmd) -> c.stop());
        controller.attachDefaultAction((c, cmd) -> tw.printError(cmd.getReasonForInvalidation()));
        controller.start();
    }

}
