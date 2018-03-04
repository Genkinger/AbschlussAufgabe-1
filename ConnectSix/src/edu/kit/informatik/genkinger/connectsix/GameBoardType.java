package edu.kit.informatik.genkinger.connectsix;

/**
 * This enum represents the types of a {@link GameBoard}.
 *
 * @author Lukas Genkinger
 * @see GameBoard
 */
public enum GameBoardType {
    /**
     * Standard gameboard (no wrap-around)
     */
    STANDARD,
    /**
     * Torus gameboard (wrap-around)
     */
    TORUS
}
