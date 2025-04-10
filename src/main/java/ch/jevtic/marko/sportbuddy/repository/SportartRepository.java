package ch.jevtic.marko.sportbuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.jevtic.marko.sportbuddy.model.Sportart;

@Repository
public interface SportartRepository extends JpaRepository<Sportart, Long> {
    
}
