package jpaproject.jpa.Controller.factory;

import jpaproject.jpa.Controller.ItemForm;
import jpaproject.jpa.domain.item.Album;
import jpaproject.jpa.domain.item.Book;
import jpaproject.jpa.domain.item.Item;
import jpaproject.jpa.domain.item.Movie;
import org.springframework.stereotype.Component;

@Component
public class ItemFactory {

    public Item createItem(ItemForm form) {
        Item item;

        switch (form.getCategory()) {
            case "book":
                item = createBook(form);
                break;
            case "album":
                item = createAlbum(form);
                break;
            case "movie":
                item = createMovie(form);
                break;
            default:
                throw new IllegalArgumentException("Invalid category: " + form.getCategory());
        }
        return item;
    }

    private Item createMovie(ItemForm form) {
        Movie movie = new Movie();

        movie.setName(form.getName());
        movie.setPrice(form.getPrice());
        movie.setStockQuantity(form.getStockQuantity());
        movie.setDirector(form.getDirector());
        movie.setActor(form.getActor());

        return movie;
    }

    private Item createAlbum(ItemForm form) {
        Album album = new Album();

        album.setName(form.getName());
        album.setPrice(form.getPrice());
        album.setStockQuantity(form.getStockQuantity());
        album.setArtist(form.getArtist());
        album.setEtc(form.getEtc());

        return album;


    }

    private Item createBook(ItemForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        return book;
    }
}
