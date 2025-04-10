package ch.jevtic.marko.sportbuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ch.jevtic.marko.sportbuddy.model.Sportart;
import ch.jevtic.marko.sportbuddy.security.Roles;
import ch.jevtic.marko.sportbuddy.service.SportartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class SportartController {

    @Autowired
    private SportartService sportartService;

    @GetMapping("api/sportart")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Sportart>> getAllSportart() {
        List<Sportart> allSportart = sportartService.getAllSportart();
        return new ResponseEntity<>(allSportart, HttpStatus.OK);
    }

    @GetMapping("api/sportart/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Sportart> getSportartById(@PathVariable Long id) {
        Sportart sportart = sportartService.getSportartById(id);
        return new ResponseEntity<>(sportart, HttpStatus.OK);
    }

    @PostMapping("api/sportart")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Sportart> createSportart(@RequestBody Sportart sportart) {
        System.out.println("ALLAH " + sportart);
        Sportart newSportart = sportartService.createSportart(sportart);
        return new ResponseEntity<>(newSportart, HttpStatus.CREATED);
    }

    @PutMapping("api/sportart/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Sportart> updateSportart(@RequestBody Sportart sportart, @PathVariable Long id) {
        Sportart updatedSportart = sportartService.updateSportart(sportart, id);
        return new ResponseEntity<>(updatedSportart, HttpStatus.OK);
    }

    @DeleteMapping("api/sportart/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<String> deleteSportart(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(sportartService.deleteSportart(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
