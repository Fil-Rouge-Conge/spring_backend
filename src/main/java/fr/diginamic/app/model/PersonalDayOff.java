package fr.diginamic.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PersonalDayOff extends DayOff{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "personalDayOffType", nullable = false)
    private PersonalDayOffType personalDayOffType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Employee employee;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
