package ch.jevtic.marko.sportbuddy.service;

import org.springframework.stereotype.Service;

import ch.jevtic.marko.sportbuddy.repository.MatchRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ch.jevtic.marko.sportbuddy.model.Match;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match mit ID " + id + " nicht gefunden."));
    }

    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    public Match updateMatch(Match match, Long id) {
        Match existing = getMatchById(id);
        existing.setSportler1(match.getSportler1());
        existing.setSportler2(match.getSportler2());
        existing.setBestätigt(match.isBestätigt());
        return matchRepository.save(existing);
    }

    public String deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match mit ID " + id + " existiert nicht.");
        }
        matchRepository.deleteById(id);
        return "Match mit der ID " + id + " wurde erfolgreich gelöscht.";
    }
}
