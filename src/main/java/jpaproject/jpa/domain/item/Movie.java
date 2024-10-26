package jpaproject.jpa.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jpaproject.jpa.dto.MovieUpdateDto;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("M")
public class Movie extends Item {

    private String director;
    private String actor;

    public void changeItem(MovieUpdateDto dto) {
        super.changeItem(dto);
        this.director = dto.getDirector();
        this.actor = dto.getActor();
    }
}
