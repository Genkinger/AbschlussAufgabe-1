package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.*;

import java.util.ArrayList;

/**
 * This class is a {@link Container} of {@link Competition} objects.
 */
public class CompetitionContainer extends Container implements Clearable {
    private AthleteContainer athleteContainer;
    private IocContainer iocContainer;
    private SportContainer sportContainer;
    private MedalTable medalTable;

    private ArrayList<Competition> competitions = new ArrayList<>();

    /**
     * Constructs a new {@link CompetitionContainer}.
     *
     * @param athleteContainer the {@link AthleteContainer}.
     * @param iocContainer     the {@link IocContainer}.
     * @param sportContainer   the {@link SportContainer}.
     * @param medalTable       the {@link MedalTable}.
     */
    public CompetitionContainer(AthleteContainer athleteContainer, IocContainer iocContainer,
                                SportContainer sportContainer, MedalTable medalTable) {
        this.athleteContainer = athleteContainer;
        this.iocContainer = iocContainer;
        this.sportContainer = sportContainer;
        this.medalTable = medalTable;
    }

    /**
     * Adds a new {@link Competition} to the {@link Container}.
     *
     * @param athleteId  the id of the Athlete
     * @param year       the year of the competiton.
     * @param country    the country of the athlete.
     * @param sport      the sport.
     * @param discipline the discipline.
     * @param medals     the medals won in the competition.
     * @return <code>true</code> if no errors occurred.
     * <code>false</code> otherwise.
     */
    public boolean addCompetition(String athleteId, String year,
                                  String country, String sport,
                                  String discipline, Medals medals) {

        Athlete athlete = athleteContainer.findById(athleteId);
        if (athlete == null) {
            setErrorString("invalid athlete");
            return false;
        }
        IocCode code = iocContainer.findIocCodeByCountry(country);
        if (code == null) {
            setErrorString("invalid country");
            return false;
        }
        Sport s = sportContainer.findSportByName(sport);
        if (s == null) {
            setErrorString("invalid sport");
            return false;
        }
        Discipline d = s.findDisciplineByName(discipline);
        if (d == null) {
            setErrorString("invalid discipline");
            return false;
        }
        if (!athlete.getSport().equals(s)) {
            setErrorString("athlete does not compete in this sport");
            return false;
        }
        if (!athlete.getDiscipline().equals(d)) {
            setErrorString("athlete does not compete in this discipline");
            return false;
        }
        if (!athlete.getIocCode().equals(code)) {
            setErrorString("invalid country for athlete");
            return false;
        }
        if (!checkYear(year)) {
            setErrorString("invalid year");
            return false;
        }
        if (!checkMedals(medals)) {
            setErrorString("invalid medal count");
            return false;
        }
        ArrayList<Competition> comps = findByAthlete(athlete);
        for (Competition competition : comps) {
            if (competition.getYear().equals(year)
                    && competition.getSport().equals(s)
                    && competition.getDiscipline().equals(d)) {
                setErrorString("competition already exists");
                return false;
            }
        }

        competitions.add(new Competition(athlete, year, code, s, d, medals));
        athlete.setMedalCount(athlete.getMedalCount() + medals.getBronze() + medals.getGold() + medals.getSilver());
        medalTable.addCompetitionResults(code, medals);
        return true;
    }


    private boolean checkYear(String year) {
        if (!year.matches("[0-9]{4}")) {
            return false;
        }

        int y = Integer.parseInt(year);
        for (int i = 1926; i <= 2018; i += 4) {
            if (y == i) {
                return true;
            }
        }
        return false;
    }

    private boolean checkMedals(Medals medals) {
        int sum = medals.getBronze() + medals.getGold() + medals.getSilver();
        if (sum > 1) {
            return false;
        }
        return true;
    }

    private ArrayList<Competition> findByAthlete(Athlete athlete) {
        ArrayList<Competition> comps = new ArrayList<>();
        for (Competition competition : competitions) {
            if (competition.getAthlete().equals(athlete)) {
                comps.add(competition);
            }
        }
        return comps;
    }

    @Override
    public void clear() {
        competitions.clear();
    }
}
