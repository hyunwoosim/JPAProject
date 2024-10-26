package jpaproject.jpa.domain.item;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import jpaproject.jpa.domain.Category;
import jpaproject.jpa.dto.UpdateItemDto;
import jpaproject.jpa.excoption.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @ManyToMany(mappedBy = "items")
    private final List<Category> categories = new ArrayList<>();
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    // == 비즈니스 로직 ==//

    // 업데이트 로직
    public void changeItem(UpdateItemDto dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.stockQuantity = dto.getStockQuantity();
    }


    // Dtype을 조회하는 로직
    public String getDtype() {
        return this.getClass().getSimpleName();
    }

    // 재고 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    // 재고 감소
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
