package ch.jevtic.marko.sportbuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.jevtic.marko.sportbuddy.model.Sportler;

@Repository
public interface SportlerRepository extends JpaRepository<Sportler, Long>{
    
}
