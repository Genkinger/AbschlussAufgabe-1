package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.Clearable;
import edu.kit.informatik.genkinger.olympicgames.IocCode;
import edu.kit.informatik.genkinger.olympicgames.Venue;

import java.util.ArrayList;

public class VenueContainer extends Container implements Clearable {

    private ArrayList<Venue> venues = new ArrayList<>();


    public boolean addVenue(String id, IocCode code, String locus, String name, String openingYear, int spectatorCount) {

        if (!id.matches("[0-9]{3}")) {
            setErrorString("invalid id");
            return false;
        }

        if (!openingYear.matches("[0-9]{4}")) {
            setErrorString("invalid year");
            return false;
        }

        ArrayList<Venue> venuesByName = findVenuesByName(name);
        for (Venue venue : venuesByName) {
            if (venue.getLocus().equals(locus) && venue.getIocCode().equals(code)) {
                setErrorString("venue with that name already exists for " + locus + ", " + code.getCountryName());
                return false;
            }
        }

        for (Venue venue : venues) {
            if (venue.getId().equals(id)) {
                setErrorString("venue with that id already exists");
            }
        }

        venues.add(new Venue(id, code, locus, name, openingYear, spectatorCount));

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

    public String getVenuesByCountryAsString(String country) {
        StringBuilder builder = new StringBuilder();

        ArrayList<Venue> vens = findVenuesByCountry(country);

        int index = 1;
        for (Venue venue : vens) {
            builder.append("(");
            builder.append(index);
            builder.append(" ");
            builder.append(venue);
            builder.append(")");
            builder.append("\n");
            index++;
        }
        return builder.toString().trim();
    }
}
