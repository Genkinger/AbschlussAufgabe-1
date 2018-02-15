package edu.kit.informatik.genkinger.controller;

public interface Action {
    /**
     * used as the entry-point
     *
     * @param stringInputController reference to a StringInputController
     * @param command    command
     */
    void execute(StringInputController stringInputController, Command command);
}
