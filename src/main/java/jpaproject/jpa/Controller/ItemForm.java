package jpaproject.jpa.Controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    private String category; // book, album, movie


    // book
    private String author;
    private String isbn;

    // album
    private String artist;
    private String etc;

    //movie
    private String director;
    private String actor;

}
