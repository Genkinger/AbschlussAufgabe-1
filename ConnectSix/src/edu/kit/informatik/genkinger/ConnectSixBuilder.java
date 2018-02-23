package edu.kit.informatik.genkinger;

import edu.kit.informatik.genkinger.controller.StringOutputInterface;
import edu.kit.informatik.genkinger.controller.TerminalWrapper;

/**
 * This class is used to "build" a {@link ConnectSixGame} from an array of commandline arguments.
 *
 * @author Lukas Genkinger
 * @see ConnectSixGame
 */
public class ConnectSixBuilder {

    /**
     * Returns a new {@link ConnectSixGame} if the specified <code>args</code> are valid.
     * It parses the <code>args</code>, checks them and sets up a {@link ConnectSixGame} accordingly.
     *
     * @param args the commandline arguments to use in the construction of the {@link ConnectSixGame}
     * @return the {@link ConnectSixGame} if the specified <code>args</code> are valid.
     * <code>null</code> otherwise.
     */
    public static ConnectSixGame build(String[] args) {

        TerminalWrapper wrapper = new TerminalWrapper();

        GameBoardSize size;
        GameBoardType type;
        PlayerMark maxPlayer;

        if (args.length != 3) {
            printUsage(wrapper);
            return null;
        }

        switch (args[0]) {
            case "standard":
                type = GameBoardType.STANDARD;
                break;
            case "torus":
                type = GameBoardType.TORUS;
                break;
            default:
                printUsage(wrapper);
                return null;
        }

        switch (args[1]) {
            case "18":
                size = GameBoardSize.SMALL;
                break;
            case "20":
                size = GameBoardSize.LARGE;
                break;
            default:
                printUsage(wrapper);
                return null;
        }

        switch (args[2]) {
            case "2":
                maxPlayer = PlayerMark.TWO;
                break;
            case "3":
                maxPlayer = PlayerMark.THREE;
                break;
            case "4":
                maxPlayer = PlayerMark.FOUR;
                break;
            default:
                printUsage(wrapper);
                return null;
        }

        return new ConnectSixGame(size, type, maxPlayer, wrapper, wrapper);
    }

    private static void printUsage(StringOutputInterface outputInterface) {
        outputInterface.printError("Invalid arguments\n\n"
                + "Usage: ConnectSixGame [type] [n] [players]\n"
                + "\ttype: torus or standard [String]\n"
                + "\tn: size n x n of the board (18 or 20) [Integer]\n"
                + "\tplayers: number of players (2, 3 or 4) [Integer]");
    }
}
