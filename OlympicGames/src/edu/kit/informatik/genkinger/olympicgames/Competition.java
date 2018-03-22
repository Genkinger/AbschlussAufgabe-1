package edu.kit.informatik.genkinger.olympicgames;

/**
 *
 */
public class Competition {

    private Athlete athlete;
    private String year;
    private IocCode iocCode;
    private Sport sport;
    private Discipline discipline;
    private Medals medals;


    /**
     * @param athlete    .
     * @param year       .
     * @param iocCode    .
     * @param sport      .
     * @param discipline .
     * @param medals     .
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
     * @return .
     */
    public Athlete getAthlete() {
        return athlete;
    }

    /**
     * @return .
     */
    public String getYear() {
        return year;
    }

    /**
     * @return .
     */
    public IocCode getIocCode() {
        return iocCode;
    }

    /**
     * @return .
     */
    public Sport getSport() {
        return sport;
    }

    /**
     * @return .
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * @return .
     */
    public Medals getMedals() {
        return medals;
    }
}
