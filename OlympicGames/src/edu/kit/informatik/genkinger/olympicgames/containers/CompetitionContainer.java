package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.*;

import java.util.ArrayList;

public class CompetitionContainer extends Container implements Clearable {

    ArrayList<Competition> competitions = new ArrayList<>();

    public boolean addCompetition(int athleteId, int year, IocCode code, Sport sport, Discipline discipline, Medals table) {
        return true;
    }

    @Override
    public void clear() {
        competitions.clear();
    }
}
