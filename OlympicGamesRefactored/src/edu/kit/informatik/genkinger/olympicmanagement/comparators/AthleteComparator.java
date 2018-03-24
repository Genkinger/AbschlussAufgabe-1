package edu.kit.informatik.genkinger.olympicmanagement.comparators;

import edu.kit.informatik.genkinger.olympicmanagement.Athlete;

import java.util.Comparator;

/**
 * Comparator for {@link Athlete}s.
 *
 * @author Lukas Genkinger
 */
public class AthleteComparator implements Comparator<Athlete> {
    @Override
    public int compare(Athlete o1, Athlete o2) {
        if (o1.getMedalCount() < o2.getMedalCount()) {
            return 1;
        } else if (o1.getMedalCount() > o2.getMedalCount()) {
            return -1;
        } else {
            if (Integer.parseInt(o1.getId()) > Integer.parseInt(o2.getId())) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
