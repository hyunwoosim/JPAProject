package jpaproject.jpa.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
    private String etc;

    public void changeArtist(String artist) {
        this.artist = artist;
    }

    public void changeEtc(String etc) {
        this.etc = etc;
    }
}
