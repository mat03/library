package models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Borrow {
    private Long id;
    private Date rentalDate;
    private Long bookId;
    private Long borrowerId;

    public Borrow(Long id, Date rentalDate, Long bookId, Long borrowerId) {
        this.id = id;
        this.rentalDate = rentalDate;
        this.bookId = bookId;
        this.borrowerId = borrowerId;
    }
}
