package edu.kit.informatik.genkinger.olympicgames;

/**
 *
 */
public class CountrySummary {
    private IocCode iocCode;
    private Medals medals;

    /**
     *
     * @param iocCode .
     * @param medals .
     */
    CountrySummary(IocCode iocCode, Medals medals) {
        this.iocCode = iocCode;
        this.medals = medals;
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
     * @param iocCode .
     */
    public void setIocCode(IocCode iocCode) {
        this.iocCode = iocCode;
    }

    /**
     *
     * @return .
     */
    public Medals getMedals() {
        return medals;
    }

    /**
     *
     * @param medals .
     */
    public void setMedals(Medals medals) {
        this.medals = medals;
    }

    @Override
    public String toString() {
        return iocCode.getId() + "," + iocCode.getCode() + "," + iocCode.getCountryName() + "," + medals.toString();
    }
}
