package edu.kit.informatik.genkinger;

public class GameBoard {

    private PlayerMark board[][];
    private GameBoardType gameBoardType;

    GameBoard(GameBoardSize gameBoardSize, GameBoardType gameBoardType) {

        this.gameBoardType = gameBoardType;

        if (gameBoardSize == GameBoardSize.SMALL) {
            board = new PlayerMark[18][18];
        } else {
            board = new PlayerMark[20][20];
        }

        reset();
    }

    public void reset() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = PlayerMark.NONE;
            }
        }
    }

    public PlayerMark[] getRow(int row) {
        int conv_row = convertCoordinate(row);
        if (conv_row == -1) {
            return null;
        }
        return board[row];
    }

    public PlayerMark[] getColumn(int col) {
        int conv_col = convertCoordinate(col);
        if (conv_col == -1) {
            return null;
        }

        PlayerMark[] retval = new PlayerMark[board.length];
        for (int i = 0; i < board.length; i++) {
            retval[i] = board[i][conv_col];
        }
        return retval;
    }

    public PlayerMark[][] getBoard() {
        return board;
    }

    public boolean place(int row, int col, PlayerMark mark) {
        int conv_col = convertCoordinate(col);
        int conv_row = convertCoordinate(row);

        if (conv_col == -1 || conv_row == -1) {
            return false;
        }

        if (board[conv_row][conv_col] != PlayerMark.NONE) {
            return false;
        }

        board[conv_row][conv_col] = mark;
        return true;
    }

    private int convertCoordinate(int value) {
        if (gameBoardType == GameBoardType.TORUS) {
            return value % board.length;
        } else {
            if (Utils.inRange(value, 0, board.length - 1)) {
                return value;
            } else {
                return -1;
            }
        }
    }

    public PlayerMark getCell(int row, int col) {
        int conv_row = convertCoordinate(row);
        int conv_col = convertCoordinate(col);
        if (conv_col == -1 || conv_row == -1) {
            return null;
        } else {
            return board[conv_row][conv_col];
        }
    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                str += board[i][j].toString() + " ";

            }
            str += "\n";
        }

        return str.trim();
    }
}
