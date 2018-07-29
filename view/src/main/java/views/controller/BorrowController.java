package views.controller;

import models.Borrow;
import services.BorrowService;
import services.IBorrowService;

import java.io.IOException;

public class BorrowController {
    private IBorrowService borrowService = new BorrowService();

    public BorrowController() {
    }

    public void addBorrow(Long bookId, Long borrowerId) {
        Borrow borrow = new Borrow(bookId, borrowerId);

        try {
            borrowService.addBorrow(borrow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeBorrow(Long id) {
        try {
            borrowService.remoweBorrow(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
