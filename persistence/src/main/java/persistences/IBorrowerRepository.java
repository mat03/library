package persistences;

import models.Borrower;

import java.io.IOException;
import java.util.List;

public interface IBorrowerRepository {
    void add(Borrower borrower) throws IOException;
    void remove(Long id);
    void update(Borrower borrower);
    Borrower get(Long id);
    List<Borrower> getAll();
}
