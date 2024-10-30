package jpaproject.jpa.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("M")
public class Movie extends Item {

    private String director;
    private String actor;

    public void changeDirector(String director) {
        this.director = director;
    }

    public void changeActor(String actor) {
        this.actor = actor;
    }
}
