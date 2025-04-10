package ch.jevtic.marko.sportbuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.jevtic.marko.sportbuddy.model.Kategorie;
import ch.jevtic.marko.sportbuddy.security.Roles;
import ch.jevtic.marko.sportbuddy.service.KategorieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class KategorieController {
    
    @Autowired
    private KategorieService kategorieService;

    @GetMapping("api/kategorie")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Kategorie>> getAllKategorie() {
        List<Kategorie> allKategorie = kategorieService.getAllKategorie();
        return new ResponseEntity<>(allKategorie, HttpStatus.OK);
    }

    @GetMapping("api/kategorie/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Kategorie> getKategorieById(@PathVariable Long id) {
        Kategorie kategorie = kategorieService.getKategorieById(id);
        return new ResponseEntity<>(kategorie, HttpStatus.OK);
    }

    @PostMapping("api/kategorie")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Kategorie> createKategorie(@RequestBody Kategorie kategorie) {
        Kategorie newKategorie = kategorieService.createKategorie(kategorie);
        return new ResponseEntity<>(newKategorie, HttpStatus.CREATED);
    }

    @PutMapping("api/kategorie/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Kategorie> updateKategorie(@RequestBody Kategorie kategorie, @PathVariable Long id) {
        Kategorie updatedKategorie = kategorieService.updateKategorie(kategorie, id);
        return new ResponseEntity<>(updatedKategorie, HttpStatus.OK);
    }

    @DeleteMapping("api/kategorie/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<String> deleteKategorie(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(kategorieService.deleteKategorie(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
