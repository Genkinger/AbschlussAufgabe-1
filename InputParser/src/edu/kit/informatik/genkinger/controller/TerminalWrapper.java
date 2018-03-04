package edu.kit.informatik.genkinger.controller;

import edu.kit.informatik.Terminal;

/**
 * This class is mainly used as a layer of abstraction between my own code and the "Terminal" class which I didn't write.
 * This is done to be less dependent on code that is not mine.
 *
 * @author Lukas Genkinger
 * @see StringInputInterface
 * @see StringOutputInterface
 */

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
