package edu.kit.informatik.genkinger.olympicgames;

public class Athlete extends Person {

    private int id;
    private Country country;
    private Sport sport;
    private SportDiscipline discipline;

    public Athlete(String firstName, String lastName, int id, Country country, Sport sport, SportDiscipline discipline) {
        super(firstName, lastName);
        this.id = id;
        this.country = country;
        this.sport = sport;
        this.discipline = discipline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public SportDiscipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(SportDiscipline discipline) {
        this.discipline = discipline;
    }
}
