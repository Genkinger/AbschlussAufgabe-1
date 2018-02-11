package edu.kit.informatik.genkinger.controller;

import edu.kit.informatik.Terminal;

public class TerminalWrapper implements StringInputInterface {

    @Override
    public String readLine() {
        return Terminal.readLine();
    }

    @Override
    public String[] readFile(String path) {
        return Terminal.readFile(path);
    }
}
