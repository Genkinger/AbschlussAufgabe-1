package edu.kit.informatik.genkinger.controller;

public class StringUtils {

    public static int countOccurrencesOf(String str, String c) {
        return str.length() - str.replace(c, "").length();
    }

}
