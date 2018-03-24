package edu.kit.informatik.genkinger.olympicmanagement;


/**
 * This class represents the end result of a {@link Competition} in the olympic games.
 */
public class Competition extends Invalidatable {

    private Athlete athlete;
    private String year;
    private IocCode iocCode;
    private Discipline discipline;
    private Medals medals;


    /**
     * Creates a new {@link Competition}.
     *
     * @param athlete    the {@link Athlete} that won the competition.
     * @param year       the year the competition was held in.
     * @param iocCode    the {@link IocCode} of the country the Athlete competes for.
     * @param discipline the discipline
     * @param medals     the medals won by the {@link Athlete}
     */
    Competition(Athlete athlete, String year,
                IocCode iocCode,
                Discipline discipline, Medals medals) {
        if (!validYear(year)) {
            invalidate("invalid year");
        }
        this.athlete = athlete;
        this.year = year;
        this.iocCode = iocCode;
        this.discipline = discipline;
        this.medals = medals;
    }

    /**
     * Returns the {@link Athlete}.
     *
     * @return the {@link Athlete}
     */
    Athlete getAthlete() {
        return athlete;
    }

    /**
     * Returns the year.
     *
     * @return the year.
     */
    String getYear() {
        return year;
    }

    /**
     * Returns the {@link IocCode}.
     *
     * @return the {@link IocCode}.
     */
    IocCode getIocCode() {
        return iocCode;
    }

    /**
     * Returns the {@link Discipline}.
     *
     * @return the {@link Discipline}.
     */
    Discipline getDiscipline() {
        return discipline;
    }

    /**
     * Returns the {@link Medals} that were won in the {@link Competition} by the {@link Athlete}.
     *
     * @return the {@link Medals} that were won in the {@link Competition} by the {@link Athlete}.
     */
    public Medals getMedals() {
        return medals;
    }

    private boolean validYear(String year) {
        if (!year.matches("[0-9]{4}")) {
            return false;
        }
        int y = Integer.parseInt(year);
        for (int i = 1926; i <= 2018; i += 4) {
            if (y == i) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Competition) {
            Competition competition = (Competition) obj;
            return (competition.getAthlete().equals(athlete)
                    && competition.getDiscipline().equals(discipline)
                    && competition.getIocCode().equals(iocCode)
                    && competition.getYear().equals(year));
        }
        return false;
    }
}
