package edu.kit.informatik.genkinger.olympicmanagement;

/**
 * This class represents a {@link Person}.
 * @author Lukas Genkinger
 */
class Person extends Invalidatable {

    private String firstName;
    private String lastName;

    /**
     * Constructs a {@link Person} with the specified <code>firstName</code> and <code>lastName</code>.
     *
     * @param firstName the first name.
     * @param lastName  the last name.
     */
    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns the {@link Person}s first name.
     *
     * @return the first name
     */
    String getFirstName() {
        return firstName;
    }

    /**
     * Returns the {@link Person}s last name.
     *
     * @return the last name
     */
    String getLastName() {
        return lastName;
    }

}
