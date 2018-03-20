package edu.kit.informatik.genkinger.olympicgames;

public class Athlete extends Person {

    private String id;
    private IocCode iocCode;
    private Sport sport;
    private Discipline discipline;
    private int medalCount;

    public Athlete(String id, String firstName, String lastName, IocCode iocCode, Sport sport, Discipline discipline) {
        super(firstName, lastName);
        this.id = id;
        this.iocCode = iocCode;
        this.sport = sport;
        this.discipline = discipline;
    }

    public String getId() {
        return id;
    }

    public IocCode getIocCode() {
        return iocCode;
    }

    public Sport getSport() {
        return sport;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public int getMedalCount() {
        return medalCount;
    }

    public void setMedalCount(int medalCount) {
        this.medalCount = medalCount;
    }

    @Override
    public String toString() {
        return id + " " + getFirstName() + " " + getLastName() + " " + medalCount;
    }
}
