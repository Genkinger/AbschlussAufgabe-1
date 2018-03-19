package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.Utils;
import edu.kit.informatik.genkinger.olympicgames.Admin;
import edu.kit.informatik.genkinger.olympicgames.Clearable;

import java.util.ArrayList;

public class AdminContainer extends Container implements Clearable {
    private ArrayList<Admin> admins = new ArrayList<>();
    private boolean loggedIn = false;


    public boolean addAdmin(String firstName, String lastName, String username, String password) {
        if (!Utils.inRange(username.length(), 4, 8)) {
            setErrorString("username has to be between 4 and 8 characters in length");
            return false;
        }
        if (!Utils.inRange(password.length(), 8, 12)) {
            setErrorString("password has to be between 8 and 12 characters in length");
            return false;
        }
        if (findByUsername(username) != null) {
            setErrorString("someone with this username already exists");
            return false;
        }

        Admin admin = new Admin(firstName, lastName, username, password);
        admins.add(admin);

        return true;
    }

    public boolean loginAdmin(String username, String password) {
        Admin admin = findByUsername(username);
        if (admin == null) {
            setErrorString("no such user exists");
            return false;
        }
        if (!admin.getPassword().equals(password)) {
            setErrorString("wrong password");
            return false;
        }

        loggedIn = true;

        return true;
    }

    public boolean logoutAdmin() {

        if (!loggedIn) {
            setErrorString("not logged in");
            return false;
        }

        loggedIn = false;
        return true;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    Admin findByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        admins.clear();
    }
}
