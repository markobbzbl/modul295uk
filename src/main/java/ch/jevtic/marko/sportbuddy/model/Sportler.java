package ch.jevtic.marko.sportbuddy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Sportler {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String firstname;
    private String lastname;
    private String email;

    private String ort;
    private String verfügbarkeit;

    @ManyToMany
    private List<Sportart> sportarten;

    @ManyToOne
    private Kategorie kategorie;
}
