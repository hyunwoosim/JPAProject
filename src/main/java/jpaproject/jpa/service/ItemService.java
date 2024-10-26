package jpaproject.jpa.service;

import java.util.List;
import jpaproject.jpa.domain.item.Album;
import jpaproject.jpa.domain.item.Book;
import jpaproject.jpa.domain.item.Item;
import jpaproject.jpa.domain.item.Movie;
import jpaproject.jpa.dto.AlbumUpdateDto;
import jpaproject.jpa.dto.BookUpdateDto;
import jpaproject.jpa.dto.MovieUpdateDto;
import jpaproject.jpa.dto.UpdateItemDto;
import jpaproject.jpa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
        System.out.println("=====================");
        System.out.println("서비스 부분 :아이템 저장성공");
        System.out.println("=====================");
    }

    public List<Item> findItems() {
        System.out.println("=====================");
        System.out.println("itemRepository.findAll() = " + itemRepository.findAll());
        System.out.println("=====================");
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemDto updateDto) {
        Item findItem = itemRepository.findOne(itemId);

        if (findItem instanceof Book && updateDto instanceof BookUpdateDto) {
            ((Book) findItem).changeItem((BookUpdateDto) updateDto);
        } else if (findItem instanceof Album && updateDto instanceof AlbumUpdateDto) {
            ((Album) findItem).changeItem((AlbumUpdateDto) updateDto);
        } else if (findItem instanceof Movie && updateDto instanceof MovieUpdateDto) {
            ((Movie) findItem).changeItem((MovieUpdateDto) updateDto);
        }

    }

}
