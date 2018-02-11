package edu.kit.informatik.genkinger.controller;

import edu.kit.informatik.genkinger.controller.Command;
import edu.kit.informatik.genkinger.controller.Controller;

public interface Action {
    void execute(Controller controller, Command command);
}
