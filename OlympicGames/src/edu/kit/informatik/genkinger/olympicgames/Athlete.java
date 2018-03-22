package edu.kit.informatik.genkinger.olympicgames;

/**
 *
 */
public class Athlete extends Person {

    private String id;
    private IocCode iocCode;
    private Sport sport;
    private Discipline discipline;
    private int medalCount;

    /**
     *
     * @param id .
     * @param firstName .
     * @param lastName .
     * @param iocCode .
     * @param sport .
     * @param discipline .
     */
    public Athlete(String id, String firstName, String lastName, IocCode iocCode, Sport sport, Discipline discipline) {
        super(firstName, lastName);
        this.id = id;
        this.iocCode = iocCode;
        this.sport = sport;
        this.discipline = discipline;
    }

    /**
     *
     * @return .
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return .
     */
    public IocCode getIocCode() {
        return iocCode;
    }

    /**
     *
     * @return .
     */
    public Sport getSport() {
        return sport;
    }

    /**
     *
     * @return .
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     *
     * @return .
     */
    public int getMedalCount() {
        return medalCount;
    }

    /**
     *
     * @param medalCount .
     */
    public void setMedalCount(int medalCount) {
        this.medalCount = medalCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Athlete) {
            Athlete athlete = (Athlete) obj;

            if (athlete.getFirstName().equals(getFirstName())
                    && athlete.getSport().equals(sport)
                    && athlete.getDiscipline().equals(discipline)
                    && athlete.getIocCode().equals(iocCode)
                    && athlete.getLastName().equals(getLastName())) {
                return true;
            }

        }
        return false;
    }

    @Override
    public String toString() {
        return id + " " + getFirstName() + " " + getLastName() + " " + medalCount;
    }
}
