package views.controller;

import models.Borrower;
import services.BorrowerService;
import services.IBorrowerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BorrowerController {
    private IBorrowerService borrowerService = new BorrowerService();

    public BorrowerController() {
    }

    public List<Borrower> allBorrowers() {
        List<Borrower> borrowers = new ArrayList<Borrower>();

        try {
            borrowers = borrowerService.allBorowers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return borrowers;
    }
}
