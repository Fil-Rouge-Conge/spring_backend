package fr.diginamic.app.service.impl;

import fr.diginamic.app.model.CommonDayOff;
import fr.diginamic.app.model.CommonDayOffType;
import fr.diginamic.app.model.Status;
import fr.diginamic.app.repository.CommonDayOffRepository;
import fr.diginamic.app.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommonDayOffServiceImplTest {

    @Mock
    private CommonDayOffRepository commonDayOffRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private CommonDayOffServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowWhenDateIsInPast() {
        CommonDayOff dayOff = new CommonDayOff(
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(1),
                "Fête ancienne",
                Status.INITIAL,
                "Ancien",
                CommonDayOffType.HOLIDAY
        );

        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.save(dayOff));
        assertEquals("Cannot create common day off in the past", ex.getMessage());
    }

    @Test
    void shouldThrowWhenStartAfterEndDate() {
        CommonDayOff dayOff = new CommonDayOff(
                LocalDate.of(2025, 12, 26),
                LocalDate.of(2025, 12, 25),
                "Date inversée",
                Status.INITIAL,
                "Noël",
                CommonDayOffType.HOLIDAY
        );

        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.save(dayOff));
        assertEquals("Start date must be before or equal to end date", ex.getMessage());
    }

    @Test
    void shouldThrowWhenRTTIsOnWeekend() {
        LocalDate saturday = LocalDate.of(2025, 5, 17); // Samedi
        assertEquals(DayOfWeek.SATURDAY, saturday.getDayOfWeek());

        CommonDayOff dayOff = new CommonDayOff(
                saturday,
                saturday,
                "RTT samedi",
                Status.INITIAL,
                "RTT week-end",
                CommonDayOffType.RTT_EMPLOYER
        );

        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.save(dayOff));
        assertEquals("RTT cannot be set on weekends", ex.getMessage());
    }

    @Test
    void shouldThrowWhenDuplicateDateExists() {
        LocalDate date = LocalDate.of(2025, 12, 25);

        when(commonDayOffRepository.existsByBeginningDate(date)).thenReturn(true);

        CommonDayOff dayOff = new CommonDayOff(
                date,
                date,
                "Doublon",
                Status.INITIAL,
                "Noël",
                CommonDayOffType.HOLIDAY
        );

        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.save(dayOff));
        assertEquals("Another common day off already exists on this date", ex.getMessage());

        verify(commonDayOffRepository, times(1)).existsByBeginningDate(date);
    }

    @Test
    void shouldSaveWhenValid() {
        LocalDate date = LocalDate.of(2025, 12, 25);

        when(commonDayOffRepository.existsByBeginningDate(date)).thenReturn(false);
        CommonDayOff valid = new CommonDayOff(
                date,
                date,
                "Noël",
                Status.INITIAL,
                "Noël",
                CommonDayOffType.HOLIDAY
        );

        when(commonDayOffRepository.save(valid)).thenReturn(valid);

        CommonDayOff result = service.save(valid);
        assertEquals(valid, result);
        verify(commonDayOffRepository).save(valid);
    }
}
