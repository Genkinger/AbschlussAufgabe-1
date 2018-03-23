package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.Clearable;
import edu.kit.informatik.genkinger.olympicgames.Sport;
import edu.kit.informatik.genkinger.olympicgames.comparators.SportComparator;

import java.util.ArrayList;

/**
 * This class is a {@link Container} of {@link Sport} objects.
 */
public class SportContainer extends Container implements Clearable {
    private ArrayList<Sport> sports = new ArrayList<>();

    /**
     * Adds a new {@link Sport} to the {@link Container}.
     *
     * @param name       the name of the {@link Sport}.
     * @param discipline the name of the {@link edu.kit.informatik.genkinger.olympicgames.Discipline}.
     * @return <code>true</code> if no errors occurred.
     * <code>false</code> otherwise.
     */
    public boolean addSport(String name, String discipline) {

        Sport sport = findSportByName(name);
        if (sport == null) {
            Sport s = new Sport(name);
            s.addDiscipline(discipline);
            sports.add(s);
            sports.sort(new SportComparator());
        } else {
            if (!sport.addDiscipline(discipline)) {
                setErrorString(sport.getErrorString());
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the {@link Sport} with name <code>name</code>.
     *
     * @param name the name of the {@link Sport}.
     * @return the {@link Sport} with name <code>name</code>.
     * <code>null</code> otherwise.
     */
    Sport findSportByName(String name) {
        for (Sport sport : sports) {
            if (sport.getName().equals(name)) {
                return sport;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        sports.clear();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Sport sport : sports) {
            builder.append(sport);
            builder.append("\n");
        }

        return builder.toString().trim();
    }
}
