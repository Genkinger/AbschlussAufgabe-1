package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.*;

import java.util.ArrayList;

public class CompetitionContainer extends Container implements Clearable {

    private ArrayList<Competition> competitions = new ArrayList<>();

    public boolean addCompetition(Athlete athlete, String year, IocCode code, Sport sport, Discipline discipline, Medals medals) {

        if (!year.matches("[0-9]{4}")) {
            setErrorString("invalid year");
            return false;
        }

        competitions.add(new Competition(athlete.getId(), year, code, sport, discipline, medals));
        athlete.setMedalCount(athlete.getMedalCount() + medals.getBronze() + medals.getGold() + medals.getSilver());

        return true;
    }

    @Override
    public void clear() {
        competitions.clear();
    }

    @Override
    public String toString() {
        return "";
    }
}
