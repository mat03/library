package services;

import models.Borrower;
import persistences.BookRepository;
import persistences.BorrowerRepository;
import persistences.IBorrowerRepository;

import java.io.IOException;
import java.util.List;

public class BorrowerService implements IBorrowerService{
    private static final IBorrowerRepository borrowerRepository = new BorrowerRepository();

    public List<Borrower> allBorowers() throws IOException {
        return borrowerRepository.getAll();
    }

    public void addBorrower(Borrower borrower) throws IOException {
        borrowerRepository.add(borrower);
    }
}
