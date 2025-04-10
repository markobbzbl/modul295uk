package ch.jevtic.marko.sportbuddy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.jevtic.marko.sportbuddy.model.Kategorie;

@Repository
public interface KategorieRepository extends JpaRepository<Kategorie, Long> {

    Optional<Kategorie> findByName(String name);
}
