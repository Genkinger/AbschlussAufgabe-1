package edu.kit.informatik.genkinger.olympicgames;

import edu.kit.informatik.genkinger.olympicgames.comparators.DisciplineComparator;
import edu.kit.informatik.genkinger.olympicgames.containers.Container;

import java.util.ArrayList;

/**
 * This class represents a {@link Sport} in the olympic games.
 */
public class Sport extends Container {

    private String name;
    private ArrayList<Discipline> disciplines = new ArrayList<>();


    /**
     * Constructs a new {@link Sport} by name.
     *
     * @param name the name of the sport.
     */
    public Sport(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the {@link Sport}.
     *
     * @return the name of the {@link Sport}.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a {@link Discipline} to the {@link Sport}.
     *
     * @param discipline the name of the {@link Discipline} to add.
     * @return <code>true</code> if no errors occurred.
     * <code>false</code> otherwise.
     */
    public boolean addDiscipline(String discipline) {
        if (findDisciplineByName(discipline) != null) {
            setErrorString("discipline already exists for " + name);
            return false;
        }

        disciplines.add(new Discipline(discipline));
        disciplines.sort(new DisciplineComparator());

        return true;
    }

    /**
     * Returns the {@link Discipline} with name <code>name</code>
     * if it exists.
     *
     * @param name of the {@link Discipline}.
     * @return the {@link Discipline} with name <code>name</code>.
     * <code>null</code> otherwise.
     */
    public Discipline findDisciplineByName(String name) {
        for (Discipline discipline : disciplines) {
            if (discipline.getName().equals(name)) {
                return discipline;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sport) {
            Sport sport = (Sport) obj;
            return name.equals(sport.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Discipline discipline : disciplines) {
            builder.append(name);
            builder.append(" ");
            builder.append(discipline);
            builder.append("\n");
        }
        return builder.toString().trim();
    }
}
