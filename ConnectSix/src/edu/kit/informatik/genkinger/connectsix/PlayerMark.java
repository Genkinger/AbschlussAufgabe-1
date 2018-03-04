package edu.kit.informatik.genkinger.connectsix;


/**
 * This enum represents the Marks players place on a {@link GameBoard}
 *
 * @author Lukas Genkinger
 * @see ConnectSixGame
 * @see GameBoard
 */
public enum PlayerMark {
    /**
     * Player one
     */
    ONE,
    /**
     * Player two
     */
    TWO,
    /**
     * Player three
     */
    THREE,
    /**
     * Player four
     */
    FOUR,
    /**
     * Used for marking empty cells on a {@link GameBoard}
     */
    NONE;

    @Override
    public String toString() {
        switch (this) {
            case NONE:
                return "**";
            case ONE:
                return "P1";
            case TWO:
                return "P2";
            case THREE:
                return "P3";
            case FOUR:
                return "P4";
            default:
                return "";
        }

    }
}
