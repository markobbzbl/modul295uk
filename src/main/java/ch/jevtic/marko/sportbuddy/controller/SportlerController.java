package ch.jevtic.marko.sportbuddy.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import ch.jevtic.marko.sportbuddy.model.Sportler;
import ch.jevtic.marko.sportbuddy.security.Roles;
import ch.jevtic.marko.sportbuddy.service.SportlerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class SportlerController {
    
    @Autowired
    private SportlerService sportlerService;

    @GetMapping("api/sportler")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<List<Sportler>> getAllSportler() {
        List<Sportler> allSportler = sportlerService.getAllSportler();
        System.out.println("Allah");    

        return new ResponseEntity<>(allSportler, HttpStatus.OK);
    }

    @RolesAllowed(Roles.Read)
    @GetMapping("api/sportler/{id}")
    public ResponseEntity<Sportler> getMethodName(@PathVariable Long id) {
        Sportler sportler = sportlerService.getSportlerById(id);
        return new ResponseEntity<>(sportler, HttpStatus.OK);
    }

    @RolesAllowed(Roles.Admin)
    @PostMapping("api/sportler")
    public ResponseEntity<Sportler> createSportler(@RequestBody Sportler sportler) {
        Sportler newSportler = sportlerService.createSportler(sportler);    
        System.out.println("Allah");    
        return new ResponseEntity<>(newSportler, HttpStatus.OK);
    }

    @RolesAllowed(Roles.Admin)
    @PutMapping("api/sportler/{id}")
    public ResponseEntity<Sportler> updateSportler(@RequestBody Sportler sportler, @PathVariable Long id) {
        Sportler updatedSportler = sportlerService.updateSportler(sportler, id);        
        return new ResponseEntity<>(updatedSportler, HttpStatus.OK);
    }
    
    @DeleteMapping("api/sportler/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<String> deleteSportler(@PathVariable Long id){
        try {
            return ResponseEntity.ok(sportlerService.deleteSportler(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
