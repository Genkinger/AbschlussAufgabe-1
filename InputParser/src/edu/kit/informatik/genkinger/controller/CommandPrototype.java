package edu.kit.informatik.genkinger.controller;

public class CommandPrototype {
    private String command;
    private CommandLayout layout;

    /**
     * Constructs a CommandPrototype
     *
     * @param command the actual command
     * @param layout  the layout of the command
     */
    public CommandPrototype(String command, CommandLayout layout) {
        this.command = command;
        this.layout = layout;
    }

    /**
     * @return layout
     */
    public CommandLayout getLayout() {
        return layout;
    }

    /**
     * @return command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param o other CommandPrototype object to test equality
     * @return true if this.command = o.command
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
