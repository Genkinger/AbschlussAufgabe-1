package edu.kit.informatik.genkinger;

import edu.kit.informatik.genkinger.connectsix.PlayerMark;

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
    public static boolean inRange(int value, int min, int max) {
        return value >= min && value <= max;
    }


    /**
     * Returns a new <code>PlayerMark[]</code> representing the concatenation of <code>first</code> with <code>second</code>
     *
     * @param first  the first array in the concatenation
     * @param second the second array in the concatenation
     * @return a new <code>PlayerMark[]</code> representing the concatenation
     * of <code>first</code> with <code>second</code>.
     * <code>null</code> if one or both of the input arrays are <code>null</code>
     */
    public static PlayerMark[] concatenateArrays(PlayerMark[] first, PlayerMark[] second) {

        if (first == null || second == null) {
            return null;
        }

        PlayerMark[] retval = new PlayerMark[first.length + second.length];

        for (int i = 0; i < retval.length; i++) {
            if (i < first.length) {
                retval[i] = first[i];
            } else {
                retval[i] = second[i - first.length];
            }
        }

        return retval;
    }

}
