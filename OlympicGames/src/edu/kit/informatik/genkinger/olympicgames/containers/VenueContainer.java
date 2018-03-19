package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.Clearable;
import edu.kit.informatik.genkinger.olympicgames.IocCode;
import edu.kit.informatik.genkinger.olympicgames.Venue;
import edu.kit.informatik.genkinger.olympicgames.VenueComparator;

import java.util.ArrayList;

public class VenueContainer extends Container implements Clearable {
    private ArrayList<Venue> venues = new ArrayList<>();


    public boolean addVenue(int id, IocCode code, String locus, String name, int openingYear, int spectatorCount) {

        Venue venue = findVenueByName(name);
        if (venue != null) {
            if (venue.getIocCode().equals(code) && venue.getLocus().equals(locus)) {
                setErrorString("venue with that name already exists in " + locus + ", " + code.getCountryName());
                return false;
            }
        }

        venues.add(new Venue(id, code, locus, name, openingYear, spectatorCount));
        venues.sort(new VenueComparator());

        return true;
    }

    Venue findVenueByName(String name) {
        for (Venue venue : venues) {
            if (venue.getName().equals(name)) {
                return venue;
            }
        }
        return null;
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    @Override
    public void clear() {
        venues.clear();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int index = 1;
        for (Venue venue : venues) {
            builder.append("(");
            builder.append(index);
            builder.append(" ");
            builder.append(venue);
            builder.append(")");
            index++;
        }
        return builder.toString();
    }
}
