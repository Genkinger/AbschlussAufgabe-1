package edu.kit.informatik.genkinger.olympicgames.comparators;

import edu.kit.informatik.genkinger.olympicgames.IocCode;

import java.util.Comparator;

/**
 * Comparator for {@link IocCode}s.
 */
public class IocCodeComparator implements Comparator<IocCode> {
    @Override
    public int compare(IocCode o1, IocCode o2) {
        if (Integer.parseInt(o1.getYear()) > Integer.parseInt(o2.getYear())) {
            return 1;
        } else if (Integer.parseInt(o1.getYear()) < Integer.parseInt(o2.getYear())) {
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
