package edu.kit.informatik.genkinger.olympicgames;

public class Athlete extends Person {

    private int id;
    private IocCode iocCode;
    private Sport sport;
    private Discipline discipline;
    private int medalCount;

    public Athlete(String firstName, String lastName, int id, IocCode iocCode, Sport sport, Discipline discipline) {
        super(firstName, lastName);
        this.id = id;
        this.iocCode = iocCode;
        this.sport = sport;
        this.discipline = discipline;
    }

    public int getId() {
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
