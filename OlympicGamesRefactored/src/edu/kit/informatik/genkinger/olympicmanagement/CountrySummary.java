package edu.kit.informatik.genkinger.olympicmanagement;

/**
 * This class represents a summary of the Medals
 * won by {@link Athlete}s of the Country specified by the {@link IocCode}.
 *
 * @author Lukas Genkinger
 * @see Athlete
 * @see IocCode
 */
public class CountrySummary {
    private IocCode iocCode;
    private Medals medals;

    /**
     * Constructs a new {@link CountrySummary}.
     *
     * @param iocCode the {@link IocCode}
     * @param medals  the {@link Medals} that were won by
     *                {@link Athlete}s competing for the country specified by the {@link IocCode}.
     */
    CountrySummary(IocCode iocCode, Medals medals) {
        this.iocCode = iocCode;
        this.medals = medals;
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
     * Returns the {@link Medals}.
     *
     * @return the {@link Medals}.
     */
    public Medals getMedals() {
        return medals;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CountrySummary) {
            CountrySummary summary = (CountrySummary) obj;
            return summary.getIocCode().equals(iocCode);
        }
        return false;
    }

    @Override
    public String toString() {
        return iocCode.getId() + "," + iocCode.getCode() + "," + iocCode.getCountryName() + "," + medals.toString();
    }
}
