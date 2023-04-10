package jpabook.jpashop.domain.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@DiscriminatorValue("ALBUM")
@Entity
public class Album extends Item{

    private String artist;
    private String etc;
}
