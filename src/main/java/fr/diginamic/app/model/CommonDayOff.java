package fr.diginamic.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class CommonDayOff extends DayOff{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "caption")
    private String caption;

    @Enumerated(EnumType.STRING)
    @Column(name = "commonDayOffType", nullable = false)
    private CommonDayOffType commonDayOffType;

    @ManyToMany
    @JoinTable(
            name = "user_common_dayoff",
            joinColumns = @JoinColumn(name = "common_dayoff_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
