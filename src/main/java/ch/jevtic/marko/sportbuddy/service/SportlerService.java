package ch.jevtic.marko.sportbuddy.service;

import java.net.http.HttpResponse.ResponseInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.jevtic.marko.sportbuddy.model.Sportler;
import ch.jevtic.marko.sportbuddy.repository.SportlerRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SportlerService {

    @Autowired
    private SportlerRepository sportlerRepository;

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
        Optional<Sportler> exisitingSportler = sportlerRepository.findByUsername(sportler.getUsername());
       
        if(exisitingSportler.isEmpty()){

            return sportlerRepository.save(sportler);
        } else {
            throw new RuntimeException("Sportler mit dem Nutzernamen '" + sportler.getUsername() + "' exisitiert bereits.");
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
        sportlerRepository.deleteById(id);
        return "Sportler with id: " + id + " has been deleted";

    }
}
