package edu.kit.informatik.genkinger.olympicgames;

public class Competition {

    private String athleteId;
    private String year;
    private IocCode iocCode;
    private Sport sport;
    private Discipline discipline;
    private Medals medals;


    public Competition(String athleteId, String year, IocCode iocCode, Sport sport, Discipline discipline, Medals medals) {
        this.athleteId = athleteId;
        this.year = year;
        this.iocCode = iocCode;
        this.sport = sport;
        this.discipline = discipline;
        this.medals = medals;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public String getYear() {
        return year;
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

    public Medals getMedals() {
        return medals;
    }
}
