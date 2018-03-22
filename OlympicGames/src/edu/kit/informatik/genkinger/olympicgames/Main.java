package edu.kit.informatik.genkinger.olympicgames;

import edu.kit.informatik.genkinger.controller.TerminalWrapper;

public class Main {
    /**
     *
     * @param args .
     */
    public static void main(String[] args) {
        TerminalWrapper wrapper = new TerminalWrapper();
        OlympicGames games = new OlympicGames(wrapper, wrapper);
        games.start();
    }
}
