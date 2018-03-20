package edu.kit.informatik.genkinger.olympicgames;

public class Venue {

    private String id;
    private IocCode iocCode;
    private String locus;
    private String name;
    private String year;
    private int spectatorCount;


    public Venue(String id, IocCode iocCode, String locus, String name, String year, int spectatorCount) {
        this.id = id;
        this.iocCode = iocCode;
        this.locus = locus;
        this.name = name;
        this.year = year;
        this.spectatorCount = spectatorCount;
    }

    public String getId() {
        return id;
    }

    public IocCode getIocCode() {
        return iocCode;
    }

    public String getLocus() {
        return locus;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public int getSpectatorCount() {
        return spectatorCount;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Venue) {
            Venue v = (Venue) obj;
            if (v.getId() == id
                    || (v.getIocCode().equals(iocCode)
                    && v.getLocus().equals(locus)
                    && v.getName().equals(name)
                    && v.getSpectatorCount() == spectatorCount)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return id + " " + locus + " " + spectatorCount;
    }


}

