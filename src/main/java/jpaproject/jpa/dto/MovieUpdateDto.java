package jpaproject.jpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieUpdateDto extends UpdateItemDto {

    private String director;
    private String actor;

    public MovieUpdateDto(String name, int price, int stockQuantity, String director,
        String actor) {
    }
}
