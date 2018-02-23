package edu.kit.informatik.genkinger;

/**
 * This class is intended as a "namespace" for utility methods.
 *
 * @author Lukas Genkinger
 */
public class Utils {

    /**
     * Returns <code>true</code> if <code>min <= value <= max</code>
     *
     * @param value the value to check
     * @param min   the minimum
     * @param max   the maximum
     * @return <code>true</code> if <code>min <= value <= max</code>.
     * <code>false</code> otherwise.
     */
    static boolean inRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
}
