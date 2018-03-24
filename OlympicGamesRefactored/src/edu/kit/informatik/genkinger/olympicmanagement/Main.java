package edu.kit.informatik.genkinger.olympicmanagement;

import edu.kit.informatik.genkinger.controller.TerminalWrapper;

public class Main {
    /**
     * Entry point
     *
     * @param args the commandline args
     */
    public static void main(String[] args) {
        TerminalWrapper wrapper = new TerminalWrapper();
        OlympicManagement games = new OlympicManagement(wrapper, wrapper);
        games.start();
    }
}
