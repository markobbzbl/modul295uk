package ch.jevtic.marko.sportbuddy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Sportler {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    @Size(max = 20)
    @NotEmpty
    private String username;

    @Column()
    private String firstname;

    @Column()
    private String lastname;

    @Column()
    private String email;

    @Column()
    private String ort;

    @Column()
    private String verfügbarkeit;

    // Sportarten als IDs speichern
    @ElementCollection
    @CollectionTable(name = "sportler_sportarten", joinColumns = @JoinColumn(name = "sportler_id"))
    @Column(name = "sportart_id")
    private List<Long> sportartenIds;

    // Liste von Kategorie-IDs speichern (da Sportler mehrere Kategorien haben kann)
    @ElementCollection
    @CollectionTable(name = "sportler_kategorien", joinColumns = @JoinColumn(name = "sportler_id"))
    @Column(name = "kategorie_id")
    private List<Long> kategorieIds;
}
