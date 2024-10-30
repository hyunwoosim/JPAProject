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
                form.setCategory(book.getDtype());
                break;
            case "Album":
                Album album = (Album) item;
                form.setArtist(album.getArtist());
                form.setEtc(album.getEtc());
                form.setCategory(album.getDtype());
                break;
            case "Movie":
                Movie movie = (Movie) item;
                form.setDirector(movie.getDirector());
                form.setActor(movie.getActor());
                form.setCategory(movie.getDtype());
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

        movie.changeName(form.getName());
        movie.changePrice(form.getPrice());
        movie.changeStockQuantity(form.getStockQuantity());
        movie.changeDirector(form.getDirector());
        movie.changeActor(form.getActor());

        return movie;
    }

    private Item commonAlbum(ItemForm form) {
        Album album = new Album();

        album.changeName(form.getName());
        album.changePrice(form.getPrice());
        album.changeStockQuantity(form.getStockQuantity());
        album.changeArtist(form.getArtist());
        album.changeEtc(form.getEtc());

        return album;


    }

    private Item commonBook(ItemForm form) {
        Book book = new Book();
        book.changeName(form.getName());
        book.changePrice(form.getPrice());
        book.changeStockQuantity(form.getStockQuantity());
        book.changeAuthor(form.getAuthor());
        book.changeIsbn(form.getIsbn());
        return book;
    }
}
