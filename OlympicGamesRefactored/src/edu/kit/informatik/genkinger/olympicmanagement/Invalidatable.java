package edu.kit.informatik.genkinger.olympicmanagement;

/**
 * This class represents an object that can be invalidated.
 * @author Lukas Genkinger
 */
public abstract class Invalidatable {
    private String reason = null;
    private boolean invalid = false;

    /**
     * Invalidates the object for the given <code>reason</code>.
     *
     * @param reason the reason for the invalidation.
     */
    void invalidate(String reason) {
        this.reason = reason;
        this.invalid = true;
    }

    /**
     * Returns the reason for the invalidation.
     *
     * @return the reason for the invalidation.
     */
    String getReason() {
        return reason;
    }

    /**
     * Returns <code>true</code> if the object is invalid.
     * <code>false</code> otherwise.
     *
     * @return <code>true</code> if the object is invalid.
     * <code>false</code> otherwise.
     */
    boolean isInvalid() {
        return invalid;
    }
}
