package ch.jevtic.marko.sportbuddy.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Kategorie {

    @Id
    @GeneratedValue
    private Long id;

    @Column()
    @NonNull
    private String name;

}
