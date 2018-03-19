package edu.kit.informatik.genkinger.olympicgames;

public class Admin extends Person {

    public String username;
    private String password;

    public Admin(String firstName, String lastName, String username, String password) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

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
