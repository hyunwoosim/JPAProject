package jpaproject.jpa.dto;

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


}
