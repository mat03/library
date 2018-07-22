package views.entry;

import views.enums.State;
import views.message.Message;

import java.util.Scanner;

public class AplicationMenager {
    private static final Scanner sc = new Scanner(System.in);

    public State runAplication() {
        do {
            System.out.println(Message.WELCOME_MESSAGE);
            int readKey = sc.nextInt();
            sc.nextLine();

            switch (readKey) {
                case 1: {
                    return State.ADD_BOOK;
                }
                case 2: {
                    return State.REMOVE_BOOK;
                }
                case 3: {
                    return State.BORROW_BOOK;
                }
                case 4: {
                    return State.BACK_BOOK;
                }
                case 5: {
                    return State.EXIT;
                }
                default: {
                    System.out.println(Message.WRONG_KEY);
                }
            }

        }while (true);

    }
}
