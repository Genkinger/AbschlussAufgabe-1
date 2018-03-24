package edu.kit.informatik.genkinger.olympicmanagement;

/**
 * The class representing an {@link IocCode}.
 */
public class IocCode extends Invalidatable {

    private String id;
    private String code;
    private String countryName;
    private String year;

    /**
     * Constructs a new {@link IocCode}.
     *
     * @param id          the id of the {@link IocCode}.
     * @param code        the shorthand code of the country represented by this {@link IocCode}.
     * @param countryName the name of the country represented by this {@link IocCode}.
     * @param year        the year the {@link IocCode} was created.
     */
    public IocCode(String id, String code, String countryName, String year) {
        if (!id.matches("[0-9]{3}") || id.equals("000")) {
            invalidate("invalid id");
        }

        if (!code.matches("[a-z]{3}")) {
            invalidate("invalid code");
        }

        if (!year.matches("[0-9]{4}")) {
            invalidate("invalid year");
        }

        this.id = id;
        this.code = code;
        this.countryName = countryName;
        this.year = year;
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
     * Returns the shorthand code.
     *
     * @return the shorthand code.
     */
    String getCode() {
        return code;
    }

    /**
     * Returns the country name.
     *
     * @return the country name.
     */
    String getCountryName() {
        return countryName;
    }

    /**
     * Returns the year.
     *
     * @return the year.
     */
    public String getYear() {
        return year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IocCode) {
            IocCode c = (IocCode) obj;
            if (c.getId().equals(id) || c.getCountryName().equals(countryName) || c.getCode().equals(code)) {
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
