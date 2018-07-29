package services;

import models.Borrow;

import java.io.IOException;
import java.util.List;

public interface IBorrowService {
    List<Borrow> allBorow() throws IOException;
    void addBorrow(Borrow borrow) throws IOException;
    void remoweBorrow(Long id) throws IOException;
    Borrow getBorrow(Long id) throws IOException;
}
