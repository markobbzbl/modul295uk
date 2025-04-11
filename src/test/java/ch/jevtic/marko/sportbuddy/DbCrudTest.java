package ch.jevtic.marko.sportbuddy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import ch.jevtic.marko.sportbuddy.model.Kategorie;
import ch.jevtic.marko.sportbuddy.repository.KategorieRepository;

@Rollback(false)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DbCrudTest {

    @Autowired
    private KategorieRepository kategorieRepository;


    //TESTING CRUD OPERATIONS
    @Test
    void testCreateKategorie() {
        Kategorie kategorie = new Kategorie();
        kategorie.setName("Test-Kategorie");
        Kategorie savedKategorie = kategorieRepository.save(kategorie);
        assertNotNull(savedKategorie.getId());
    }

    @Test
    void testReadKategorie() {
        Kategorie kategorie = new Kategorie();
        kategorie.setName("Read-Kategorie");
        Kategorie saved = kategorieRepository.save(kategorie);

        Optional<Kategorie> fetched = kategorieRepository.findById(saved.getId());
        assertTrue(fetched.isPresent());
        assertEquals("Read-Kategorie", fetched.get().getName());
    }

    @Test
    void testUpdateKategorie() {
        Kategorie kategorie = new Kategorie();
        kategorie.setName("Old Name");
        Kategorie saved = kategorieRepository.save(kategorie);

        saved.setName("Updated Name");
        Kategorie updated = kategorieRepository.save(saved);

        assertEquals("Updated Name", updated.getName());
    }

    @Test
    void testDeleteKategorie() {
        Kategorie kategorie = new Kategorie();
        kategorie.setName("Delete Me");
        Kategorie saved = kategorieRepository.save(kategorie);

        Long id = saved.getId();
        kategorieRepository.deleteById(id);

        Optional<Kategorie> deleted = kategorieRepository.findById(id);
        assertFalse(deleted.isPresent());
    }
}
