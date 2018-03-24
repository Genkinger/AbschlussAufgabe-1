package edu.kit.informatik.genkinger.olympicmanagement.comparators;

import edu.kit.informatik.genkinger.olympicmanagement.Discipline;

import java.util.Comparator;

/**
 * Comparator for {@link Discipline}s.
 *
 * @author Lukas Genkinger
 */
public class DisciplineComparator implements Comparator<Discipline> {
    @Override
    public int compare(Discipline o1, Discipline o2) {
        int val = o1.getSport().compareTo(o2.getSport());
        if (val == 0) {
            return o1.getDiscipline().compareTo(o2.getDiscipline());
        }
        return val;

    }
}
