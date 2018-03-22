package edu.kit.informatik.genkinger.olympicgames;

/**
 *
 */
public class Admin extends Person {

    private String username;
    private String password;

    /**
     *
     * @param firstName .
     * @param lastName .
     * @param username .
     * @param password .
     */
    public Admin(String firstName, String lastName, String username, String password) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @return .
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return .
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
