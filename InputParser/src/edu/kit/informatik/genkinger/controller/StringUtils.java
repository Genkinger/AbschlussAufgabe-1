package edu.kit.informatik.genkinger.controller;

/**
 * This class is used as a "namespace" for static methods involving Strings.
 *
 * @author Lukas Genkinger
 */
public class StringUtils {

    /**
     * Returns the number of occurrences of the {@link String} <code>c</code> in the {@link String} <code>str</code>.
     *
     * @param str the {@link String} to be searched
     * @param c   the {@link String} to count
     * @return the number of occurrences of <code>c</code> in <code>str</code>
     */
    public static int countOccurrencesOf(String str, String c) {
        return str.length() - str.replace(c, "").length();
    }

}
