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
        book.changeName(this.name);
        book.changePrice(this.price);
        book.changeStockQuantity(this.stockQuantity);
        book.changeAuthor(this.author);
        book.changeIsbn(this.isbn);
    }

    public void changeAlbum(Album album) {
        album.changeName(this.name);
        album.changePrice(this.price);
        album.changeStockQuantity(this.stockQuantity);
        album.changeArtist(this.artist);
        album.changeEtc(this.etc);
    }

    public void changeMovie(Movie movie) {
        movie.changeName(this.name);
        movie.changePrice(this.price);
        movie.changeStockQuantity(this.stockQuantity);
        movie.changeDirector(this.director);
        movie.changeActor(this.actor);
    }

}
