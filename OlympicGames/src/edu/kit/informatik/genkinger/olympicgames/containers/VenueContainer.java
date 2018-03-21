package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.Clearable;
import edu.kit.informatik.genkinger.olympicgames.IocCode;
import edu.kit.informatik.genkinger.olympicgames.Venue;

import java.util.ArrayList;

public class VenueContainer extends Container implements Clearable {

    private IocContainer iocContainer;
    private ArrayList<Venue> venues = new ArrayList<>();

    public VenueContainer(IocContainer iocContainer) {
        this.iocContainer = iocContainer;
    }

    public boolean addVenue(String id, String country, String locus, String name, String openingYear, int spectatorCount) {

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
