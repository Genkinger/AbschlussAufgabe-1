package edu.kit.informatik.genkinger.connectsix;

import edu.kit.informatik.genkinger.Utils;
import edu.kit.informatik.genkinger.controller.*;


/**
 * This class implements the game "ConnectSix" and its logic.
 * It uses a {@link StringInputController} to get user input (usually from stdin).
 *
 * @see StringInputController
 * @see GameBoard
 */
public class ConnectSixGame {


    private StringInputController inputController;
    private StringOutputInterface outputInterface;

    private GameBoard gameBoard;
    private int currentPlayerIndex;
    private PlayerMark[] activePlayers;
    private boolean gameOver;

    /**
     * Constructs a ConnectSixGame with a {@link GameBoardSize}, a {@link GameBoardType},
     * a {@link PlayerMark} representing the last active Player, a {@link StringInputInterface}
     * and a {@link StringOutputInterface}.
     *
     * @param gameBoardSize   the size of the board
     * @param gameBoardType   the type of the board
     * @param maxPlayer       the PlayerMark representing the last active player
     * @param inputInterface  the StringInputInterface to use (should be TerminalWrapper)
     * @param outputInterface the StringOutputInterface to use (should be TerminalWrapper)
     */
    ConnectSixGame(GameBoardSize gameBoardSize, GameBoardType gameBoardType, PlayerMark maxPlayer,
                   StringInputInterface inputInterface, StringOutputInterface outputInterface) {
        this.outputInterface = outputInterface;

        CommandPrototype quit = new CommandPrototype("quit", new CommandLayout());
        CommandPrototype place = new CommandPrototype("place", new CommandLayout().
                addParameter(CommandParameterType.INT).
                addParameter(CommandParameterType.INT).
                addParameter(CommandParameterType.INT).
                addParameter(CommandParameterType.INT));
        CommandPrototype rowprint = new CommandPrototype("rowprint", new CommandLayout().
                addParameter(CommandParameterType.INT));
        CommandPrototype colprint = new CommandPrototype("colprint", new CommandLayout().
                addParameter(CommandParameterType.INT));
        CommandPrototype print = new CommandPrototype("print", new CommandLayout());
        CommandPrototype reset = new CommandPrototype("reset", new CommandLayout());
        CommandPrototype state = new CommandPrototype("state", new CommandLayout().
                addParameter(CommandParameterType.INT).
                addParameter(CommandParameterType.INT));

        inputController = new StringInputController(inputInterface);

        inputController.attachActionToCommand(quit, (c, cmd) -> c.stop());
        inputController.attachActionToCommand(print, (c, cmd) -> print());
        inputController.attachActionToCommand(rowprint, (c, cmd) -> printRow(cmd.getIntegerParameter(0)));
        inputController.attachActionToCommand(colprint, (c, cmd) -> printColumn(cmd.getIntegerParameter(0)));
        inputController.attachActionToCommand(reset, (c, cmd) -> resetGame());
        inputController.attachActionToCommand(place, (c, cmd) -> {
            int r1 = cmd.getIntegerParameter(0);
            int c1 = cmd.getIntegerParameter(1);
            int r2 = cmd.getIntegerParameter(2);
            int c2 = cmd.getIntegerParameter(3);
            placeStones(r1, c1, r2, c2);
        });
        inputController.attachActionToCommand(state, (c, cmd) -> {
            state(cmd.getIntegerParameter(0), cmd.getIntegerParameter(1));
        });
        inputController.attachDefaultAction((c, cmd) -> {
            outputInterface.printError(cmd.getReasonForInvalidation());
        });


        this.gameBoard = new GameBoard(gameBoardSize, gameBoardType);
        this.currentPlayerIndex = 0;

        switch (maxPlayer) {
            case TWO:
                this.activePlayers
                        = new PlayerMark[]{PlayerMark.ONE, PlayerMark.TWO};
                break;
            case THREE:
                this.activePlayers
                        = new PlayerMark[]{PlayerMark.ONE, PlayerMark.TWO, PlayerMark.THREE};
                break;
            case FOUR:
                this.activePlayers
                        = new PlayerMark[]{PlayerMark.ONE, PlayerMark.TWO, PlayerMark.THREE, PlayerMark.FOUR};
                break;
            default:
                //TODO: error
                break;
        }

        this.gameOver = false;
    }

    /**
     * Starts the associated <code>StringInputController</code> (i.e. the game)
     */
    public void start() {
        inputController.start();
    }

    private void print() {
        outputInterface.printLine(gameBoard.toString());
    }

    private void printMarks(PlayerMark[] marks) {
        String str = "";
        for (PlayerMark m : marks) {
            str += m.toString() + " ";
        }
        outputInterface.printLine(str.trim());
    }

    private void printColumn(int col) {
        PlayerMark[] colmarks = gameBoard.getColumn(col);
        if (colmarks == null) {
            outputInterface.printError("Invalid column");
            return;
        }
        printMarks(colmarks);
    }

    private void printRow(int row) {
        PlayerMark[] rowmarks = gameBoard.getRow(row);
        if (rowmarks == null) {
            outputInterface.printError("Invalid row");
            return;
        }
        printMarks(rowmarks);
    }

    private void resetGame() {
        gameBoard.reset();
        currentPlayerIndex = 0;
        gameOver = false;
        ok();
    }

