package edu.kit.informatik.genkinger.olympicgames;

import java.util.Comparator;

public class AthleteComparator implements Comparator<Athlete> {
    @Override
    public int compare(Athlete o1, Athlete o2) {
        if (o1.getMedalCount() > o2.getMedalCount()) {
            return 1;
        } else if (o1.getMedalCount() < o2.getMedalCount()) {
            return -1;
        } else {
            if (o1.getId() > o2.getId()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
