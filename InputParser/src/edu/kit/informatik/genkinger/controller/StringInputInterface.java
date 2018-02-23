package edu.kit.informatik.genkinger.controller;

/**
 * This is used as an abstraction layer between the KIT Terminal class and my Program.
 * Its purpose is to get rid of hardcoded dependencies.
 *
 * @author Lukas Genkinger
 * @see TerminalWrapper
 * @see StringOutputInterface
 */
public interface StringInputInterface {

    /**
     * Returns a {@link String} read in mainly from (but not limited to) stdin.
     *
     * @return the {@link String} read in
     */
    String readLine();

    /**
     * Returns an array of {@link String}s representing the contents of the specified file at <code>path</code>.
     *
     * @param path the path to the file
     * @return an array of {@link String}s representing the contents of the specified file at <code>path</code>.
     */
    String[] readFile(String path);
}
