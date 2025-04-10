package ch.jevtic.marko.sportbuddy.controller;

import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.jevtic.marko.sportbuddy.model.Match;
import ch.jevtic.marko.sportbuddy.security.Roles;
import ch.jevtic.marko.sportbuddy.service.MatchService;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/api/match")
    @RolesAllowed(Roles.Read)
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/api/match/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }

    @PostMapping("/api/match")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        return ResponseEntity.ok(matchService.createMatch(match));
    }

    @PutMapping("/api/match/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Match> updateMatch(@RequestBody Match match, @PathVariable Long id) {
        return ResponseEntity.ok(matchService.updateMatch(match, id));
    }

    @DeleteMapping("/api/match/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<String> deleteMatch(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.deleteMatch(id));
    }
}
