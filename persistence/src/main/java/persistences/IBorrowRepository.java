package persistences;

import models.Borrow;

import java.io.IOException;
import java.util.List;

public interface IBorrowRepository {
    void add(Borrow borrow) throws IOException;
    void remove(Long id) throws IOException;
    void update(Borrow borrow) throws IOException;
    Borrow get(Long id) throws IOException;
    List<Borrow> getAll() throws IOException;
}
