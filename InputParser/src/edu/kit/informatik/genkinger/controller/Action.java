package edu.kit.informatik.genkinger.controller;

public interface Action {
    /**
     * used as the entry-point
     *
     * @param controller reference to a Controller
     * @param command    command
     */
    void execute(Controller controller, Command command);
}
