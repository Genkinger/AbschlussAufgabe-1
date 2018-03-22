package edu.kit.informatik.genkinger.olympicgames.containers;


import edu.kit.informatik.genkinger.olympicgames.Admin;
import edu.kit.informatik.genkinger.olympicgames.Clearable;

import java.util.ArrayList;

/**
 *
 */
public class AdminContainer extends Container implements Clearable {
    private ArrayList<Admin> admins = new ArrayList<>();
    private boolean loggedIn = false;

    /**
     *
     * @param firstName .
     * @param lastName .
     * @param username .
     * @param password .
     * @return .
     */
    public boolean addAdmin(String firstName, String lastName, String username, String password) {
        if (findByUsername(username) != null) {
            setErrorString("someone with this username already exists");
            return false;
        }

        if (!username.matches(".{4,8}")) {
            setErrorString("username has to be between 4 and 8 characters in length");
            return false;
        }

        if (!password.matches(".{8,12}")) {
            setErrorString("password has to be between 8 and 12 characters in length");
            return false;
        }

        Admin admin = new Admin(firstName, lastName, username, password);
        admins.add(admin);

        return true;
    }

    /**
     *
     * @param username .
     * @param password .
     * @return .
     */
    public boolean loginAdmin(String username, String password) {
        Admin admin = findByUsername(username);
        if (admin == null) {
            setErrorString("invalid username");
            return false;
        }

        if (!admin.getPassword().equals(password)) {
            setErrorString("invalid password");
            return false;
        }

        if (loggedIn) {
            setErrorString("already logged in");
            return false;
        }

        loggedIn = true;
        return true;
    }

    /**
     *
     * @return .
     */
    public boolean logoutAdmin() {

        if (!loggedIn) {
            setErrorString("not logged in");
            return false;
        }

        loggedIn = false;
        return true;
    }

    /**
     *
     * @return .
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     *
     * @param username .
     * @return .
     */
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
