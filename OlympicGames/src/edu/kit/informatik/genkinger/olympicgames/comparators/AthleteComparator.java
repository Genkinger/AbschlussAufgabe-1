package edu.kit.informatik.genkinger.olympicgames.comparators;

import edu.kit.informatik.genkinger.olympicgames.Athlete;

import java.util.Comparator;

public class AthleteComparator implements Comparator<Athlete> {
    @Override
    public int compare(Athlete o1, Athlete o2) {
        if (o1.getMedalCount() > o2.getMedalCount()) {
            return 1;
        } else if (o1.getMedalCount() < o2.getMedalCount()) {
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
