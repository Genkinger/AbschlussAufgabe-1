package edu.kit.informatik.genkinger.olympicgames;

/**
 * This class represents an Athlete that takes part in the olympic games.
 */
public class Athlete extends Person {

    private String id;
    private IocCode iocCode;
    private Sport sport;
    private Discipline discipline;
    private int medalCount;

    /**
     * Constructs a new Athlete.
     *
     * @param id         the id of the Athlete.
     * @param firstName  the first name of the Athlete.
     * @param lastName   the last name of the Athlete.
     * @param iocCode    the ioc code of the country the Athlete competes for.
     * @param sport      the sport the Athlete competes in.
     * @param discipline the discipline the Athlete competes in.
     */
    public Athlete(String id, String firstName, String lastName, IocCode iocCode, Sport sport, Discipline discipline) {
        super(firstName, lastName);
        this.id = id;
        this.iocCode = iocCode;
        this.sport = sport;
        this.discipline = discipline;
    }

    /**
     * Returns the id.
     *
     * @return the id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the {@link IocCode} of the country the Athlete competes for.
     *
     * @return the {@link IocCode} of the country the Athlete competes for.
     */
    public IocCode getIocCode() {
        return iocCode;
    }

    /**
     * Returns the {@link Sport} the Athlete competes in.
     *
     * @return the {@link Sport} the Athlete competes in.
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
     * Returns the amount of medals the Athlete has won.
     *
     * @return the amount of medals the Athlete has won.
     */
    public int getMedalCount() {
        return medalCount;
    }

    /**
     * Sets the amount of medals won by the Athlete.
     *
     * @param medalCount the total amount of medals won by the Athlete.
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
