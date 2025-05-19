package fr.diginamic.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Représente un congé personnel
 * Le nom de sa table en base de données est " personal_day_off "
 */

@Entity
@Table(name="personal_day_off")
public class PersonalDayOff extends DayOff{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Type de congé personnel
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PersonalDayOffType type;

    /**
     * Id de l'employée concerné par ce congé
     */


    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public PersonalDayOff() {}

    public PersonalDayOff(LocalDate beginningDate, LocalDate endDate, String reason,
                           Status status, PersonalDayOffType type) {
        super(beginningDate, endDate, reason, status);
        this.type = type;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public PersonalDayOffType getType() {
        return type;
    }

    public void setType(PersonalDayOffType type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
