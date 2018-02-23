package edu.kit.informatik.genkinger;


/**
 * This enum represents the Marks players place on a {@link GameBoard}
 *
 * @author Lukas Genkinger
 * @see ConnectSixGame
 * @see GameBoard
 */
public enum PlayerMark {
    /**
     *
     */
    ONE,
    /**
     *
     */
    TWO,
    /**
     *
     */
    THREE,
    /**
     *
     */
    FOUR,
    /**
     *
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
