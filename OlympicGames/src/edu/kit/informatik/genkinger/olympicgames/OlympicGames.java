package edu.kit.informatik.genkinger.olympicgames;

import edu.kit.informatik.genkinger.controller.*;
import edu.kit.informatik.genkinger.olympicgames.containers.*;

import java.util.ArrayList;

public class OlympicGames {


    private AdminContainer adminContainer = new AdminContainer();
    private AthleteContainer athleteContainer = new AthleteContainer();
    private VenueContainer venueContainer = new VenueContainer();
    private SportContainer sportContainer = new SportContainer();
    private IocContainer iocContainer = new IocContainer();
    private CompetitionContainer competitionContainer = new CompetitionContainer();

    private StringInputController controller;
    private StringOutputInterface outputInterface;


    OlympicGames(StringInputInterface inputInterface, StringOutputInterface outputInterface) {
        this.outputInterface = outputInterface;
        this.controller = new StringInputController(inputInterface);

        controller.attachDefaultAction((controller, cmd) -> outputInterface.printError(cmd.getReasonForInvalidation()));
        registerCommands();
    }

    private void registerCommands() {
        CommandLayout layoutEmpty = new CommandLayout();
        CommandLayout layoutTwoStrings = new CommandLayout().
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING);

        CommandLayout layoutAddAdmin = new CommandLayout().
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING);

        CommandLayout layoutAddSportsVenue = new CommandLayout().
                addParameter(CommandParameterType.INT).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.INT).
                addParameter(CommandParameterType.INT);

        CommandLayout layoutListSportsVenues = new CommandLayout().
                addParameter(CommandParameterType.STRING);

        CommandLayout layoutAddIocCode = new CommandLayout().
                addParameter(CommandParameterType.INT).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.INT);

        CommandLayout layoutAddAthlete = new CommandLayout().
                addParameter(CommandParameterType.INT).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING);

        CommandLayout layoutAddCompetition = new CommandLayout().
                addParameter(CommandParameterType.INT).
                addParameter(CommandParameterType.INT).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.STRING).
                addParameter(CommandParameterType.INT).
                addParameter(CommandParameterType.INT).
                addParameter(CommandParameterType.INT);

        CommandLayout[] layouts = {
                layoutAddAdmin,
                layoutTwoStrings,
                layoutEmpty,
                layoutAddSportsVenue,
                layoutListSportsVenues,
                layoutTwoStrings,
                layoutEmpty,
                layoutAddIocCode,
                layoutEmpty,
                layoutAddAthlete,
                layoutTwoStrings,
                layoutAddCompetition,
                layoutEmpty,
                layoutEmpty,
                layoutEmpty
        };

        String[] cmdStrings = {
                "add-admin",
                "login-admin",
                "logout-admin",
                "add-sports-venue",
                "list-sports-venues",
                "add-olympic-sport",
                "list-olympic-sports",
                "add-ioc-code",
                "list-ioc-codes",
                "add-athlete",
                "summary-athletes",
                "add-competition",
                "olympic-medal-table",
                "reset",
                "quit"
        };

        Action[] actions = {
                (c, cmd) -> {
                    if (checkLogin()) {
                        outputInterface.printError("can't execute command when logged in");
                        return;
                    }
                    String firstName = cmd.getStringParameter(0);
                    String lastName = cmd.getStringParameter(1);
                    String username = cmd.getStringParameter(2);
                    String password = cmd.getStringParameter(3);

                    if (!adminContainer.addAdmin(firstName, lastName, username, password)) {
                        outputInterface.printError(adminContainer.getErrorString());
                    } else {
                        ok();
                    }

                }, //add-admin

                (c, cmd) -> {
                    String username = cmd.getStringParameter(0);
                    String password = cmd.getStringParameter(1);

                    if (!adminContainer.loginAdmin(username, password)) {
                        outputInterface.printError(adminContainer.getErrorString());
                    } else {
                        ok();
                    }
                }, //login-admin

                (c, cmd) -> {
                    if (!adminContainer.logoutAdmin()) {
                        outputInterface.printError(adminContainer.getErrorString());
                    } else {
                        ok();
                    }
                }, //logout-admin

                (c, cmd) -> {
                    if (!checkLogin()) {
                        return;
                    }
                    int id = cmd.getIntegerParameter(0);
                    String countryName = cmd.getStringParameter(0);
                    String locus = cmd.getStringParameter(1);
                    String name = cmd.getStringParameter(2);
                    int openingYear = cmd.getIntegerParameter(1);
                    int spectatorCount = cmd.getIntegerParameter(2);

                    IocCode code = iocContainer.findIocCodeByCountry(countryName);
                    if (code == null) {
                        outputInterface.printError("unknown country");
                        return;
                    }

                    if (!venueContainer.addVenue(id, code, locus, name, openingYear, spectatorCount)) {
                        outputInterface.printError(venueContainer.getErrorString());
                    } else {
                        ok();
                    }
                }, //add-sports-venue

                (c, cmd) -> {
                    if (!checkLogin()) {
                        return;
                    }
                    String countryName = cmd.getStringParameter(0);
                    listSportsVenues(countryName);
                }, //list-sports-venues

                (c, cmd) -> {
                    if (!checkLogin()) {
                        return;
                    }
                    String sport = cmd.getStringParameter(0);
                    String discipline = cmd.getStringParameter(1);
                    if (!sportContainer.addSport(sport)) {
                        outputInterface.printError(sportContainer.getErrorString());
                    } else if (!sportContainer.addDisciplineToSport(sport, discipline)) {
                        outputInterface.printError(sportContainer.getErrorString());
                    } else {
                        ok();
                    }
                }, //add-olympic-sport

                (c, cmd) -> {
                    if (!checkLogin()) {
                        return;
                    }
                    listOlympicSports();
                }, //list-olympic-sports

                (c, cmd) -> {
                    if (!checkLogin()) {
                        return;
                    }
                    int id = cmd.getIntegerParameter(0);
                    String code = cmd.getStringParameter(0);
                    String countryName = cmd.getStringParameter(1);
                    int year = cmd.getIntegerParameter(1);

                    if (!iocContainer.addIocCode(id, code, countryName, year)) {
                        outputInterface.printError(iocContainer.getErrorString());
                    } else {
                        ok();
                    }
                }, //add-ioc-code

                (c, cmd) -> {
                    if (!checkLogin()) {
                        return;
                    }
                    listIocCodes();
                }, //list-ioc-codes

                (c, cmd) -> {
                    if (!checkLogin()) {
                        return;
                    }
                    int id = cmd.getIntegerParameter(0);
                    String firstName = cmd.getStringParameter(0);
                    String lastName = cmd.getStringParameter(1);
                    String countryName = cmd.getStringParameter(2);
                    String sportstr = cmd.getStringParameter(3);
                    String disciplinestr = cmd.getStringParameter(4);

                    IocCode code = iocContainer.findIocCodeByCountry(countryName);
                    if (code == null) {
                        outputInterface.printError("unknown country");
                        return;
                    }

                    Sport sport = sportContainer.findSportByName(sportstr);
                    if (sport == null) {
                        outputInterface.printError("unknown sport");
                        return;
                    }

                    Discipline discipline = sport.findDisciplineByName(disciplinestr);
                    if (discipline == null) {
                        outputInterface.printError("unknown discipline");
                        return;
                    }

                    if (!athleteContainer.addAthlete(id, firstName, lastName, code, sport, discipline)) {
                        outputInterface.printError(athleteContainer.getErrorString());
                    } else {
                        ok();
                    }
                }, //add-athlete

                (c, cmd) -> {
                    if (!checkLogin()) {
                        return;
                    }
                    String sport = cmd.getStringParameter(0);
                    String discipline = cmd.getStringParameter(1);

                    summaryAthletes(sport, discipline);
                }, //summary-athletes

                (c, cmd) -> {
                    if (!checkLogin()) {
                        return;
                    }
                    int id = cmd.getIntegerParameter(0);
                    int year = cmd.getIntegerParameter(1);
                    String countryName = cmd.getStringParameter(0);
                    String sportstr = cmd.getStringParameter(1);
                    String disciplinestr = cmd.getStringParameter(2);
                    int gold = cmd.getIntegerParameter(2);
                    int silver = cmd.getIntegerParameter(3);
                    int bronze = cmd.getIntegerParameter(4);

                    IocCode code = iocContainer.findIocCodeByCountry(countryName);
                    if (code == null) {
                        outputInterface.printError("unknown country");
                        return;
                    }

                    Sport sport = sportContainer.findSportByName(sportstr);
                    if (sport == null) {
                        outputInterface.printError("unknown sport");
                        return;
                    }

                    Discipline discipline = sport.findDisciplineByName(disciplinestr);
                    if (discipline == null) {
                        outputInterface.printError("unknown discipline");
                        return;
                    }

                    if (!competitionContainer.addCompetition(id, year, code, sport, discipline, new MedalTable(gold, silver, bronze))) {
                        outputInterface.printError(competitionContainer.getErrorString());
                    } else {
                        ok();
                    }
                }, //add-competition

                (c, cmd) -> {
                    if (!checkLogin()) {
                        return;
                    }
                    olympicMedalTable();
                }, //olympic-medal-table

                (c, cmd) -> {
                    reset();
                }, //reset

                (c, cmd) -> {
                    c.stop();
                }  //quit
        };

        for (int i = 0; i < cmdStrings.length; i++) {
            controller.attachActionToCommand(new CommandPrototype(cmdStrings[i], layouts[i]), actions[i]);
        }

    }

    public void start() {
        controller.start();
    }

    private void listSportsVenues(String countryName) {
        if (iocContainer.findIocCodeByCountry(countryName) == null) {
            outputInterface.printError("unknown country");
            return;
        }

        for (Venue venue : venueContainer.getVenues()) {
            if (venue.getIocCode().getCountryName().equals(countryName)) {
                outputInterface.printLine(venue);
            }
        }
    }

    private void listOlympicSports() {
        outputInterface.printLine(sportContainer);
    }

    private void listIocCodes() {
        outputInterface.printLine(iocContainer);
    }

    private void summaryAthletes(String sport, String discipline) {
        ArrayList<Athlete> athletes = new ArrayList<>();
        for (Athlete athlete : athleteContainer.getAthletes()) {
            if (athlete.getSport().getName().equals(sport)) {
                if (athlete.getSport().findDisciplineByName(discipline) != null) {
                    athletes.add(athlete);
                }
            }
        }
        athletes.sort(new AthleteComparator());

        for (Athlete athlete : athletes) {
            outputInterface.printLine(athlete);
        }
    }

    private void olympicMedalTable() {

    }

    private void reset() {
        if (adminContainer.isLoggedIn()) {
            adminContainer.logoutAdmin();
        }
        adminContainer.clear();
        athleteContainer.clear();
        iocContainer.clear();
        venueContainer.clear();
        competitionContainer.clear();
        sportContainer.clear();
    }

    private void ok() {
        outputInterface.printLine("OK");
    }

    private boolean checkLogin() {
        if (adminContainer.isLoggedIn()) {
            return true;
        }
        outputInterface.printError("not logged in");
        return false;
    }
}
