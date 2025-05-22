package fr.diginamic.app.model;

import fr.diginamic.app.repository.CommonDayOffRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class CommonDayOffJpaConstraintTest {

    @Autowired
    private CommonDayOffRepository repository;

    @Test
    void shouldFailWhenTypeIsNull() {
        CommonDayOff invalid = new CommonDayOff(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 12, 25),
                "Fête",
                Status.INITIAL,
                "Noël",
                null // Type est obligatoire
        );

        assertThrows(PersistenceException.class, () -> {
            repository.saveAndFlush(invalid);
        });
    }

    @Test
    void shouldFailWhenStatusIsNull() {
        CommonDayOff invalid = new CommonDayOff(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 12, 25),
                "Fête",
                null, // Status est obligatoire
                "Noël",
                CommonDayOffType.HOLIDAY
        );

        assertThrows(PersistenceException.class, () -> {
            repository.saveAndFlush(invalid);
        });
    }

    @Test
    void shouldFailWhenBeginningDateIsNull() {
        CommonDayOff invalid = new CommonDayOff(
                null, // Date de début est obligatoire
                LocalDate.of(2025, 12, 25),
                "Fête",
                Status.INITIAL,
                "Noël",
                CommonDayOffType.HOLIDAY
        );

        assertThrows(PersistenceException.class, () -> {
            repository.saveAndFlush(invalid);
        });
    }
}
