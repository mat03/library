package models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Borrower {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private Long phone;
    private String email;
    private boolean remove;

    public Borrower(Long id, String name, String surname, String address, Long phone, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.remove = false;
    }

    public Borrower() {
    }
}
