package edu.kit.informatik.genkinger.connectsix;

import edu.kit.informatik.genkinger.connectsix.ConnectSixBuilder;
import edu.kit.informatik.genkinger.connectsix.ConnectSixGame;

public class Main {
    /**
     * Entry point
     *
     * @param args the commandline args
     */
    public static void main(String[] args) {


        ConnectSixGame six = ConnectSixBuilder.build(args);

        if (six != null) {
            six.start();
        }
    }


}
