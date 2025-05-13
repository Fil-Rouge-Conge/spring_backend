package fr.diginamic.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PersonalDayOff extends DayOff{
    @Enumerated(EnumType.STRING)
    @Column(name = "personalDayOffType", nullable = false)
    private PersonalDayOffType personalDayOffType;

    public PersonalDayOff() {}

    public PersonalDayOff(LocalDate beginningDate, LocalDate endDate, String reason,
                           Status status, PersonalDayOffType personalDayOffType) {
        super(beginningDate, endDate, reason, status);
        this.personalDayOffType = personalDayOffType;
    }

    public PersonalDayOffType getPersonalDayOffType() {
        return personalDayOffType;
    }

    public void setPersonalDayOffType(PersonalDayOffType personalDayOffType) {
        this.personalDayOffType = personalDayOffType;
    }
}
