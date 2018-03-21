package edu.kit.informatik.genkinger.olympicgames;

public class Discipline {

    private String name;


    public Discipline(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Discipline) {
            Discipline discipline = (Discipline) obj;
            return discipline.getName().equals(name);
        }
        return false;
    }

    @Override
    public String toString() {
        return getName();
    }
}
