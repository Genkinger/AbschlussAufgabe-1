package edu.kit.informatik.genkinger.olympicgames;

import edu.kit.informatik.genkinger.olympicgames.comparators.DisciplineComparator;
import edu.kit.informatik.genkinger.olympicgames.containers.Container;

import java.util.ArrayList;

public class Sport extends Container {

    private String name;
    private ArrayList<Discipline> disciplines = new ArrayList<>();


    public Sport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addDiscipline(String discipline) {
        if (findDisciplineByName(discipline) != null) {
            setErrorString("discipline already exists for " + name);
            return false;
        }

        disciplines.add(new Discipline(discipline));
        disciplines.sort(new DisciplineComparator());

        return true;
    }

    public Discipline findDisciplineByName(String name) {
        for (Discipline discipline : disciplines) {
            if (discipline.getName().equals(name)) {
                return discipline;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sport) {
            Sport sport = (Sport) obj;
            return name.equals(sport.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Discipline discipline : disciplines) {
            builder.append(name);
            builder.append(" ");
            builder.append(discipline);
            builder.append("\n");
        }
        return builder.toString().trim();
    }
}
