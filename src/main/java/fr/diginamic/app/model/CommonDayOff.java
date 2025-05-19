package fr.diginamic.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Représente un congé commun
 * Le nom de sa table en base de données est " common_day_off "
 */
@Entity
@Table(name="common_day_off")
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
    @Column(name = "type", nullable = false)
    private CommonDayOffType type;

    public CommonDayOff(){}

    public CommonDayOff(LocalDate beginningDate, LocalDate endDate, String reason,
                         Status status, String caption, CommonDayOffType type) {
        super(beginningDate, endDate, reason, status);
        this.caption = caption;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public CommonDayOffType getType() {
        return type;
    }

    public void setType(CommonDayOffType type) {
        this.type = type;
    }
}
