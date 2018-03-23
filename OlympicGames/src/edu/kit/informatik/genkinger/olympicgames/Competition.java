package edu.kit.informatik.genkinger.olympicgames;

/**
 * This class represents the end result of a {@link Competition} in the olympic games.
 */
public class Competition {

    private Athlete athlete;
    private String year;
    private IocCode iocCode;
    private Sport sport;
    private Discipline discipline;
    private Medals medals;


    /**
     * Creates a new {@link Competition}.
     *
     * @param athlete    the {@link Athlete} that won the competition.
     * @param year       the year the competition was held in.
     * @param iocCode    the {@link IocCode} of the country the Athlete competes for.
     * @param sport      the sport
     * @param discipline the discipline
     * @param medals     the medals won by the {@link Athlete}
     */
    public Competition(Athlete athlete, String year,
                       IocCode iocCode, Sport sport,
                       Discipline discipline, Medals medals) {
        this.athlete = athlete;
        this.year = year;
        this.iocCode = iocCode;
        this.sport = sport;
        this.discipline = discipline;
        this.medals = medals;
    }

    /**
     * Returns the {@link Athlete}.
     *
     * @return the {@link Athlete}
     */
    public Athlete getAthlete() {
        return athlete;
    }

    /**
     * Returns the year.
     *
     * @return the year.
     */
    public String getYear() {
        return year;
    }

    /**
     * Returns the {@link IocCode}.
     *
     * @return the {@link IocCode}.
     */
    public IocCode getIocCode() {
        return iocCode;
    }

    /**
     * Returns the {@link Sport}.
     *
     * @return the {@link Sport}.
     */
    public Sport getSport() {
        return sport;
    }

    /**
     * Returns the {@link Discipline}.
     *
     * @return the {@link Discipline}.
     */
    public Discipline getDiscipline() {
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
}
