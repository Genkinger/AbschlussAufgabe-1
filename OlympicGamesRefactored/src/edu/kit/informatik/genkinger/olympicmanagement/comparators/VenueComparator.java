package edu.kit.informatik.genkinger.olympicmanagement.comparators;


import edu.kit.informatik.genkinger.olympicmanagement.Venue;

import java.util.Comparator;

/**
 * Comparator for {@link Venue}s.
 *
 * @author Lukas Genkinger
 */
public class VenueComparator implements Comparator<Venue> {
    @Override
    public int compare(Venue o1, Venue o2) {
        if (o1.getSpectatorCount() > o2.getSpectatorCount()) {
            return -1;
        }
        if (o1.getSpectatorCount() < o2.getSpectatorCount()) {
            return 1;
        }

        if (Integer.parseInt(o1.getId()) > Integer.parseInt(o2.getId())) {
            return 1;
        } else {
            return -1;
        }

    }
}
