package ch.jevtic.marko.sportbuddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.jevtic.marko.sportbuddy.model.Kategorie;
import ch.jevtic.marko.sportbuddy.repository.KategorieRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class KategorieService {

    @Autowired
    private KategorieRepository kategorieRepository;

    // Get all
    public List<Kategorie> getAllKategorie() {
        return kategorieRepository.findAll();
    }

    // Get specific kategorie
    // add Id to exception
    public Kategorie getKategorieById(Long id) {
        return kategorieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kategorie with id " + id + " not found"));
    }

    // Create new kategorie
    public Kategorie createKategorie(Kategorie kategorie) {
        Optional<Kategorie> existingKategorie = kategorieRepository.findByName(kategorie.getName());

        if (existingKategorie.isEmpty()) {

            return kategorieRepository.save(kategorie);
        } else {
            throw new RuntimeException("Kategorie '" + kategorie.getName() + "' exisitiert bereits.");

        }
    }

    // Update existing kategorie
    public Kategorie updateKategorie(Kategorie kategorie, Long id) {
        return kategorieRepository.findById(id)
                .map(kategorieOrig -> {
                    kategorieOrig.setName(kategorie.getName());
                    return kategorieRepository.save(kategorieOrig);
                })
                .orElseGet(() -> {
                    kategorie.setId(id);
                    return kategorieRepository.save(kategorie);
                });
    }

    // Delete kategorie by id
    public String deleteKategorie(Long id) {
        kategorieRepository.deleteById(id);
        return "Kategorie with id: " + id + " has been deleted";
    }
}