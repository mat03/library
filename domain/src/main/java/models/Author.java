package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {
    private Long id;
    private String name;
    private String surname;
    private String birthPlace;
    private boolean remove;

    public Author(String name, String surname, String birthPlace) {
        this.name = name;
        this.surname = surname;
        this.birthPlace = birthPlace;
        this.remove = false;
    }

    public Author(Long id, String name, String surname, String birthPlace, boolean remove) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthPlace = birthPlace;
        this.remove = remove;
    }

    public Author() {
    }
}


