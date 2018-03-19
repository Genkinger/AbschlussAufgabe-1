package edu.kit.informatik.genkinger.olympicgames;

import edu.kit.informatik.genkinger.controller.TerminalWrapper;

public class Main {
    public static void main(String[] args) {
        TerminalWrapper w = new TerminalWrapper();
        OlympicGames g = new OlympicGames(w, w);
        g.adminContainer.addAdmin("Max", "Mustermann", "abcd", "12345678");
        g.adminContainer.loginAdmin("abcd", "12345678");
        g.iocContainer.addIocCode(111, "arg", "argentinia", 1920);
        g.iocContainer.addIocCode(112, "ger", "germany", 1921);
        g.iocContainer.addIocCode(113, "eng", "england", 1923);
        g.iocContainer.addIocCode(114, "chi", "china", 1920);
        g.start();
    }
}
