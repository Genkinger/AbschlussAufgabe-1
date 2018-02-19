package edu.kit.informatik.genkinger;

public class Main {

    public static void main(String[] args) {


        ConnectSix six = ConnectSixBuilder.build(args);

        if (six != null) {

            six.start();

        }
    }


}
