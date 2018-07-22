package services;

import models.Borrower;

import java.io.IOException;
import java.util.List;

public interface IBorrowerService {
    List<Borrower> allBorowers() throws IOException;
    void addBorrower(Borrower borrower) throws IOException;
}
