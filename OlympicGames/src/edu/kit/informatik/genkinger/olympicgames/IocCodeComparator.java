package edu.kit.informatik.genkinger.olympicgames;

import java.util.Comparator;

public class IocCodeComparator implements Comparator<IocCode> {
    @Override
    public int compare(IocCode o1, IocCode o2) {
        if (o1.getYear() > o2.getYear()) {
            return 1;
        } else if (o1.getYear() < o2.getYear()) {
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
