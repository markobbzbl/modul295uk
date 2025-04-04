package ch.jevtic.marko.sportbuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sportart {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Kategorie kategorie;
}
