package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.*;

import java.util.ArrayList;

public class AthleteContainer extends Container implements Clearable {
    private ArrayList<Athlete> athletes = new ArrayList<>();

    public boolean addAthlete(String id, String firstName, String lastName, IocCode code, Sport sport, Discipline discipline) {
        if (!id.matches("[0-9]{4}")) {
            setErrorString("invalid id");
        }

        athletes.add(new Athlete(id, firstName, lastName, code, sport, discipline));

        return true;
    }

    @Override
    public void clear() {
        athletes.clear();
    }

    public Athlete findAthleteById(String id) {
        for (Athlete athlete : athletes) {
            if (athlete.getId().equals(id)) {
                return athlete;
            }
        }
        return null;
    }

    public ArrayList<Athlete> getAthletes() {
        return athletes;
    }
}
