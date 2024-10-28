package jpaproject.jpa.service;

import java.util.List;
import jpaproject.jpa.domain.item.Album;
import jpaproject.jpa.domain.item.Book;
import jpaproject.jpa.domain.item.Item;
import jpaproject.jpa.domain.item.Movie;
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
    public void updateItem(Long itemId, UpdateItemDto dto) {
        Item findItem = itemRepository.findOne(itemId);

        // 카테고리에 따라 업데이트 메서드 호출
        if (findItem instanceof Book && dto.getAuthor() != null) {
            dto.changeBook((Book) findItem);
        } else if (findItem instanceof Album && dto.getArtist() != null) {
            dto.changeAlbum((Album) findItem);
        } else if (findItem instanceof Movie && dto.getDirector() != null) {
            dto.changeMovie((Movie) findItem);
        } else {
            throw new IllegalArgumentException("Invalid item category or missing fields in DTO");
        }
    }

}
