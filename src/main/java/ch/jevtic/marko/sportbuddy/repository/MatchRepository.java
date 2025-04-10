package ch.jevtic.marko.sportbuddy.repository;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.jevtic.marko.sportbuddy.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
