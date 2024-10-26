package jpaproject.jpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BookUpdateDto extends UpdateItemDto {

    private String author;
    private String isbn;

    public BookUpdateDto(String name, int price, int stockQuantity, String author, String isbn) {
    }
}
