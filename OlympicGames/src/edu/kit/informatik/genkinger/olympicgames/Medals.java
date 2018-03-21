package edu.kit.informatik.genkinger.olympicgames;

public class Medals {

    private int gold;
    private int silver;
    private int bronze;

    public Medals(int gold, int silver, int bronze) {
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

    public int getGold() {
        return gold;
    }

    public int getSilver() {
        return silver;
    }

    public int getBronze() {
        return bronze;
    }

}
