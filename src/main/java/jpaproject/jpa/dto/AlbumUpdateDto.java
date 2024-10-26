package jpaproject.jpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumUpdateDto extends UpdateItemDto {

    private String artist;
    private String etc;

    public AlbumUpdateDto(String name, int price, int stockQuantity, String artist, String etc) {
    }
}
