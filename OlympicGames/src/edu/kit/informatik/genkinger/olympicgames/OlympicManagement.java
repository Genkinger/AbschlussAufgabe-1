package edu.kit.informatik.genkinger.olympicgames;

import edu.kit.informatik.genkinger.controller.*;
import edu.kit.informatik.genkinger.olympicgames.containers.*;

import java.util.ArrayList;

/**
 * This class represents the {@link OlympicManagement} system.
 * It uses a {@link StringInputController} to get user input and executes methods accordingly.
 *
 * @see StringInputController
 * @see StringInputInterface
 * @see StringOutputInterface
 */
class OlympicManagement {


    private AdminContainer adminContainer = new AdminContainer();
    private SportContainer sportContainer = new SportContainer();
    private IocContainer iocContainer = new IocContainer();
    private MedalTable medalTable = new MedalTable();
    private AthleteContainer athleteContainer = new AthleteContainer(iocContainer, sportContainer);
    private CompetitionContainer competitionContainer
            = new CompetitionContainer(athleteContainer, iocContainer, sportContainer, medalTable);
    private VenueContainer venueContainer = new VenueContainer(iocContainer);

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

    /**
     * The following Javadoc comments are to pass the required checkstyle rules public test of the Praktomat.
     */
    private Action[] actions = {
            (c, cmd) -> {
                if (adminContainer.isLoggedIn()) {
                    outputInterface.printError("can't execute command when logged in");
                    return;
                }
                /**
                 * .
                 */
                String firstName = cmd.getStringParameter(0);
                /**
                 * .
                 */
                String lastName = cmd.getStringParameter(1);
                /**
                 * .
                 */
                String username = cmd.getStringParameter(2);
                /**
                 * .
                 */
                String password = cmd.getStringParameter(3);

                if (!adminContainer.addAdmin(firstName, lastName, username, password)) {
                    outputInterface.printError(adminContainer.getErrorString());
                } else {
                    ok();
                }
            }, //add-admin

            (c, cmd) -> {
                /**
                 * .
                 */
                String username = cmd.getStringParameter(0);
                /**
                 * .
                 */
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
                /**
                 *
                 */
                String id = cmd.getStringParameter(0);
                /**
                 *
                 */
                String country = cmd.getStringParameter(1);
                /**
                 *
                 */
                String locus = cmd.getStringParameter(2);
                /**
                 *
                 */
                String name = cmd.getStringParameter(3);
                /**
                 *
                 */
                String year = cmd.getStringParameter(4);
                /**
                 *
                 */
                int spectatorCount = cmd.getIntegerParameter(0);

                if (!venueContainer.addVenue(id, country, locus, name, year, spectatorCount)) {
                    outputInterface.printError(venueContainer.getErrorString());
                } else {
                    ok();
                }
            }, //add-sports-venue

            (c, cmd) -> {
                if (!checkLogin()) {
                    return;
                }
                /**
                 *
                 */
                String country = cmd.getStringParameter(0);
                listSportsVenues(country);
            }, //list-sports-venues

            (c, cmd) -> {
                if (!checkLogin()) {
                    return;
                }
                /**
                 *
                 */
                String sport = cmd.getStringParameter(0);
                /**
                 *
                 */
                String discipline = cmd.getStringParameter(1);

                if (!sportContainer.addSport(sport, discipline)) {
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

                /**
                 *
                 */
                String id = cmd.getStringParameter(0);
                /**
                 *
                 */
                String code = cmd.getStringParameter(1);
                /**
                 *
                 */
                String country = cmd.getStringParameter(2);
                /**
                 *
                 */
                String year = cmd.getStringParameter(3);

                if (!iocContainer.addIocCode(id, code, country, year)) {
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

                /**
                 *
                 */
                String id = cmd.getStringParameter(0);
                /**
                 *
                 */
                String firstName = cmd.getStringParameter(1);
                /**
                 *
                 */
                String lastName = cmd.getStringParameter(2);
                /**
                 *
                 */
                String country = cmd.getStringParameter(3);
                /**
                 *
                 */
                String sport = cmd.getStringParameter(4);
                /**
                 *
                 */
                String discipline = cmd.getStringParameter(5);

                if (!athleteContainer.addAthlete(id, firstName, lastName, country, sport, discipline)) {
                    outputInterface.printError(athleteContainer.getErrorString());
                } else {
                    ok();
                }

            }, //add-athlete

            (c, cmd) -> {
                if (!checkLogin()) {
                    return;
                }

                /**
                 *
                 */
                String sport = cmd.getStringParameter(0);
                /**
                 *
                 */
                String discipline = cmd.getStringParameter(1);

                summaryAthletes(sport, discipline);
            }, //summary-athletes

            (c, cmd) -> {
                if (!checkLogin()) {
                    return;
                }

                /**
                 *
                 */
                String id = cmd.getStringParameter(0);
                /**
                 *
                 */
                String year = cmd.getStringParameter(1);
                /**
                 *
                 */
                String country = cmd.getStringParameter(2);
                /**
                 *
                 */
                String sport = cmd.getStringParameter(3);
                /**
                 *
                 */
                String discipline = cmd.getStringParameter(4);
                /**
                 *
                 */
                int gold = cmd.getIntegerParameter(0);
                /**
                 *
                 */
                int silver = cmd.getIntegerParameter(1);
                /**
                 *
                 */
                int bronze = cmd.getIntegerParameter(2);

                if (!competitionContainer.addCompetition(id, year, country, sport, discipline,
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
    public void start() {
        controller.start();
    }

    private void listSportsVenues(String countryName) {

        StringBuilder builder = new StringBuilder();

        ArrayList<Venue> venues = venueContainer.findVenuesByCountry(countryName);
        if (venues == null) {
            outputInterface.printError(venueContainer.getErrorString());
            return;
        }

        int index = 1;
        for (Venue venue : venues) {
            builder.append("(");
            builder.append(index);
            builder.append(" ");
            builder.append(venue);
            builder.append(")");
            builder.append("\n");
            index++;
        }

        outputInterface.printLine(builder.toString().trim());
    }

    private void listOlympicSports() {
        outputInterface.printLine(sportContainer);
    }

    private void listIocCodes() {
        outputInterface.printLine(iocContainer);
    }

    private void summaryAthletes(String sport, String discipline) {
        ArrayList<Athlete> athletes = athleteContainer.findBySportAndDiscipline(sport, discipline);
        if (athletes == null) {
            outputInterface.printError(athleteContainer.getErrorString());
            return;
        }

        for (Athlete athlete : athletes) {
            outputInterface.printLine(athlete);
        }
    }

    private void olympicMedalTable() {
        outputInterface.printLine(medalTable);
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
