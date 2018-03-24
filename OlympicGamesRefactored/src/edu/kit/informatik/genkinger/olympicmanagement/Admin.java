package edu.kit.informatik.genkinger.olympicmanagement;

/**
 * This class represents a user that has permission to alter entries in the {@link OlympicManagement} system.
 *
 * @author Lukas Genkinger
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
    Admin(String firstName, String lastName, String username, String password) {
        super(firstName, lastName);

        if (!username.matches(".{4,8}")) {
            invalidate("username must be between 4 and 8 characters in length");
        }
        if (!password.matches(".{8,12}")) {
            invalidate("password must be between 8 and 12 characters in length");
        }

        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username
     *
     * @return the username
     */
    String getUsername() {
        return username;
    }

    /**
     * Returns the password
     *
     * @return the password
     */
    String getPassword() {
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
