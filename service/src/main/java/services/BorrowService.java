package services;

import models.Borrow;
import persistences.BorrowRepository;
import persistences.IBorrowRepository;

import java.io.IOException;
import java.util.List;

public class BorrowService implements IBorrowService {
    private static final IBorrowRepository borrowRepository = new BorrowRepository();

    public List<Borrow> allBorow() throws IOException {
        return borrowRepository.getAll();
    }

    public void addBorrow(Borrow borrow) throws IOException {
        borrowRepository.add(borrow);
    }

    public void remoweBorrow(Long id) throws IOException {
        borrowRepository.remove(id);
    }
}
