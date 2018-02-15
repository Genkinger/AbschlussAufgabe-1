package edu.kit.informatik.genkinger;

import edu.kit.informatik.genkinger.controller.StringOutputInterface;
import edu.kit.informatik.genkinger.controller.TerminalWrapper;

public class ConnectSixBuilder {
    public static ConnectSix build(String[] args) {

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

        return new ConnectSix(size, type, maxPlayer, wrapper, wrapper);
    }

    private static void printUsage(StringOutputInterface outputInterface) {
        outputInterface.printError("Invalid arguments\n\n"
                + "Usage: ConnectSix [type] [n] [players]\n"
                + "\ttype: torus or standard [String]\n"
                + "\tn: size n x n of the board (18 or 20) [Integer]\n"
                + "\tplayers: number of players (2, 3 or 4) [Integer]");
    }
}
