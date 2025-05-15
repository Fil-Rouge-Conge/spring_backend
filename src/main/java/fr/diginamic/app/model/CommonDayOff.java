package fr.diginamic.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Représente un congé commun
 * Le nom de sa table en base de données est " common_day_off "
 */
@Entity
public class CommonDayOff extends DayOff{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Dénomination du congés commun
     */
    @Column(name = "caption")
    private String caption;

    /**
     * Type de congés commun
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "commonDayOffType", nullable = false)
    private CommonDayOffType commonDayOffType;

    /**
     * Liste des employées concernés par ce congé commun
     */
    @ManyToMany
    @JoinTable(
            name = "employee_common_dayoff",
            joinColumns = @JoinColumn(name = "common_dayoff_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;

    public CommonDayOff(){}

    public CommonDayOff(LocalDate beginningDate, LocalDate endDate, String reason,
                         Status status, String caption, CommonDayOffType commonDayOffType) {
        super(beginningDate, endDate, reason, status);
        this.caption = caption;
        this.commonDayOffType = commonDayOffType;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public CommonDayOffType getCommonDayOffType() {
        return commonDayOffType;
    }

    public void setCommonDayOffType(CommonDayOffType commonDayOffType) {
        this.commonDayOffType = commonDayOffType;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
