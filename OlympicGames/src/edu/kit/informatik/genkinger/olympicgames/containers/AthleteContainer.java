package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.*;
import edu.kit.informatik.genkinger.olympicgames.comparators.AthleteComparator;

import java.util.ArrayList;

/**
 *
 */
public class AthleteContainer extends Container implements Clearable {
    private ArrayList<Athlete> athletes = new ArrayList<>();

    private IocContainer iocContainer;
    private SportContainer sportContainer;

    /**
     * @param iocContainer   .
     * @param sportContainer .
     */
    public AthleteContainer(IocContainer iocContainer, SportContainer sportContainer) {
        this.iocContainer = iocContainer;
        this.sportContainer = sportContainer;
    }

    /**
     * @param id         .
     * @param firstName  .
     * @param lastName   .
     * @param country    .
     * @param sport      .
     * @param discipline .
     * @return .
     */
    public boolean addAthlete(String id, String firstName,
                              String lastName, String country,
                              String sport, String discipline) {

        if (!id.matches("[0-9]{4}") || id.equals("0000")) {
            setErrorString("invalid id");
        }

        IocCode iocCode = iocContainer.findIocCodeByCountry(country);
        if (iocCode == null) {
            setErrorString("invalid country");
            return false;
        }

        Sport s = sportContainer.findSportByName(sport);
        if (s == null) {
            setErrorString("invalid sport");
            return false;
        }

        Discipline dis = s.findDisciplineByName(discipline);
        if (dis == null) {
            setErrorString("invalid discipline");
            return false;
        }

        if (findById(id) != null) {
            setErrorString("athlete with that id already exists");
            return false;
        }

        Athlete ath = new Athlete(id, firstName, lastName, iocCode, s, dis);

        ArrayList<Athlete> athletesWithSameFirstName = findAthletesByFirstName(firstName);
        for (Athlete athlete : athletesWithSameFirstName) {
            if (athlete.equals(ath)) {
                setErrorString("athlete already exists");
                return false;
            }
        }

        athletes.add(ath);

        return true;
    }

    @Override
    public void clear() {
        athletes.clear();
    }

    /**
     * @param id .
     * @return .
     */
    Athlete findById(String id) {
        for (Athlete athlete : athletes) {
            if (athlete.getId().equals(id)) {
                return athlete;
            }
        }
        return null;
    }

    /**
     * @param sport      .
     * @param discipline .
     * @return .
     */
    public ArrayList<Athlete> findBySportAndDiscipline(String sport, String discipline) {

        Sport s = sportContainer.findSportByName(sport);
        if (s == null) {
            setErrorString("invalid sport");
            return null;
        }
        Discipline dis = s.findDisciplineByName(discipline);

        if (dis == null) {
            setErrorString("invalid discipline");
            return null;
        }

        ArrayList<Athlete> aths = new ArrayList<>();
        for (Athlete athlete : athletes) {
            if (athlete.getSport().equals(s)
                    && athlete.getDiscipline().equals(dis)) {
                aths.add(athlete);
            }
        }
        aths.sort(new AthleteComparator());
        return aths;
    }

    private ArrayList<Athlete> findAthletesByFirstName(String firstName) {

        ArrayList<Athlete> aths = new ArrayList<>();

        for (Athlete athlete : athletes) {
            if (athlete.getFirstName().equals(firstName)) {
                aths.add(athlete);
            }
        }
        return aths;
    }
}
