package edu.kit.informatik.genkinger.connectsix;

import edu.kit.informatik.genkinger.Utils;

/**
 * This class represents the GameBoard of a {@link ConnectSixGame}
 *
 * @author Lukas Genkinger
 */
public class GameBoard {

    private PlayerMark board[][];
    private GameBoardType gameBoardType;

    /**
     * Constructs a new {@link GameBoard} of size {@link GameBoardSize} and of type {@link GameBoardType}.
     *
     * @param gameBoardSize the {@link GameBoardSize} of the board
     * @param gameBoardType the {@link GameBoardType} of the board
     */
    GameBoard(GameBoardSize gameBoardSize, GameBoardType gameBoardType) {

        this.gameBoardType = gameBoardType;

        if (gameBoardSize == GameBoardSize.SMALL) {
            board = new PlayerMark[18][18];
        } else {
            board = new PlayerMark[20][20];
        }

        reset();
    }

    /**
     * Resets the board (i.e. sets all {@link PlayerMark}s to <code>PlayerMark.NONE</code>).
     */
    public void reset() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = PlayerMark.NONE;
            }
        }
    }

    /**
     * Returns a {@link PlayerMark} array representing the specified row <code>row</code>
     *
     * @param row the index of the row to return
     * @return a {@link PlayerMark} array representing the specified row <code>row</code>.
     * <code>null</code> if <code>row</code> is invalid.
     */
    public PlayerMark[] getRow(int row) {
        int convRow = convertCoordinate(row);
        if (convRow == -1) {
            return null;
        }
        return board[convRow];
    }

    /**
     * Returns a {@link PlayerMark} array representing the specified column <code>col</code>
     *
     * @param col the index of the row to return
     * @return a {@link PlayerMark} array representing the specified column <code>col</code>.
     * <code>null</code> if <code>col</code> is invalid.
     */
    public PlayerMark[] getColumn(int col) {
        int convCol = convertCoordinate(col);
        if (convCol == -1) {
            return null;
        }

        PlayerMark[] retval = new PlayerMark[board.length];
        for (int i = 0; i < board.length; i++) {
            retval[i] = board[i][convCol];
        }
        return retval;
    }

    /**
     * Returns the two-dimensional {@link PlayerMark} array that represents the board.
     *
     * @return the two-dimensional {@link PlayerMark} array that represents the board
     */
    public PlayerMark[][] getBoard() {
        return board;
    }

    /**
     * Sets the "cell" at indices <code>row</code> and <code>col</code> to the {@link PlayerMark} <code>mark</code>.
     *
     * @param row  the index of the row
     * @param col  the index of the col
     * @param mark the mark to set the specified "cell" to
     * @return <code>true</code> if the specified "cell" was marked with <code>PlayerMark.NONE</code>
     */
    public boolean place(int row, int col, PlayerMark mark) {
        int convCol = convertCoordinate(col);
        int convRow = convertCoordinate(row);

        if (convCol == -1 || convRow == -1) {
            return false;
        }

        if (board[convRow][convCol] != PlayerMark.NONE) {
            return false;
        }

        board[convRow][convCol] = mark;
        return true;
    }

    private int convertCoordinate(int value) {
        if (gameBoardType == GameBoardType.TORUS) {
            return (value % board.length + board.length) % board.length;
        } else {
            if (Utils.inRange(value, 0, board.length - 1)) {
                return value;
            } else {
                return -1;
            }
        }
    }

    /**
     * Returns the "cell" on the board specified by <code>row</code> and <code>col</code>.
     *
     * @param row the index of the row
     * @param col the index of the col
     * @return the specified "cell" at row <code>row</code>, column <code>col</code>.
     * <code>null</code> if the indices are invalid.
     */
    public PlayerMark getCell(int row, int col) {
        int convRow = convertCoordinate(row);
        int convCol = convertCoordinate(col);
        if (convCol == -1 || convRow == -1) {
            return null;
        } else {
            return board[convRow][convCol];
        }
    }

    /**
     * Returns the {@link GameBoardType}.
     *
     * @return the {@link GameBoardType}
     */
    public GameBoardType getGameBoardType() {
        return gameBoardType;
    }

    /**
     * Returns a {@link PlayerMark} array representing the diagonal (top-left -> bot-right) with index <code>i</code>.
     * Indices from left to right.
     * Range: <code>0 < i < 2 * board.length</code>
     *
     * @param i the index of the diagonal
     * @return a {@link PlayerMark} array representing the diagonal at index <code>i</code>.
     * <code>null</code> if <code>i</code> is invalid.
     */
    public PlayerMark[] getDiagonalTopDown(int i) {

        if (!Utils.inRange(i, 0, 2 * board.length - 1)) {
            return null;
        }

        int size;

        if (i < board.length) {
            size = i + 1;
        } else {
            size = board.length - (i - board.length) - 1;
        }
        PlayerMark[] marks = new PlayerMark[size];

        for (int j = 0; j < size; j++) {
            int index = board.length - (size - j);
            if (i < board.length) {
                marks[j] = board[index][j];
            } else {
                marks[j] = board[j][index];
            }
        }

        return marks;
    }

    /**
     * Returns a {@link PlayerMark} array representing the diagonal (bot-left -> top-right) with index <code>i</code>.
     * Indices from left to right.
     * Range: <code>0 < i < 2 * board.length</code>
     *
     * @param i the index of the diagonal
     * @return a {@link PlayerMark} array representing the diagonal.
     *<code>null</code> if <code>i</code> is invalid.
     */
    public PlayerMark[] getDiagonalBottomUp(int i) {

        if (!Utils.inRange(i, 0, 2 * board.length - 1)) {
            return null;
        }

        int size;

        if (i < board.length) {
            size = i + 1;
        } else {
            size = board.length - (i - board.length) - 1;
        }

        PlayerMark[] marks = new PlayerMark[size];

        for (int j = 0; j < size; j++) {
            if (i < board.length) {
                int index = size - 1 - j;
                marks[j] = board[j][index];
            } else {
                int indexRow = (i - (board.length - 1)) + j;
                int indexCol = board.length - 1 - j;
                marks[j] = board[indexRow][indexCol];
            }
        }

        return marks;
    }

    /**
     * Returns <code>true</code> if every cell of the board is occupied. <code>false</code> otherwise.
     *
     * @return <code>true</code> if every cell of the board is occupied. <code>false</code> otherwise
     */
    public boolean isFull() {
        int count = 0;

        //TODO: this is inefficient ...
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == PlayerMark.NONE) {
                    count++;
                }
            }
        }
        return (count == 0);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                stringBuilder.append(board[i][j].toString()).append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString().trim();
    }
}
