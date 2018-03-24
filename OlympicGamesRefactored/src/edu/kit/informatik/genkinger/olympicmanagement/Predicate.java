package edu.kit.informatik.genkinger.olympicmanagement;

/**
 * This interface is used as a predicate to {@link OlympicManagement#stringify(Container, Predicate, boolean, char)}
 * to determine if an element needs to be printed.
 *
 * @param <T> the type of element to be printed.
 * @author Lukas Genkinger
 */
public interface Predicate<T> {
    /**
     * This method is "implemented" for individual cases in the form of a lambda expression.
     * It determines if the given element <code>e</code> should be printed.
     *
     * @param e the element to be tested.
     * @return <code>true</code> if the element should be printed.
     * <code>false</code> otherwise.
     */
    boolean evaluate(T e);
}
