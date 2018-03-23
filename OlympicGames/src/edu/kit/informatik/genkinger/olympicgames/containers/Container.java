package edu.kit.informatik.genkinger.olympicgames.containers;

/**
 * This class is used as a base for other Container classes that need similar functionality.
 * (error strings)
 *
 * @see AdminContainer
 * @see AthleteContainer
 * @see CompetitionContainer
 * @see IocContainer
 * @see SportContainer
 * @see VenueContainer
 */
public abstract class Container {
    private String errorString = null;

    /**
     * Returns the error string.
     *
     * @return the error string.
     */
    public String getErrorString() {
        return errorString;
    }

    /**
     * Sets the error string
     *
     * @param errorString the error string.
     */
    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}
