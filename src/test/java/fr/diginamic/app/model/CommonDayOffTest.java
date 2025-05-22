package fr.diginamic.app.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CommonDayOffTest {

    @Test
    void testCommonDayOffFields() {
        LocalDate date = LocalDate.of(2025, 12, 25);
        CommonDayOff dayOff = new CommonDayOff(
                date,
                date,
                "Jour férié",
                Status.APPROVED,
                "Noël",
                CommonDayOffType.HOLIDAY
        );

        assertEquals(date, dayOff.getBeginningDate());
        assertEquals(date, dayOff.getEndDate());
        assertEquals("Jour férié", dayOff.getReason());
        assertEquals(Status.APPROVED, dayOff.getStatus());
        assertEquals("Noël", dayOff.getCaption());
        assertEquals(CommonDayOffType.HOLIDAY, dayOff.getType());
    }

    @Test
    void testCommonDayOffDefaultConstructor() {
        CommonDayOff dayOff = new CommonDayOff();
        assertNotNull(dayOff);
    }
}
