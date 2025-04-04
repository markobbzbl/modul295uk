package ch.jevtic.marko.sportbuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Kategorie {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer schwierigkeitsGrad; // 1–3
}