    private void placeStones(int row1, int col1, int row2, int col2) {
        PlayerMark player = activePlayers[currentPlayerIndex];
        PlayerMark cell1 = gameBoard.getCell(row1, col1);
        PlayerMark cell2 = gameBoard.getCell(row2, col2);

        if (cell1 == null
                || cell2 == null
                || cell1 != PlayerMark.NONE
                || cell2 != PlayerMark.NONE
                || (row1 == row2 && col1 == col2)
                || gameOver) {
            outputInterface.printError("Invalid move ");
        } else {

            //TODO: Error checking (unneccessary ?)
            gameBoard.place(row1, col1, player);
            gameBoard.place(row2, col2, player);


            PlayerMark winner = determineWinner();
            if (winner == PlayerMark.NONE) {
                if (gameBoard.isFull()) {
                    outputInterface.printLine("draw");
                    gameOver = true;
                } else {
                    ok();
                    nextPlayer();
                }
            } else {
                outputInterface.printLine(winner.toString() + " wins");
                gameOver = true;
            }
        }
    }

    private void nextPlayer() {
        currentPlayerIndex++;
        currentPlayerIndex %= activePlayers.length;
    }

    private void ok() {
        outputInterface.printLine("OK");
    }

    private PlayerMark determineWinner() {

        PlayerMark winner;


        winner = determineWinnerHorizVert();

        if (winner != PlayerMark.NONE) {
            return winner;
        }

        winner = determineWinnerDiagonal();

        if (winner != PlayerMark.NONE) {
            return winner;
        }

        return PlayerMark.NONE;
    }

    private PlayerMark determineWinnerDiagonal() {

        PlayerMark winner;
        int size = gameBoard.getBoard().length;

        if (gameBoard.getGameBoardType() == GameBoardType.STANDARD) {
            for (int i = 0; i < (size * 2 - 1); i++) {
                winner = checkLane(gameBoard.getDiagonalBottomUp(i));
                if (winner != PlayerMark.NONE) {
                    return winner;
                }
            }

            for (int i = 0; i < (size * 2 - 1); i++) {
                winner = checkLane(gameBoard.getDiagonalTopDown(i));
                if (winner != PlayerMark.NONE) {
                    return winner;
                }
            }
        } else {
            for (int i = 0; i < (size * 2 - 1); i++) {
                PlayerMark[] first;
                PlayerMark[] second;
                PlayerMark[] diag;

                if (i < size) {
                    first = gameBoard.getDiagonalTopDown(i);
                    second = gameBoard.getDiagonalTopDown(i + size);
                    diag = concatenateArrays(first, second);
                } else if (i == size) {
                    diag = gameBoard.getDiagonalTopDown(i);
                } else {
                    first = gameBoard.getDiagonalTopDown(i);
                    second = gameBoard.getDiagonalTopDown(i - size);
                    diag = concatenateArrays(first, second);
                }

                winner = checkLane(diag);
                if (winner != PlayerMark.NONE) {
                    return winner;
                }
            }

            for (int i = 0; i < (size * 2 - 1); i++) {
                PlayerMark[] first;
                PlayerMark[] second;
                PlayerMark[] diag;

                if (i < size) {
                    first = gameBoard.getDiagonalBottomUp(i);
                    second = gameBoard.getDiagonalBottomUp(i + size);
                    diag = concatenateArrays(first, second);
                } else if (i == size) {
                    diag = gameBoard.getDiagonalBottomUp(i);
                } else {
                    first = gameBoard.getDiagonalBottomUp(i);
                    second = gameBoard.getDiagonalBottomUp(i - size);
                    diag = concatenateArrays(first, second);
                }

                winner = checkLane(diag);
                if (winner != PlayerMark.NONE) {
                    return winner;
                }
            }
        }
        return PlayerMark.NONE;
    }

    private PlayerMark determineWinnerHorizVert() {
        PlayerMark winner;
        int size = gameBoard.getBoard().length;

        for (int i = 0; i < size; i++) {
            winner = checkLane(gameBoard.getRow(i));
            if (winner != PlayerMark.NONE) {
                return winner;
            }
        }

        for (int i = 0; i < size; i++) {
            winner = checkLane(gameBoard.getColumn(i));
            if (winner != PlayerMark.NONE) {
                return winner;
            }
        }
        return PlayerMark.NONE;
    }

    private PlayerMark checkLane(PlayerMark[] marks) {
        int count = 0;

        PlayerMark current = PlayerMark.NONE;
        int size = gameBoard.getGameBoardType() == GameBoardType.STANDARD ? marks.length : marks.length * 2;


        for (int j = 0; j < size; j++) {
            int index = j % marks.length;

            if (marks[index] == PlayerMark.NONE) {
                count = 0;
                current = PlayerMark.NONE;
            } else {
                if (current == marks[index]) {
                    count++;
                    if (count == 6) {
                        return current;
                    }
                } else {
                    count = 1;
                }
                current = marks[index];
            }
        }


        return PlayerMark.NONE;
    }

    private void state(int row, int col) {
        PlayerMark cell = gameBoard.getCell(row, col);
        if (cell == null) {
            outputInterface.printError("Invalid indices");
        } else {
            outputInterface.printLine(cell.toString());
        }
    }

    private PlayerMark[] concatenateArrays(PlayerMark[] first, PlayerMark[] second) {

        if (first == null || second == null) {
            return null;
        }

        PlayerMark[] retval = new PlayerMark[first.length + second.length];

        for (int i = 0; i < retval.length; i++) {
            if (i < first.length) {
                retval[i] = first[i];
            } else {
                retval[i] = second[i - first.length];
            }
        }

        return retval;
    }


}

