package ch.jevtic.marko.sportbuddy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.jevtic.marko.sportbuddy.model.Match;
import ch.jevtic.marko.sportbuddy.model.Sportler;
import ch.jevtic.marko.sportbuddy.repository.MatchRepository;
import ch.jevtic.marko.sportbuddy.repository.SportlerRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SportlerService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private SportlerRepository sportlerRepository;

    SportlerService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    // Get all
    public List<Sportler> getAllSportler() {
        return sportlerRepository.findAll();
    }

    // Get specific sportler
    // add Id to exception
    public Sportler getSportlerById(Long id) {
        return sportlerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public Sportler createSportler(Sportler sportler) {

        // check if athlete exists
        Optional<Sportler> exisitingSportler = sportlerRepository.findByUsername(sportler.getUsername());

        if (exisitingSportler.isEmpty()) {

            return sportlerRepository.save(sportler);
        } else {
            throw new RuntimeException(
                    "Sportler mit dem Nutzernamen '" + sportler.getUsername() + "' exisitiert bereits.");
        }
    }

    public Sportler updateSportler(Sportler sportler, Long id) {
        return sportlerRepository.findById(id)
                .map(sportlerOrig -> {
                    sportlerOrig.setFirstname(sportler.getFirstname());
                    sportlerOrig.setLastname(sportler.getLastname());
                    sportlerOrig.setEmail(sportler.getEmail());
                    sportlerOrig.setOrt(sportler.getOrt());
                    if (sportler.getSportartenIds() == null) {
                        sportlerOrig.setSportartenIds(new ArrayList<>());
                    } else {
                        sportlerOrig.setSportartenIds(sportler.getSportartenIds());
                    }
                    if (sportler.getKategorieIds() == null) {
                        sportler.setKategorieIds(new ArrayList<>());
                    } else {
                        sportlerOrig.setKategorieIds(sportler.getKategorieIds());

                    }
                    sportlerOrig.setVerfügbarkeit(sportler.getVerfügbarkeit());
                    return sportlerRepository.save(sportlerOrig);

                })
                .orElseGet(() -> sportlerRepository.save(sportler));
    }

    public String deleteSportler(Long id) {
        if (!sportlerRepository.existsById(id)) {
            throw new RuntimeException("Sportler mit ID " + id + " existiert nicht.");
        } else {
            List<Match> match = matchRepository.findBySportler1IdOrSportler2Id(id, id);
            if (!match.isEmpty()) {
                return "Sportler mit der ID " + id
                        + " kann nicht gelöscht werden da die Person noch existierende Matches besitzt";
            } else {

                sportlerRepository.deleteById(id);
                return "Sportler mit der ID " + id + " wurde erfolgreich gelöscht.";
            }
        }
    }

}
