package edu.kit.informatik.genkinger.olympicgames;

import java.util.Comparator;

public class VenueComparator implements Comparator<Venue> {
    @Override
    public int compare(Venue o1, Venue o2) {
        if (o1.getSpectatorCount() > o2.getSpectatorCount()) {
            return -1;
        } else if (o1.getSpectatorCount() < o2.getSpectatorCount()) {
            return 1;
        } else {
            if (o1.getId() < o2.getId()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
