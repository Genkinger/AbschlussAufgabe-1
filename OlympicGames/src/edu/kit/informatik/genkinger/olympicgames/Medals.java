package edu.kit.informatik.genkinger.olympicgames;

/**
 *
 */
public class Medals {

    private int gold;
    private int silver;
    private int bronze;

    /**
     *
     * @param gold .
     * @param silver .
     * @param bronze .
     */
    public Medals(int gold, int silver, int bronze) {
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

    /**
     *
     * @param medals .
     */
    public void add(Medals medals) {
        gold += medals.getGold();
        silver += medals.getSilver();
        bronze += medals.getBronze();
    }

    /**
     *
     * @return .
     */
    public int getGold() {
        return gold;
    }

    /**
     *
     * @return .
     */
    public int getSilver() {
        return silver;
    }

    /**
     *
     * @return .
     */
    public int getBronze() {
        return bronze;
    }

    /**
     *
     * @param gold .
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     *
     * @param silver .
     */
    public void setSilver(int silver) {
        this.silver = silver;
    }

    /**
     *
     * @param bronze .
     */
    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    @Override
    public String toString() {
        return gold + "," + silver + "," + bronze + "," + (gold + silver + bronze);
    }
}
