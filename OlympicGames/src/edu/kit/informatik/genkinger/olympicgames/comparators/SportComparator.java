package edu.kit.informatik.genkinger.olympicgames.comparators;

import edu.kit.informatik.genkinger.olympicgames.Sport;

import java.util.Comparator;

public class SportComparator implements Comparator<Sport> {
    @Override
    public int compare(Sport o1, Sport o2) {
        return o1.getName().compareTo(o2.getName());
    }
}