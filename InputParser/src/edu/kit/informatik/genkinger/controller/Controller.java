package edu.kit.informatik.genkinger.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    private Map<CommandPrototype, Action> actionMap = new HashMap<>();
    private boolean running;
    private Action defaultCallback;
    private boolean hasDefaultAction;
    private CommandParser parser;
    private StringInputInterface inputInterface;

    Controller(StringInputInterface inputInterface) {
        this.inputInterface = inputInterface;
    }

    /**
     * Attaches an Action to a CommandPrototype
     *
     * @param commandPrototype will be attached to Action
     * @param callback         Action to be attached
     */
    public void attachActionToCommand(CommandPrototype commandPrototype, Action callback) {
        if (actionMap.containsKey(commandPrototype)) {
            return;
        }
        actionMap.put(commandPrototype, callback);
    }

    /**
     * Attaches a default Action that gets called if no other Actions can be matched while running
     *
     * @param callback Action to be attached
     */
    public void attachDefaultAction(Action callback) {
        hasDefaultAction = true;
        defaultCallback = callback;
    }

    /**
     * Initializes a CommandParser from specified Action-Command mappings and starts the Controller
     */
    public void start() {
        parser = new CommandParser(new ArrayList<>(actionMap.keySet()), inputInterface);
        running = true;
        run();
    }

    /**
     * stops the Controller
     */
    public void stop() {
        running = false;
    }

    private void run() {
        while (running) {

            Command command = parser.parseNext();
            if (!command.isValid()) {
                if (hasDefaultAction) {
                    defaultCallback.execute(this, command);
                }
                continue;
            }

            for (Map.Entry<CommandPrototype, Action> entry : actionMap.entrySet()) {
                if (command.getCommand().equals(entry.getKey().getCommand())) {
                    entry.getValue().execute(this, command);
                }
            }


        }
    }

}
