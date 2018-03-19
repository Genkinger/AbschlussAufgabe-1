package edu.kit.informatik.genkinger.olympicgames;

public class Venue {

    private int id;
    private IocCode iocCode;
    private String locus;
    private String name;
    private int year;
    private int spectatorCount;

    public Venue(int id, IocCode iocCode, String locus, String name, int year, int spectatorCount) {
        this.id = id;
        this.iocCode = iocCode;
        this.locus = locus;
        this.name = name;
        this.year = year;
        this.spectatorCount = spectatorCount;
    }

    public int getId() {
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

    public int getYear() {
        return year;
    }

    public int getSpectatorCount() {
        return spectatorCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Venue) {
            Venue v = (Venue) obj;
            if (v.getId() == id) {
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

