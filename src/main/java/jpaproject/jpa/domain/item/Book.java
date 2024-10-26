package jpaproject.jpa.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jpaproject.jpa.dto.BookUpdateDto;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;


    public void changeItem(BookUpdateDto dto) {
        super.changeItem(dto);
        this.author = dto.getAuthor();
        this.isbn = dto.getIsbn();
    }
}
