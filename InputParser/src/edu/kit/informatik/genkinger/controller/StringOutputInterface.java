package edu.kit.informatik.genkinger.controller;


/**
 * This is used as an abstraction layer between the KIT Terminal class and my Program.
 * Its purpose is to get rid of hardcoded dependencies.
 *
 * @author Lukas Genkinger
 * @see TerminalWrapper
 * @see StringInputInterface
 */
public interface StringOutputInterface {
    /**
     * Prints a string representation of the {@link Object} <code>object</code>
     * ending with a newline.
     *
     * @param object the {@link Object} to be printed
     */
    void printLine(Object object);

    /**
     * Prints the array <code>charArray</code> ending with a newline.
     *
     * @param charArray the <code>char[]</code> to be printed
     */
    void printLine(char[] charArray);

    /**
     * Prints the String <code>message</code>
     * It's intended for errors only.
     *
     * @param message the {@link String} to be printed
     */
    void printError(String message);
}
