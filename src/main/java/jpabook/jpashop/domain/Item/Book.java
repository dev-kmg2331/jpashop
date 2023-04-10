package jpabook.jpashop.domain.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("BOOK")
@Entity
public class Book extends Item{

    private String author;
    private String isbn;
}
