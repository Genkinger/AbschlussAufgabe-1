package edu.kit.informatik.genkinger.olympicgames;

/**
 *
 */
public class IocCode {

    private String id;
    private String code;
    private String countryName;
    private String year;

    /**
     *
     * @param id .
     * @param code .
     * @param countryName .
     * @param year .
     */
    public IocCode(String id, String code, String countryName, String year) {
        this.id = id;
        this.code = code;
        this.countryName = countryName;
        this.year = year;
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
    public String getCode() {
        return code;
    }

    /**
     *
     * @return .
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     * @return .
     */
    public String getYear() {
        return year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IocCode) {
            IocCode c = (IocCode) obj;
            if (c.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return year + " " + id + " " + code + " " + countryName;
    }
}
