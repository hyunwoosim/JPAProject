package jpaproject.jpa.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;

    public void changeAuthor(String author) {
        this.author = author;
    }

    public void changeIsbn(String isbn) {
        this.isbn = isbn;
    }
}
