package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.Clearable;
import edu.kit.informatik.genkinger.olympicgames.Sport;

import java.util.ArrayList;

public class SportContainer extends Container implements Clearable {
    private ArrayList<Sport> sports = new ArrayList<>();

    public boolean addSport(String name) {
        if (findSportByName(name) != null) {
            setErrorString("sport already exists");
            return false;
        }

        sports.add(new Sport(name));
        return true;
    }

    public boolean addDisciplineToSport(String name, String discipline) {
        Sport sport = findSportByName(name);
        if (sport == null) {
            setErrorString("unknown sport");
            return false;
        }

        if (!sport.addDiscipline(discipline)) {
            setErrorString(sport.getErrorString());
            return false;
        }

        return true;
    }

    public Sport findSportByName(String name) {
        for (Sport sport : sports) {
            if (sport.getName().equals(name)) {
                return sport;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        sports.clear();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Sport sport : sports) {
            builder.append(sport);
            builder.append("\n");
        }

        return builder.toString().trim();
    }
}
