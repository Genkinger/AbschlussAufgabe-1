package edu.kit.informatik.genkinger.controller;


/**
 * This class is used in conjunction with a {@link CommandLayout}.
 * It's used to define "prototypes" of commands which a {@link CommandParser} uses to match inputs to {@link Command}s.
 * It does not contain the actual contents of a command (i.e. the values of the parameters) just information about
 * its {@link CommandLayout}.
 *
 * @author Lukas Genkinger
 * @see Command
 * @see CommandLayout
 * @see CommandParser
 */
public class CommandPrototype {
    private String command;
    private CommandLayout layout;

    /**
     * Constructs a {@link CommandPrototype} from the commands string representation and its {@link CommandLayout}.
     *
     * @param command the string representation of the command.
     * @param layout  the layout of the command.
     */
    public CommandPrototype(String command, CommandLayout layout) {
        this.command = command;
        this.layout = layout;
    }

    /**
     * Returns the layout of the prototype.
     *
     * @return the layout of the prototype
     */
    public CommandLayout getLayout() {
        return layout;
    }

    /**
     * Returns the string representation of the command.
     *
     * @return the string representation of the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns <code>true</code> if the String representations of the two {@link CommandPrototype}s are the same.
     *
     * @param o the other {@link CommandPrototype} to compare to
     * @return <code>true</code> if the string representations of the two {@link CommandPrototype}s are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof CommandPrototype) {
            CommandPrototype cp = (CommandPrototype) o;
            return cp.getCommand().equals(this.command);
        }
        return false;
    }

}
