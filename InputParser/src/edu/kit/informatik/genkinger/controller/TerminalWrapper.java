package edu.kit.informatik.genkinger.controller;

import edu.kit.informatik.Terminal;

public class TerminalWrapper implements StringInputInterface, StringOutputInterface {

    @Override
    public String readLine() {
        return Terminal.readLine();
    }

    @Override
    public String[] readFile(String path) {
        return Terminal.readFile(path);
    }

    @Override
    public void printLine(Object object) {
        Terminal.printLine(object);
    }

    @Override
    public void printError(String message) {
        Terminal.printError(message);
    }

    @Override
    public void printLine(char[] charArray) {
        Terminal.printLine(charArray);
    }
}
