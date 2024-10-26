package jpaproject.jpa.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jpaproject.jpa.dto.AlbumUpdateDto;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
    private String etc;

    public void changeItem(AlbumUpdateDto dto) {
        super.changeItem(dto);
        this.artist = dto.getArtist();
        this.etc = dto.getEtc();
    }
}
