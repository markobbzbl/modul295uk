package ch.jevtic.marko.sportbuddy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.jevtic.marko.sportbuddy.model.Kategorie;
import ch.jevtic.marko.sportbuddy.model.Sportart;
import ch.jevtic.marko.sportbuddy.repository.KategorieRepository;
import ch.jevtic.marko.sportbuddy.repository.SportartRepository;



@Service
public class SportartService {

    @Autowired
    private SportartRepository sportartRepository;

    @Autowired
    private KategorieRepository kategorieRepository;

    public List<Sportart> getAllSportart() {
        return sportartRepository.findAll();
    }

    public Sportart getSportartById(Long id) {
        return sportartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sportart mit ID " + id + " nicht gefunden."));
    }


    public Sportart createSportart(Sportart sportart) {
        Sportart newSportart = new Sportart();
        newSportart.setName(sportart.getName());
        List<String> kategorieNamen = sportart.getKategorien();

        System.out.println("Kategorie-Namen aus Input: " + kategorieNamen);

        if (kategorieNamen != null) {
            for (String name : kategorieNamen) {
                if (kategorieRepository.findByName(name).isEmpty()) {
                    throw new RuntimeException("Kategorie '" + name + "' nicht gefunden.");
                }
            }
        }

        newSportart.setKategorien(kategorieNamen);

        System.out.println("Zu speichernde Sportart: " + newSportart);

        Sportart savedSportart = sportartRepository.save(newSportart);

        System.out.println("Gespeicherte Sportart: " + savedSportart);

        return savedSportart;
    }

    public Sportart updateSportart(Sportart sportart, Long id) {
        Sportart updatedSportart = getSportartById(id);
        updatedSportart.setName(sportart.getName());
        updatedSportart.setKategorien(sportart.getKategorien());
        return sportartRepository.save(updatedSportart);
    }

    public String deleteSportart(Long id) {
        if (!sportartRepository.existsById(id)) {
            throw new RuntimeException("Sportart mit ID " + id + " existiert nicht.");
        }
        sportartRepository.deleteById(id);
        return "Sportart mit der ID " + id + " wurde erfolgreich gelöscht.";
    }
}
