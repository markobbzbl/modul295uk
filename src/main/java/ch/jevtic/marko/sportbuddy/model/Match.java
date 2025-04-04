package ch.jevtic.marko.sportbuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Match {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Sportler sportler1;

    @ManyToOne
    private Sportler sportler2;

    private boolean bestätigt;
}
