package edu.kit.informatik.genkinger.olympicmanagement;

import edu.kit.informatik.genkinger.controller.*;
import edu.kit.informatik.genkinger.olympicmanagement.comparators.*;

/**
 * This class represents the {@link OlympicManagement} system.
 * It uses a {@link StringInputController} to get user input and executes methods accordingly.
 *
 * @author Lukas Genkinger
 * @see StringInputController
 * @see StringInputInterface
 * @see StringOutputInterface
 */
class OlympicManagement {

    private Container<Admin> adminContainer = new Container<>(null);
    private Container<Athlete> athleteContainer = new Container<>(new AthleteComparator());
    private Container<IocCode> iocContainer = new Container<>(new IocCodeComparator());
    private Container<Discipline> disciplineContainer = new Container<>(new DisciplineComparator());
    private Container<Venue> venueContainer = new Container<>(new VenueComparator());
    private Container<Competition> competitionContainer = new Container<>(null);
    private Container<CountrySummary> summaryContainer = new Container<>(new SummaryComparator());

    private boolean loggedIn = false;

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
                addAdmin(cmd);
            }, //add-admin
            (c, cmd) -> {
                loginAdmin(cmd);
            }, //login-admin
            (c, cmd) -> {
                logoutAdmin();
            }, //logout-admin
            (c, cmd) -> {
                addSportsVenue(cmd);
            }, //add-sports-venue
            (c, cmd) -> {
                listSportsVenues(cmd);
            }, //list-sports-venues
            (c, cmd) -> {
                addOlympicSport(cmd);
            }, //add-olympic-sport
            (c, cmd) -> {
                listOlympicSports();
            }, //list-olympic-sports
            (c, cmd) -> {
                addIocCode(cmd);
            }, //add-ioc-code
            (c, cmd) -> {
                listIocCodes();
            }, //list-ioc-codes
            (c, cmd) -> {
                addAthlete(cmd);

            }, //add-athlete
            (c, cmd) -> {
                summaryAthletes(cmd);
            }, //summary-athletes
            (c, cmd) -> {
                addCompetition(cmd);

            }, //add-competition
            (c, cmd) -> {
                olympicMedalTable();
            }, //olympic-medal-table
            (c, cmd) -> {
                reset();
            }, //reset
            (c, cmd) -> {
                c.stop();
            }  //quit
    };

    /**
     * Constructs a new {@link OlympicManagement} object.
     *
     * @param inputInterface  the {@link StringInputInterface} the {@link StringInputController} uses.
     * @param outputInterface the {@link StringOutputInterface} used to output info to the user.
     */
    OlympicManagement(StringInputInterface inputInterface, StringOutputInterface outputInterface) {
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

    /**
     * Starts the associated {@link StringInputController}.
     */
    void start() {
        controller.start();
    }

    private void addAdmin(Command cmd) {
        if (loggedIn) {
            outputInterface.printError("can't execute this command while logged in");
            return;
        }
        String firstName = cmd.getStringParameter(0);
        String lastName = cmd.getStringParameter(1);
        String username = cmd.getStringParameter(2);
        String password = cmd.getStringParameter(3);

        Admin admin = new Admin(firstName, lastName, username, password);
        if (admin.isInvalid()) {
            outputInterface.printError(admin.getReason());
            return;
        }
        if (adminContainer.find(admin) != null) {
            outputInterface.printError("admin with that username already exists");
            return;
        }
        adminContainer.add(admin);
        ok();
    }

    private void loginAdmin(Command cmd) {
        if (loggedIn) {
            outputInterface.printError("already logged in");
            return;
        }
        String username = cmd.getStringParameter(0);
        String password = cmd.getStringParameter(1);
        Admin admin = adminContainer.find(new Admin("", "", username, password));
        if (admin == null) {
            outputInterface.printError("invalid username");
            return;
        }
        if (!admin.getPassword().equals(password)) {
            outputInterface.printError("invalid password");
            return;
        }
        loggedIn = true;
        ok();
    }

    private void logoutAdmin() {
        if (!checkLogin()) {
            return;
        }
        loggedIn = false;
        ok();
    }

    private void addSportsVenue(Command cmd) {
        String id = cmd.getStringParameter(0);
        String country = cmd.getStringParameter(1);
        String locus = cmd.getStringParameter(2);
        String name = cmd.getStringParameter(3);
        String year = cmd.getStringParameter(4);
        int spectators = cmd.getIntegerParameter(0);

        IocCode code = iocContainer.find(new IocCode("", "", country, ""));
        if (code == null) {
            outputInterface.printError("invalid country");
            return;
        }

        Venue venue = new Venue(id, code, locus, name, year, spectators);
        if (venue.isInvalid()) {
            outputInterface.printError(venue.getReason());
            return;
        }
        if (venueContainer.find(venue) != null) {
            outputInterface.printError("venue already exists");
            return;
        }

        venueContainer.add(venue);
        ok();

    }

    private void addOlympicSport(Command cmd) {
        String sport = cmd.getStringParameter(0);
        String discipline = cmd.getStringParameter(1);

        Discipline dis = new Discipline(sport, discipline);

        if (disciplineContainer.find(dis) != null) {
            outputInterface.printError("discipline already exists");
            return;
        }

        disciplineContainer.add(dis);
        ok();
    }

    private void addIocCode(Command cmd) {
        String id = cmd.getStringParameter(0);
        String code = cmd.getStringParameter(1);
        String country = cmd.getStringParameter(2);
        String year = cmd.getStringParameter(3);

        IocCode iocCode = new IocCode(id, code, country, year);
        if (iocCode.isInvalid()) {
            outputInterface.printError(iocCode.getReason());
            return;
        }

        IocCode found = iocContainer.find(iocCode);
        if (found != null) {
            outputInterface.printError("ioc-code already exists");
            return;
        }

        iocContainer.add(iocCode);
        ok();
    }

    private void addAthlete(Command cmd) {
        String id = cmd.getStringParameter(0);
        String firstName = cmd.getStringParameter(1);
        String lastName = cmd.getStringParameter(2);
        String country = cmd.getStringParameter(3);
        String sport = cmd.getStringParameter(4);
        String discipline = cmd.getStringParameter(5);

        IocCode code = iocContainer.find(new IocCode("", "", country, ""));
        if (code == null) {
            outputInterface.printError("invalid country");
            return;
        }

        Discipline dis = disciplineContainer.find(new Discipline(sport, discipline));
        if (dis == null) {
            outputInterface.printError("invalid discipline");
            return;
        }

        Athlete athlete = new Athlete(id, firstName, lastName, code, dis);
        if (athlete.isInvalid()) {
            outputInterface.printError(athlete.getReason());
            return;
        }

        athleteContainer.add(athlete);
        ok();
    }

    private void addCompetition(Command cmd) {
        String id = cmd.getStringParameter(0);
        String year = cmd.getStringParameter(1);
        String country = cmd.getStringParameter(2);
        String sport = cmd.getStringParameter(3);
        String discipline = cmd.getStringParameter(4);
        int gold = cmd.getIntegerParameter(0);
        int silver = cmd.getIntegerParameter(1);
        int bronze = cmd.getIntegerParameter(2);

        Athlete athlete = athleteContainer.find(new Athlete(id, "", "", null, null));
        if (athlete == null) {
            outputInterface.printError("invalid athlete id");
            return;
        }
        Discipline dis = disciplineContainer.find(new Discipline(sport, discipline));
        if (dis == null) {
            outputInterface.printError("invalid discipline");
            return;
        }
        IocCode code = iocContainer.find(new IocCode("", "", country, ""));
        if (code == null) {
            outputInterface.printError("invalid country");
            return;
        }
        if (!athlete.getIocCode().equals(code)) {
            outputInterface.printError("athlete doesn't compete for the given country");
            return;
        }
        Medals medals = new Medals(gold, silver, bronze);
        if (medals.isInvalid()) {
            outputInterface.printError(medals.getReason());
            return;
        }
        Competition competition = new Competition(athlete, year, code, dis, medals);
        if (competition.isInvalid()) {
            outputInterface.printError(competition.getReason());
            return;
        }
        if (competitionContainer.find(competition) != null) {
            outputInterface.printError("competition already exists");
            return;
        }
        CountrySummary summary = summaryContainer.find(new CountrySummary(code, medals));
        if (summary == null) {
            summaryContainer.add(new CountrySummary(code, medals));
        } else {
            summary.getMedals().add(medals);
            summaryContainer.sort();
        }

        athlete.setMedalCount(athlete.getMedalCount() + medals.getTotal());
        competitionContainer.add(competition); //This is kind of useless...

        ok();
    }

    private void listSportsVenues(Command cmd) {
        String country = cmd.getStringParameter(0);
        IocCode code = iocContainer.find(new IocCode("", "", country, ""));
        if (code == null) {
            outputInterface.printError("invalid country");
            return;
        }

        outputInterface.printLine(stringify(venueContainer,
                (Venue v) -> v.getIocCode().getCountryName().equals(country), true, ' '));
    }

    private void listOlympicSports() {
        outputInterface.printLine(stringify(disciplineContainer, (Discipline d) -> true, false, ' '));
    }

    private void listIocCodes() {
        outputInterface.printLine(stringify(iocContainer, (IocCode code) -> true, false, ' '));
    }

    private void summaryAthletes(Command cmd) {
        String sport = cmd.getStringParameter(0);
        String discipline = cmd.getStringParameter(1);
        Discipline dis = disciplineContainer.find(new Discipline(sport, discipline));
        if (dis == null) {
            outputInterface.printError("invalid discipline");
            return;
        }
        outputInterface.printLine(stringify(athleteContainer,
                (Athlete athlete) -> athlete.getDiscipline().equals(dis), false, ' '));
    }

    private void olympicMedalTable() {
        outputInterface.printLine(stringify(summaryContainer, (CountrySummary summary) -> true, true, ','));
    }

    private void reset() {
        athleteContainer.clear();
        iocContainer.clear();
        venueContainer.clear();
        competitionContainer.clear();
        disciplineContainer.clear();
    }

    private void ok() {
        outputInterface.printLine("OK");
    }

    private boolean checkLogin() {
        if (loggedIn) {
            return true;
        } else {
            outputInterface.printError("not logged in");
            return false;
        }
    }

    private <T> String stringify(Container<T> container, Predicate<T> predicate, boolean indexed, char delim) {
        StringBuilder builder = new StringBuilder();
        if (indexed) {
            int index = 1;
            for (T e : container.getList()) {
                if (predicate.evaluate(e)) {
                    builder.append("(");
                    builder.append(index);
                    builder.append(delim);
                    builder.append(e);
                    builder.append(")");
                    builder.append("\n");
                    index++;
                }
            }
        } else {
            for (T e : container.getList()) {
                if (predicate.evaluate(e)) {
                    builder.append(e);
                    builder.append("\n");
                }
            }
        }
        return builder.toString().trim();
    }
}
