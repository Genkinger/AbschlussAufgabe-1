package edu.kit.informatik.genkinger.olympicgames.comparators;

import edu.kit.informatik.genkinger.olympicgames.Discipline;

import java.util.Comparator;

public class DisciplineComparator implements Comparator<Discipline> {
    @Override
    public int compare(Discipline o1, Discipline o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
