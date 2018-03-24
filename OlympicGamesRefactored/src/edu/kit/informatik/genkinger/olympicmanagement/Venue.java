package edu.kit.informatik.genkinger.olympicmanagement;

/**
 * This class represents a sports venue in a specified country represented by the {@link IocCode}.
 *
 * @author Lukas Genkinger
 */
public class Venue extends Invalidatable {

    private String id;
    private IocCode iocCode;
    private String locus;
    private String name;
    private String year;
    private int spectatorCount;


    /**
     * Constructs a new {@link Venue} object.
     *
     * @param id             the id of the {@link Venue}
     * @param iocCode        the {@link IocCode} of the country the {@link Venue} is located in.
     * @param locus          the locus of the {@link Venue}.
     * @param name           the name of the {@link Venue}.
     * @param year           the year the {@link Venue} was opened.
     * @param spectatorCount the maximum number of spectators the {@link Venue} can hold.
     */
    Venue(String id, IocCode iocCode, String locus, String name, String year, int spectatorCount) {
        if (!id.matches("[0-9]{3}") || id.equals("000")) {
            invalidate("invalid id");
        }

        if (!year.matches("[0-9]{4}")) {
            invalidate("invalid year");
        }

        if (spectatorCount <= 0) {
            invalidate("invalid spectator count");
        }

        this.id = id;
        this.iocCode = iocCode;
        this.locus = locus;
        this.name = name;
        this.year = year;
        this.spectatorCount = spectatorCount;
    }

    /**
     * Returns the id.
     *
     * @return the id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the {@link IocCode}.
     *
     * @return the {@link IocCode}.
     */
    IocCode getIocCode() {
        return iocCode;
    }

    /**
     * Returns the locus of the {@link Venue}.
     *
     * @return the locus of the {@link Venue}.
     */
    String getLocus() {
        return locus;
    }

    /**
     * Returns the name of the {@link Venue}.
     *
     * @return the name of the {@link Venue}.
     */
    String getName() {
        return name;
    }

    /**
     * Returns the spectator count.
     *
     * @return the spectator count.
     */
    public int getSpectatorCount() {
        return spectatorCount;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Venue) {
            Venue v = (Venue) obj;
            if (v.getId().equals(id)
                    || (v.getIocCode().equals(iocCode)
                    && v.getLocus().equals(locus)
                    && v.getName().equals(name))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return id + " " + locus + " " + spectatorCount;
    }


}

