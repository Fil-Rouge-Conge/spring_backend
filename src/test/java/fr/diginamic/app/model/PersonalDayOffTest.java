package fr.diginamic.app.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonalDayOffTest {

    @Test
    void testPersonalDayOffFields() {
        LocalDate start = LocalDate.of(2025, 8, 15);
        LocalDate end = LocalDate.of(2025, 8, 16);
        PersonalDayOff pdo = new PersonalDayOff(
                start,
                end,
                "Déplacement personnel",
                Status.INITIAL,
                PersonalDayOffType.UNPAID_DAY_OFF
        );

        Employee emp = new Employee("Doe", "John", "john.doe@example.com", "securePass", Role.EMPLOYEE);
        pdo.setEmployee(emp);

        assertEquals(start, pdo.getBeginningDate());
        assertEquals(end, pdo.getEndDate());
        assertEquals("Déplacement personnel", pdo.getReason());
        assertEquals(Status.INITIAL, pdo.getStatus());
        assertEquals(PersonalDayOffType.UNPAID_DAY_OFF, pdo.getType());
        assertEquals(emp, pdo.getEmployee());
    }

    @Test
    void testPersonalDayOffDefaultConstructor() {
        PersonalDayOff dayOff = new PersonalDayOff();
        assertNotNull(dayOff);
    }
}
