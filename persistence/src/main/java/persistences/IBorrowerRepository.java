package persistences;

import models.Borrower;

import java.util.ArrayList;

public interface IBorrowerRepository {
    void add(Borrower borrower);
    void remove(Long id);
    void update(Borrower borrower);
    Borrower get(Long id);
    ArrayList<Borrower> getAll();
}
