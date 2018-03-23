package edu.kit.informatik.genkinger.olympicgames;

/**
 * This class represents a {@link Discipline} of a {@link Sport}
 * @see Sport
 */
public class Discipline {

    private String name;


    /**
     * Constructs a new {@link Discipline} by name.
     *
     * @param name the name of the {@link Discipline}.
     */
    Discipline(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the {@link Discipline}.
     *
     * @return the name of the {@link Discipline}.
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Discipline) {
            Discipline discipline = (Discipline) obj;
            return discipline.getName().equals(name);
        }
        return false;
    }

    @Override
    public String toString() {
        return getName();
    }
}
