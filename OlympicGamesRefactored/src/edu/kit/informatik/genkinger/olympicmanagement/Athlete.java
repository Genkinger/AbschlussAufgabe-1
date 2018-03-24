package edu.kit.informatik.genkinger.olympicmanagement;

/**
 * This class represents an Athlete that takes part in the olympic games.
 *
 * @author Lukas Genkinger
 */
public class Athlete extends Person {

    private String id;
    private IocCode iocCode;
    private Discipline discipline;
    private int medalCount;

    /**
     * Constructs a new Athlete.
     *
     * @param id         the id of the Athlete.
     * @param firstName  the first name of the Athlete.
     * @param lastName   the last name of the Athlete.
     * @param iocCode    the ioc code of the country the Athlete competes for.
     * @param discipline the discipline the Athlete competes in.
     */
    public Athlete(String id, String firstName, String lastName, IocCode iocCode, Discipline discipline) {
        super(firstName, lastName);

        if (!id.matches("[0-9]{4}") || id.equals("0000")) {
            invalidate("invalid id");
        }

        this.id = id;
        this.iocCode = iocCode;
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
    IocCode getIocCode() {
        return iocCode;
    }

    /**
     * @return .
     */
    Discipline getDiscipline() {
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
    void setMedalCount(int medalCount) {
        this.medalCount = medalCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Athlete) {
            Athlete athlete = (Athlete) obj;

            if (athlete.getId().equals(id)
                    || (athlete.getFirstName().equals(getFirstName())
                    && athlete.getDiscipline().equals(discipline)
                    && athlete.getIocCode().equals(iocCode)
                    && athlete.getLastName().equals(getLastName()))) {
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
