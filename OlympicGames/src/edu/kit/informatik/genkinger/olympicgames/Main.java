package edu.kit.informatik.genkinger.olympicgames;

import edu.kit.informatik.genkinger.controller.TerminalWrapper;

public class Main {
    public static void main(String[] args) {
        TerminalWrapper w = new TerminalWrapper();
        OlympicGames g = new OlympicGames(w, w);
        g.adminContainer.addAdmin("Max", "Mustermann", "abcd", "12345678");
        g.adminContainer.loginAdmin("abcd", "12345678");
        g.iocContainer.addIocCode("111", "ger", "germany", "1900");
        g.iocContainer.addIocCode("112", "arg", "argentinia", "1920");
        g.iocContainer.addIocCode("113", "chi", "chile", "1930");
        g.iocContainer.addIocCode("114", "nig", "nigeria", "1910");
        g.iocContainer.addIocCode("115", "eng", "england", "1900");

        g.sportContainer.addSport("bobsport", "bob");
        g.sportContainer.addSport("bobsport", "skeleton");
        g.sportContainer.addSport("hockey", "hockey");

        g.athleteContainer.addAthlete("0001", "Max", "Mustermann", "germany", "bobsport", "skeleton");
        g.athleteContainer.addAthlete("0002", "Fritz", "Mustermann", "germany", "bobsport", "skeleton");
        g.athleteContainer.addAthlete("0003", "Max", "Musterfrau", "germany", "bobsport", "skeleton");
        g.athleteContainer.addAthlete("0004", "Max", "Mustermann", "chile", "bobsport", "skeleton");
        g.start();
    }
}
