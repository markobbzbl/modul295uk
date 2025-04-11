package ch.jevtic.marko.sportbuddy.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.jevtic.marko.sportbuddy.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findBySportler1IdOrSportler2Id(Long sportler1Id, Long sportler2Id);

}
