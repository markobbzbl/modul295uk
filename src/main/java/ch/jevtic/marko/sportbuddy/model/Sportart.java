package ch.jevtic.marko.sportbuddy.model;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sportart {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NonNull
    private String name;

    @Column
    private List<String> kategorien;

}
