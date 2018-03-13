package edu.kit.informatik.genkinger.olympicgames;

import edu.kit.informatik.Utils;
import edu.kit.informatik.genkinger.controller.*;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;

public class OlympicGames {

    private List<Admin> admins = new ArrayList<>();

    private List<IocCode> iocCodes = new ArrayList<>();
    private List<Country> countries = new ArrayList<>();
    private List<Athlete> athletes = new ArrayList<>();
    private List<SportVenue> sportVenues = new ArrayList<>();
    private List<Sport> sports = new ArrayList<>();

    private boolean adminLoggedIn = false;


    private StringInputController controller;
    private StringInputInterface inputInterface;
    private StringOutputInterface outputInterface;


    OlympicGames(StringInputInterface inputInterface, StringOutputInterface outputInterface) {
        this.inputInterface = inputInterface;
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
                layoutEmpty,
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
                    String first = cmd.getStringParameter(0);
                    String last = cmd.getStringParameter(1);
                    String uname = cmd.getStringParameter(2);
                    String pwd = cmd.getStringParameter(3);

                    addAdmin(first, last, uname, pwd);
                }, //add-admin
                (c, cmd) -> {
                    String uname = cmd.getStringParameter(0);
                    String pwd = cmd.getStringParameter(1);
                    loginAdmin(uname, pwd);
                }, //login-admin

                (c, cmd) -> {
                    logoutAdmin();
                }, //logout-admin

                (c, cmd) -> {
                }, //add-sports-venue

                (c, cmd) -> {
                    listSportsVenues();
                }, //list-sports-venues

                (c, cmd) -> {
                }, //add-olympic-sport

                (c, cmd) -> {
                    listOlympicSports();
                }, //list-olympic-sports

                (c, cmd) -> {
                }, //add-ioc-code

                (c, cmd) -> {
                    listIocCodes();
                }, //list-ioc-codes

                (c, cmd) -> {
                }, //add-athlete

                (c, cmd) -> {
                }, //summary-athletes

                (c, cmd) -> {
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

        for (int i = 0; i < cmdStrings.length; i++) {
            controller.attachActionToCommand(new CommandPrototype(cmdStrings[i], layouts[i]), actions[i]);
        }

    }

    public void start() {
        controller.start();
    }

    private void addAdmin(String firstName, String lastName, String username, String password) {
        if (!Utils.inRange(username.length(), 4, 8)) {
            outputInterface.printError("username has to be between 4 and 8 characters in length");
            return;
        }

        if (!Utils.inRange(password.length(), 8, 12)) {
            outputInterface.printError("password has to be between 8 and 12 characters in length");
            return;
        }

        Admin admin = new Admin(firstName, lastName, username, password);

        if (admins.contains(admin)) {
            outputInterface.printError("someone with this username already exists");
            return;
        }

        admins.add(admin);
        ok();

    }

    private void loginAdmin(String username, String password) {
        if (adminLoggedIn) {
            outputInterface.printError("already logged in");
            return;
        }
        Admin admin = findAdmin(username);
        if (admin == null) {
            outputInterface.printError("no such user exists");
            return;
        }

        if (!admin.getPassword().equals(password)) {
            outputInterface.printError("invalid password");
            return;
        }

        adminLoggedIn = true;
        ok();
    }

    private void logoutAdmin() {
        if (!adminLoggedIn) {
            outputInterface.printError("not logged in");
            return;
        }

        adminLoggedIn = false;
        ok();
    }

    private void addSportsVenue(int id, String countryName, String locus, String name, int openingYear, int spectatorCount) {

    }

    private void listSportsVenues() {

    }

    private void addOlympicSport(String sport, String discipline) {

    }

    private void listOlympicSports() {

    }

    private void addIocCode(int id, String code, String countryName, int year) {

    }

    private void listIocCodes() {

    }

    private void addAthlete(int id, String firstName, String lastName, String countryName, String sport, String discipline) {

    }

    private void summaryAthletes(String sport, String discipline) {

    }

    private void addCompetition(int id, int year, String countryName, String sport, String discipline, int gold, int silver, int bronze) {

    }

    private void olympicMedalTable() {

    }

    private void reset() {

    }

    private void ok() {
        outputInterface.printLine("OK");
    }

    private Admin findAdmin(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;
    }
}
