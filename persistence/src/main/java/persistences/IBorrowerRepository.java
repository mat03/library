package persistences;

import models.Borrower;

import java.io.IOException;
import java.util.List;

public interface IBorrowerRepository {
    void add(Borrower borrower) throws IOException;
    void remove(Long id) throws IOException;
    void update(Borrower borrower) throws IOException;
    Borrower get(Long id) throws IOException;
    List<Borrower> getAll() throws IOException;
}
