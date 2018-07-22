package views.entry;

import java.util.Scanner;

public class KeyboardReader {
    private static Scanner sc = null;

    public static Scanner getInstance() {

        if(sc == null) {
            sc = new Scanner(System.in);
        }
        return sc;
    }

}
