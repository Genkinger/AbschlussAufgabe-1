package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.*;

import java.util.ArrayList;

public class AthleteContainer extends Container implements Clearable {
    ArrayList<Athlete> athletes = new ArrayList<>();

    public boolean addAthlete(int id, String firstName, String lastName, IocCode code, Sport sport, Discipline discipline) {
        return true;
    }

    @Override
    public void clear() {
        athletes.clear();
    }

    public ArrayList<Athlete> getAthletes() {
        return athletes;
    }
}
