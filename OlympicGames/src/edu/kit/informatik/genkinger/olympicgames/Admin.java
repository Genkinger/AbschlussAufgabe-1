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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Admin) {
            Admin a = (Admin) obj;
            return a.getUsername().equals(this.username);
        }

        return false;
    }
}
