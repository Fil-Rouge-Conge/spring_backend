package fr.diginamic.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Représente un congé
 * Le nom de sa table en base de données est " dayoffs "
 */
@Entity
@Table(name = "dayoffs")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DayOff {

    /**
     * Identifiant
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Date de début
     */
    @Column(name = "beginning_date", nullable = false)
    private LocalDate beginningDate;

    /**
     * Date de fin
     */
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    /**
     * Raison
     */
    @Column(name = "reason")
    private String reason;

    /**
     * Status
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public DayOff() {}

    public DayOff(LocalDate beginningDate, LocalDate endDate, String reason, Status status) {
        this.beginningDate = beginningDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(LocalDate beginningDate) {
        this.beginningDate = beginningDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
