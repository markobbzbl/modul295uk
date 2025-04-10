package ch.jevtic.marko.sportbuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.jevtic.marko.sportbuddy.model.Sportler;
import java.util.List;
import java.util.Optional;


@Repository
public interface SportlerRepository extends JpaRepository<Sportler, Long>{
 
    Optional<Sportler> findByUsername(String username);
}
