package views.entry;

import views.controller.AuthorController;

import java.io.IOException;

public class AppLauncher {
    public static void main(String[] args) throws IOException {
        AplicationMenager aplicationMenager = new AplicationMenager();
        aplicationMenager.runApplication();
    }
}
