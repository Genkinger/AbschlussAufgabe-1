package edu.kit.informatik.genkinger.olympicgames;

/**
 *  This class represents a user that has permission to alter entries in the {@link OlympicManagement} system.
 *
 */
public class Admin extends Person {

    private String username;
    private String password;

    /**
     * Constructs a new {@link Admin} object.
     *
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param username  the username of the user
     * @param password  the password of the user
     */
    public Admin(String firstName, String lastName, String username, String password) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Admin) {
            Admin a = (Admin) obj;
            return a.getUsername().equals(username);
        }

        return false;
    }
}
