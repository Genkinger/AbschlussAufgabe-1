package edu.kit.informatik.genkinger;

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
