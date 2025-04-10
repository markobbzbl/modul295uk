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
    @JoinColumn(name = "sportler1_id")
    private Sportler sportler1;

    @ManyToOne
    @JoinColumn(name = "sportler2_id")
    private Sportler sportler2;

    private boolean bestätigt;
}