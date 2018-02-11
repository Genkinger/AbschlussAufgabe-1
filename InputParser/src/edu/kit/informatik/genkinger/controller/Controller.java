package edu.kit.informatik.genkinger.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    private Map<CommandPrototype, Action> actionMap;
    private boolean running;
    private Action defaultCallback;
    private boolean hasDefaultAction;
    private CommandParser parser;
    private boolean trim;

    Controller(boolean trim) {
        this.trim = trim;
        actionMap = new HashMap<>();
    }

    public void attachActionToCommand(CommandPrototype commandPrototype, Action callback) {
        if (actionMap.containsKey(commandPrototype)) {
            return;
        }
        actionMap.put(commandPrototype, callback);
    }

    public void attachDefaultAction(Action callback) {
        hasDefaultAction = true;
        defaultCallback = callback;
    }

    public void start() {
        parser = new CommandParser(getPrototypeList());
        running = true;
        run();
    }

    public void stop() {
        running = false;
    }

    private List<CommandPrototype> getPrototypeList() {
        List<CommandPrototype> list = new ArrayList<>();
        list.addAll(actionMap.keySet());
        return list;
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
