package jpaproject.jpa.Controller.factory;

import jpaproject.jpa.Controller.ItemForm;
import jpaproject.jpa.domain.item.Album;
import jpaproject.jpa.domain.item.Book;
import jpaproject.jpa.domain.item.Item;
import jpaproject.jpa.domain.item.Movie;
import org.springframework.stereotype.Component;

@Component
public class ItemFactory {

    // 상품 등록
    public Item createItem(ItemForm form) {
        Item item;

        switch (form.getCategory()) {
            case "book":
                item = commonBook(form);
                break;
            case "album":
                item = commonAlbum(form);
                break;
            case "movie":
                item = commonMovie(form);
                break;
            default:
                throw new IllegalArgumentException("Invalid category: " + form.getCategory());
        }
        return item;
    }

    public ItemForm viewItem(Item item) {
        ItemForm form = new ItemForm();

        commonItemSetup(item, form);

        switch (item.getDtype()) {
            case "Book":
                Book book = (Book) item;
                form.setAuthor(book.getAuthor());
                form.setIsbn(book.getIsbn());
                break;
            case "Album":
                Album album = (Album) item;
                form.setArtist(album.getArtist());
                form.setEtc(album.getEtc());
                break;
            case "Movie":
                Movie movie = (Movie) item;
                form.setDirector(movie.getDirector());
                form.setActor(movie.getActor());
                break;
            default:
                throw new IllegalArgumentException("Invalid category: " + form.getCategory());
        }
        return form;
    }

    public ItemForm updateItem(Item item) {
        ItemForm form = new ItemForm();

        switch (item.getDtype()) {
            case "Book":
                commonBook(form);
                break;
            case "Album":
                commonAlbum(form);
                break;
            case "Movie":
                commonMovie(form);
                break;
            default:
                throw new IllegalArgumentException("Invalid category: " + form.getCategory());
        }
        return form;
    }


    private void commonItemSetup(Item item, ItemForm form) {
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
    }


    private Item commonMovie(ItemForm form) {
        Movie movie = new Movie();

        movie.setName(form.getName());
        movie.setPrice(form.getPrice());
        movie.setStockQuantity(form.getStockQuantity());
        movie.setDirector(form.getDirector());
        movie.setActor(form.getActor());

        return movie;
    }

    private Item commonAlbum(ItemForm form) {
        Album album = new Album();

        album.setName(form.getName());
        album.setPrice(form.getPrice());
        album.setStockQuantity(form.getStockQuantity());
        album.setArtist(form.getArtist());
        album.setEtc(form.getEtc());

        return album;


    }

    private Item commonBook(ItemForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        return book;
    }
}
