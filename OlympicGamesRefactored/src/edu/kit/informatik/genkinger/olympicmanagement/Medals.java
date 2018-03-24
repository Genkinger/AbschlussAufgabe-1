package edu.kit.informatik.genkinger.olympicmanagement;

/**
 * This class represents a medal distribution.
 * It consists of the gold, silver and bronze medal count.
 */
public class Medals extends Invalidatable {

    private int gold;
    private int silver;
    private int bronze;

    /**
     * Constructs a new Medal distribution.
     * It can only be constructed in a valid manner if the total medal count is less or equal to <code>1</code>.
     *
     * @param gold   the count of gold medals.
     * @param silver the count of silver medals.
     * @param bronze the count of bronze medals.
     */
    Medals(int gold, int silver, int bronze) {
        if (gold + silver + bronze > 1 || gold < 0 || silver < 0 || bronze < 0) {
            invalidate("invalid medal count");
        }

        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

    /**
     * Adds another medal distribution to the current one.
     *
     * @param medals the medal distribution to add.
     */
    void add(Medals medals) {
        gold += medals.getGold();
        silver += medals.getSilver();
        bronze += medals.getBronze();
    }

    /**
     * Returns the gold medal count.
     *
     * @return the gold medal count.
     */
    public int getGold() {
        return gold;
    }

    /**
     * Returns the silver medal count.
     *
     * @return the silver medal count.
     */
    public int getSilver() {
        return silver;
    }

    /**
     * Returns the bronze medal count.
     *
     * @return the bronze medal count.
     */
    public int getBronze() {
        return bronze;
    }

    /**
     * Returns the total number of medals.
     *
     * @return the total number of medals.
     */
    int getTotal() {
        return gold + silver + bronze;
    }

    @Override
    public String toString() {
        return gold + "," + silver + "," + bronze + "," + (gold + silver + bronze);
    }
}
