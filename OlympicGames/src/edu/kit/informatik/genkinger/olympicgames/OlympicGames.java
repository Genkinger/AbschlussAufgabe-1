package edu.kit.informatik.genkinger.olympicgames;

import edu.kit.informatik.genkinger.controller.*;
import edu.kit.informatik.genkinger.olympicgames.containers.*;

import java.util.ArrayList;

public class OlympicGames {


    AdminContainer adminContainer = new AdminContainer();
    AthleteContainer athleteContainer = new AthleteContainer();
    VenueContainer venueContainer = new VenueContainer();
    SportContainer sportContainer = new SportContainer();
    IocContainer iocContainer = new IocContainer();
    CompetitionContainer competitionContainer = new CompetitionContainer();

    private StringInputController controller;
    private StringOutputInterface outputInterface;


    private CommandLayout layoutEmpty = new CommandLayout();
    private CommandLayout layoutTwoStrings = new CommandLayout().
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING);

    private CommandLayout layoutAddAdmin = new CommandLayout().
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING);

    private CommandLayout layoutAddSportsVenue = new CommandLayout().
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.INT);

    private CommandLayout layoutListSportsVenues = new CommandLayout().
            addParameter(CommandParameterType.STRING);

    private CommandLayout layoutAddIocCode = new CommandLayout().
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING);

    private CommandLayout layoutAddAthlete = new CommandLayout().
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING);

    private CommandLayout layoutAddCompetition = new CommandLayout().
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.STRING).
            addParameter(CommandParameterType.INT).
            addParameter(CommandParameterType.INT).
            addParameter(CommandParameterType.INT);

    private CommandLayout[] layouts = {
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

    private String[] commandStrings = {
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

    private Action[] actions = {
            (c, cmd) -> {
                if (adminContainer.isLoggedIn()) {
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

                String id = cmd.getStringParameter(0);
                String countryName = cmd.getStringParameter(1);
                String locus = cmd.getStringParameter(2);
                String name = cmd.getStringParameter(3);
                String openingYear = cmd.getStringParameter(4);
                int spectatorCount = cmd.getIntegerParameter(0);

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

                sportContainer.addSport(sport);

                if (!sportContainer.addDisciplineToSport(sport, discipline)) {
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
                String id = cmd.getStringParameter(0);
                String code = cmd.getStringParameter(1);
                String countryName = cmd.getStringParameter(2);
                String year = cmd.getStringParameter(3);

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
                String id = cmd.getStringParameter(0);
                String firstName = cmd.getStringParameter(1);
                String lastName = cmd.getStringParameter(2);
                String countryName = cmd.getStringParameter(3);
                String sportstr = cmd.getStringParameter(4);
                String disciplinestr = cmd.getStringParameter(5);

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
                String athleteId = cmd.getStringParameter(0);
                String year = cmd.getStringParameter(1);
                String countryName = cmd.getStringParameter(2);
                String sportstr = cmd.getStringParameter(3);
                String disciplinestr = cmd.getStringParameter(4);
                int gold = cmd.getIntegerParameter(0);
                int silver = cmd.getIntegerParameter(1);
                int bronze = cmd.getIntegerParameter(2);

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

                Athlete athlete = athleteContainer.findAthleteById(athleteId);
                if (athlete == null) {
                    outputInterface.printError("unknown athlete");
                    return;
                }

                if (!competitionContainer.addCompetition(athlete, year, code, sport, discipline,
                        new Medals(gold, silver, bronze))) {
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
                ok();
            }, //reset

            (c, cmd) -> {
                c.stop();
            }  //quit
    };


    OlympicGames(StringInputInterface inputInterface, StringOutputInterface outputInterface) {
        this.outputInterface = outputInterface;
        this.controller = new StringInputController(inputInterface);

        registerCommands();
    }

    private void registerCommands() {

        for (int i = 0; i < commandStrings.length; i++) {
            controller.attachActionToCommand(new CommandPrototype(commandStrings[i], layouts[i]), actions[i]);
        }

        controller.attachDefaultAction((controller, cmd) -> outputInterface.printError(cmd.getReasonForInvalidation()));
    }

    public void start() {
        controller.start();
    }

    private void listSportsVenues(String countryName) {
        if (iocContainer.findIocCodeByCountry(countryName) == null) {
            outputInterface.printError("unknown country");
            return;
        }

        outputInterface.printLine(venueContainer.getVenuesByCountryAsString(countryName));

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
