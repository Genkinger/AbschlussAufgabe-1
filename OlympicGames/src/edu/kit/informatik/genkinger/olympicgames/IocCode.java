package edu.kit.informatik.genkinger.olympicgames;

public class IocCode {

    private int id;
    private String code;
    private String countryName;
    private int year;

    public IocCode(int id, String code, String countryName, int year) {
        this.id = id;
        this.code = code;
        this.countryName = countryName;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getYear() {
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
