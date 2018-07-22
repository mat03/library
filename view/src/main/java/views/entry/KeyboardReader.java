package views.entry;

import java.util.Scanner;

public class KeyboardReader {
    private final static KeyboardReader ourInstance = new KeyboardReader();

    public static KeyboardReader getInstance() {

        return ourInstance;
    }

    KeyboardReader() {
        Scanner scanner = new Scanner(System.in);
    }
}
