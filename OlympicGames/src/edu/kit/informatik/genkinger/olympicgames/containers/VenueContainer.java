package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.Clearable;
import edu.kit.informatik.genkinger.olympicgames.IocCode;
import edu.kit.informatik.genkinger.olympicgames.Venue;
import edu.kit.informatik.genkinger.olympicgames.comparators.VenueComparator;

import java.util.ArrayList;

/**
 * This class is a {@link Container} of {@link Venue} objects.
 */
public class VenueContainer extends Container implements Clearable {

    private IocContainer iocContainer;
    private ArrayList<Venue> venues = new ArrayList<>();

    /**
     * Constructs a new {@link VenueContainer}.
     *
     * @param iocContainer the {@link IocContainer}.
     */
    public VenueContainer(IocContainer iocContainer) {
        this.iocContainer = iocContainer;
    }

    /**
     * Adds a new {@link Venue} to the {@link Container}.
     *
     * @param id             the id of the {@link Venue}.
     * @param country        the name of the country the {@link Venue} is located in.
     * @param locus          the name of the locus the {@link Venue} is located in.
     * @param name           the name of the {@link Venue}.
     * @param openingYear    the opening year of the {@link Venue}.
     * @param spectatorCount the maximum number of spectators the {@link Venue} can hold.
     * @return .
     */
    public boolean addVenue(String id, String country,
                            String locus, String name,
                            String openingYear, int spectatorCount) {

        if (!id.matches("[0-9]{3}") || id.equals("000")) {
            setErrorString("invalid id");
            return false;
        }

        if (!openingYear.matches("[0-9]{4}")) {
            setErrorString("invalid year");
            return false;
        }

        IocCode iocCode = iocContainer.findIocCodeByCountry(country);
        if (iocCode == null) {
            setErrorString("invalid country");
            return false;
        }

        if (spectatorCount <= 0) {
            setErrorString("invalid spectator count");
            return false;
        }

        ArrayList<Venue> venuesByName = findVenuesByName(name);
        for (Venue venue : venuesByName) {
            if (venue.getLocus().equals(locus) && venue.getIocCode().equals(iocCode)) {
                setErrorString("venue with that name already exists for " + locus + ", " + iocCode.getCountryName());
                return false;
            }
        }

        for (Venue venue : venues) {
            if (venue.getId().equals(id)) {
                setErrorString("venue with that id already exists");
            }
        }

        venues.add(new Venue(id, iocCode, locus, name, openingYear, spectatorCount));
        venues.sort(new VenueComparator());
        return true;
    }

    private ArrayList<Venue> findVenuesByName(String name) {
        ArrayList<Venue> vens = new ArrayList<>();

        for (Venue venue : venues) {
            if (venue.getName().equals(name)) {
                vens.add(venue);
            }
        }

        return vens;
    }

    /**
     * returns an {@link ArrayList} containing the Venues located in the specified country.
     *
     * @param country the name of the country
     * @return an {@link ArrayList} containing the Venues located in the specified country.
     */
    public ArrayList<Venue> findVenuesByCountry(String country) {
        if (iocContainer.findIocCodeByCountry(country) == null) {
            setErrorString("invalid country");
            return null;
        }

        ArrayList<Venue> vens = new ArrayList<>();
        for (Venue venue : venues) {
            if (venue.getIocCode().getCountryName().equals(country)) {
                vens.add(venue);
            }
        }
        return vens;
    }

    @Override
    public void clear() {
        venues.clear();
    }
}
