package edu.kit.informatik.genkinger.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to trigger {@link Action}s that are mapped to inputs
 * received via a {@link CommandParser}.
 *
 * @author Lukas Genkinger
 * @see Action
 * @see CommandParser
 */
public class StringInputController {

    private Map<CommandPrototype, Action> actionMap = new HashMap<>();
    private boolean running;
    private Action defaultCallback;
    private boolean hasDefaultAction;
    private CommandParser parser;
    private StringInputInterface inputInterface;

    /**
     * Constructs a {@link StringInputController} with a {@link StringInputInterface}
     * that is used to create an internal {@link CommandParser}.
     *
     * @param inputInterface the {@link StringInputInterface} to use
     */
    public StringInputController(StringInputInterface inputInterface) {
        this.inputInterface = inputInterface;
    }

    /**
     * Attaches an {@link Action} to a {@link CommandPrototype}.
     * This pair will be added to the map of "registered" prototypes.
     *
     * @param commandPrototype the {@link CommandPrototype} the {@link Action} <code>callback</code> will be attached to
     * @param callback         the {@link Action} that will be attached to the
     *                         {@link CommandPrototype} <code>commandPrototype</code>
     */
    public void attachActionToCommand(CommandPrototype commandPrototype, Action callback) {
        if (actionMap.containsKey(commandPrototype)) {
            return;
        }
        actionMap.put(commandPrototype, callback);
    }

    /**
     * Attaches a default {@link Action} that gets called if no other Actions can be matched while running.
     *
     * @param callback the {@link Action} to be attached
     */
    public void attachDefaultAction(Action callback) {
        hasDefaultAction = true;
        defaultCallback = callback;
    }

    /**
     * Creates a {@link CommandParser} from the previously specified Action-CommandPrototype mappings
     * and starts the {@link StringInputController}.
     * This method calls blocking methods.
     *
     * @see #attachActionToCommand(CommandPrototype, Action)
     * @see #attachDefaultAction(Action)
     */
    public void start() {
        parser = new CommandParser(new ArrayList<>(actionMap.keySet()), inputInterface);
        running = true;
        run();
    }

    /**
     * Stops the {@link StringInputController}
     */
    public void stop() {
        running = false;
    }

    private void run() {
        while (running) {

            Command command = parser.nextCommand();
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
