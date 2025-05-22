package fr.diginamic.app.model;

import fr.diginamic.app.repository.PersonalDayOffRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class PersonalDayOffJpaConstraintTest {

    @Autowired
    private PersonalDayOffRepository repository;

    @Test
    void shouldFailWhenEmployeeIsNull() {
        PersonalDayOff pdo = new PersonalDayOff(
                LocalDate.of(2025, 8, 15),
                LocalDate.of(2025, 8, 15),
                "Raison",
                Status.INITIAL,
                PersonalDayOffType.PAID_DAY_OFF
        );

        pdo.setEmployee(null); // Doit être rattaché à un employé

        assertThrows(PersistenceException.class, () -> {
            repository.saveAndFlush(pdo);
        });
    }

    @Test
    void shouldFailWhenStatusIsNull() {
        PersonalDayOff pdo = new PersonalDayOff(
                LocalDate.of(2025, 8, 15),
                LocalDate.of(2025, 8, 15),
                "Raison",
                null, //  Status est un champ obligatoire
                PersonalDayOffType.PAID_DAY_OFF
        );

        Employee emp = new Employee("Test", "User", "test@example.com", "pwd", Role.EMPLOYEE);
        pdo.setEmployee(emp);

        assertThrows(PersistenceException.class, () -> {
            repository.saveAndFlush(pdo);
        });
    }
}
