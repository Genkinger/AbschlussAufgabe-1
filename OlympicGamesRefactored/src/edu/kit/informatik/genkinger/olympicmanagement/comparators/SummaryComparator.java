package edu.kit.informatik.genkinger.olympicmanagement.comparators;

import edu.kit.informatik.genkinger.olympicmanagement.CountrySummary;
import edu.kit.informatik.genkinger.olympicmanagement.Medals;

import java.util.Comparator;

/**
 * Comparator for {@link CountrySummary} objects.
 */
public class SummaryComparator implements Comparator<CountrySummary> {

    @Override
    public int compare(CountrySummary o1, CountrySummary o2) {
        Medals medals1 = o1.getMedals();
        Medals medals2 = o2.getMedals();

        if (medals1.getGold() < medals2.getGold()) {
            return 1;
        }

        if (medals1.getGold() > medals2.getGold()) {
            return -1;
        }

        if (medals1.getSilver() < medals2.getSilver()) {
            return 1;
        }

        if (medals1.getSilver() > medals2.getSilver()) {
            return -1;
        }

        if (medals1.getBronze() < medals2.getBronze()) {
            return 1;
        }

        if (medals1.getBronze() > medals2.getBronze()) {
            return -1;
        }

        int id1 = Integer.parseInt(o1.getIocCode().getId());
        int id2 = Integer.parseInt(o2.getIocCode().getId());

        if (id1 < id2) {
            return 1;
        }

        if (id1 > id2) {
            return -1;
        }

        return 0;
    }
}
