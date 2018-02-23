package edu.kit.informatik.genkinger.controller;

/**
 * Represents an Action that is triggered by a {@link StringInputController}.
 *
 * @author Lukas Genkinger
 */
public interface Action {
    /**
     * Can be called by a {@link StringInputController}.
     *
     * @param stringInputController the reference to the {@link StringInputController} that triggered the Action
     * @param command               the {@link Command} passed in via the {@link StringInputController}
     */
    void execute(StringInputController stringInputController, Command command);
}
