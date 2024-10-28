package jpaproject.jpa.dto;

import jpaproject.jpa.domain.item.Album;
import jpaproject.jpa.domain.item.Book;
import jpaproject.jpa.domain.item.Movie;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateItemDto {

    private String name;
    private int price;
    private int stockQuantity;

    // book
    private String author;
    private String isbn;

    // album
    private String artist;
    private String etc;

    //movie
    private String director;
    private String actor;

    // 비즈니스 로직


    public void changeBook(Book book) {
        book.setName(this.name);
        book.setPrice(this.price);
        book.setStockQuantity(this.stockQuantity);
        book.setAuthor(this.author);
        book.setIsbn(this.isbn);
    }

    public void changeAlbum(Album album) {
        album.setName(this.name);
        album.setPrice(this.price);
        album.setStockQuantity(this.stockQuantity);
        album.setArtist(this.artist);
        album.setEtc(this.etc);
    }

    public void changeMovie(Movie movie) {
        movie.setName(this.name);
        movie.setPrice(this.price);
        movie.setStockQuantity(this.stockQuantity);
        movie.setDirector(this.director);
        movie.setActor(this.actor);
    }

}
