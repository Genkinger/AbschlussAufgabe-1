package edu.kit.informatik.genkinger.olympicmanagement;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class is used as a base for other Container classes that need similar functionality.
 * (error strings)
 */
public class Container<T> extends Invalidatable {
    private ArrayList<T> list = new ArrayList<>();
    private Comparator comparator = null;

    /**
     * Constructs a new {@link Container} with a comparator that is used to sort the internal collection.
     *
     * @param comparator the comparator to use.
     */
    Container(Comparator comparator) {
        this.comparator = comparator;
    }

    /**
     * Adds an element to the collection.
     *
     * @param element the element to add.
     */
    void add(T element) {
        list.add(element);
        sort();
    }

    /**
     * Clears the collection.
     */
    void clear() {
        list.clear();
    }

    /**
     * Returns an object matching the specified one if found in the collection.
     * <code>null</code> otherwise.
     *
     * @param e the object to search for.
     * @return an object matching the specified one if found in the collection.
     * <code>null</code> otherwise.
     */
    T find(T e) {
        for (T elem : list) {
            if (e.equals(elem)) {
                return elem;
            }
        }
        return null;
    }

    /**
     * sorts the collection.
     */
    void sort() {
        // checking for the type would be helpful but is not needed in this case...
        if (comparator != null) {
            list.sort(comparator);
        }
    }

    /**
     * Returns the internal collection.
     *
     * @return the internal collection.
     */
    ArrayList<T> getList() {
        return list;
    }
}
