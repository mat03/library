package views.controller;

import models.Borrow;
import services.BorrowService;
import services.IBorrowService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Borrow> allBorrows() {
        List<Borrow> borrows = new ArrayList<Borrow>();
        try {
            borrows = borrowService.allBorow();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return borrows;
    }

    public Borrow getBorrow(Long id) {
        Borrow borrow = new Borrow();
        try {
            borrow = borrowService.getBorrow(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return borrow;
    }
}
