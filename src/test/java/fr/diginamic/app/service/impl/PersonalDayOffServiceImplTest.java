package fr.diginamic.app.service.impl;

import fr.diginamic.app.model.*;
import fr.diginamic.app.repository.PersonalDayOffRepository;
import fr.diginamic.app.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonalDayOffServiceImplTest {

    @Mock
    private PersonalDayOffRepository personalDayOffRepository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private PersonalDayOffServiceImpl service;

    private Employee employee;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee("Doe", "John", "john@example.com", "pwd", Role.EMPLOYEE, Departement.IT, 5f, 2f);
        employee.setId(1L);
    }

    @Test
    void shouldThrowWhenDatesAreNull() {
        PersonalDayOff pdo = new PersonalDayOff(null, null, "Test", Status.INITIAL, PersonalDayOffType.PAID_DAY_OFF);
        pdo.setEmployee(employee);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.save(pdo));
        assertEquals("Dates must not be null", ex.getMessage());
    }

    @Test
    void shouldThrowWhenEndBeforeStart() {
        PersonalDayOff pdo = new PersonalDayOff(
                LocalDate.of(2025, 6, 10),
                LocalDate.of(2025, 6, 9),
                "Test",
                Status.INITIAL,
                PersonalDayOffType.PAID_DAY_OFF
        );
        pdo.setEmployee(employee);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.save(pdo));
        assertEquals("End date must be after or equal to start date", ex.getMessage());
    }

    @Test
    void shouldThrowWhenReasonRequiredForUnpaid() {
        PersonalDayOff pdo = new PersonalDayOff(
                LocalDate.of(2025, 6, 10),
                LocalDate.of(2025, 6, 11),
                null,
                Status.INITIAL,
                PersonalDayOffType.UNPAID_DAY_OFF
        );
        pdo.setEmployee(employee);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.save(pdo));
        assertEquals("Reason is required for unpaid leave", ex.getMessage());
    }

    @Test
    void shouldThrowWhenOverlapExists() {
        when(personalDayOffRepository.countOverlappingAbsences(
                eq(employee.getId()), any(), any(), isNull()
        )).thenReturn(1L);

        PersonalDayOff pdo = new PersonalDayOff(
                LocalDate.of(2025, 6, 10),
                LocalDate.of(2025, 6, 11),
                "RTT",
                Status.INITIAL,
                PersonalDayOffType.PAID_DAY_OFF
        );
        pdo.setEmployee(employee);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.save(pdo));
        assertEquals("Overlapping absence exists for this employee", ex.getMessage());
    }

    @Test
    void shouldSaveWhenValid() {
        when(personalDayOffRepository.countOverlappingAbsences(
                eq(employee.getId()), any(), any(), isNull()
        )).thenReturn(0L);

        PersonalDayOff pdo = new PersonalDayOff(
                LocalDate.of(2025, 6, 10),
                LocalDate.of(2025, 6, 12),
                "Vacances",
                Status.INITIAL,
                PersonalDayOffType.PAID_DAY_OFF
        );
        pdo.setEmployee(employee);

        when(personalDayOffRepository.save(pdo)).thenReturn(pdo);

        PersonalDayOff result = service.save(pdo);

        assertEquals(pdo, result);
        verify(personalDayOffRepository).save(pdo);
    }

    @Test
    void shouldUpdateAndKeepSameEmployee() {
        Long id = 99L;

        PersonalDayOff original = new PersonalDayOff(
                LocalDate.of(2025, 6, 10),
                LocalDate.of(2025, 6, 12),
                "Ancienne raison",
                Status.INITIAL,
                PersonalDayOffType.PAID_DAY_OFF
        );
        original.setId(id);
        original.setEmployee(employee);

        PersonalDayOff updated = new PersonalDayOff(
                LocalDate.of(2025, 6, 13),
                LocalDate.of(2025, 6, 15),
                "Nouvelle raison",
                Status.DENIED,
                PersonalDayOffType.RTT_EMPLOYEE
        );
        updated.setEmployee(employee);

        when(personalDayOffRepository.findById(id)).thenReturn(Optional.of(original));
        when(personalDayOffRepository.save(any())).thenReturn(updated);

        PersonalDayOff result = service.update(id, updated);

        assertEquals("Nouvelle raison", result.getReason());
        assertEquals(LocalDate.of(2025, 6, 13), result.getBeginningDate());
        assertEquals(PersonalDayOffType.RTT_EMPLOYEE, result.getType());
        assertEquals(employee, result.getEmployee());
    }
}
