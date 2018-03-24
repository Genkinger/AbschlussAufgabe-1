package edu.kit.informatik.genkinger.olympicmanagement;

/**
 * This class represents a {@link Discipline}.
 */

public class Discipline extends Invalidatable {

    private String sport;
    private String discipline;

    /**
     * Constructs a new {@link Discipline} of the sport <code>sport</code>.
     *
     * @param sport      the sport the discipline is associated with.
     * @param discipline the discipline.
     */
    Discipline(String sport, String discipline) {
        this.sport = sport;
        this.discipline = discipline;
    }

    /**
     * Returns the sport.
     *
     * @return the sport.
     */
    public String getSport() {
        return sport;
    }

    /**
     * Returns the discipline.
     *
     * @return the discipline.
     */
    public String getDiscipline() {
        return discipline;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Discipline) {
            Discipline dis = (Discipline) obj;
            return dis.getSport().equals(sport) && dis.getDiscipline().equals(discipline);
        }
        return false;
    }

    @Override
    public String toString() {
        return sport + " " + discipline;
    }
}
