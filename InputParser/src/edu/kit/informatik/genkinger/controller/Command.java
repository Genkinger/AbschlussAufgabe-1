package edu.kit.informatik.genkinger.controller;

import java.util.ArrayList;
import java.util.List;

public class Command {
    private String command;

    private List<Float> floats = new ArrayList<>();
    private List<Integer> integers = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    private boolean isValid = true;
    private String reasonForInvalidation;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }


    public String getStringParameter(int index) throws IndexOutOfBoundsException {
        return strings.get(index);
    }

    public int getIntegerParameter(int index) throws  IndexOutOfBoundsException {
        return integers.get(index);
    }

    public float getFloatParameter(int index) throws IndexOutOfBoundsException {
        return floats.get(index);
    }

    public void addFloatParameter(float f) {
        floats.add(f);
    }

    public void addIntegerParameter(int i) {
        integers.add(i);
    }

    public void addStringParameter(String s) {
        strings.add(s);
    }

    public Command invalidate(String reason) {
        isValid = false;
        reasonForInvalidation = reason;
        return this;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getReasonForInvalidation() {
        return reasonForInvalidation;
    }
}
