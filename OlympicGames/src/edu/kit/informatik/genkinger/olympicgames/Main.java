package edu.kit.informatik.genkinger.olympicgames;

import edu.kit.informatik.genkinger.controller.TerminalWrapper;

public class Main {
    public static void main(String[] args) {
        TerminalWrapper w = new TerminalWrapper();
        OlympicGames g = new OlympicGames(w, w);
        g.start();
    }
}
