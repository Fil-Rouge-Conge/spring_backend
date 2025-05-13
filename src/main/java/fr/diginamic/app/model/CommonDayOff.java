package fr.diginamic.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CommonDayOff extends DayOff{

    @Column(name = "caption")
    private String caption;

    @Enumerated(EnumType.STRING)
    @Column(name = "commonDayOffType", nullable = false)
    private CommonDayOffType commonDayOffType;

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
}
