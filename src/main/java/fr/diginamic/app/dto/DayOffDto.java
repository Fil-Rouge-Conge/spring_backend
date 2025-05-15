package fr.diginamic.app.dto;

import fr.diginamic.app.model.Status;

import java.time.LocalDate;

public class DayOffDto {

    private Long id;
    private LocalDate beginningDate;
    private LocalDate endDate;
    private String reason;
    private Status status;

    /**
     * Getter
     */
    public Long getId() {
        return id;
    }
    public LocalDate getBeginningDate() {
        return beginningDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public String getReason() {
        return reason;
    }
    public Status getStatus() {
        return status;
    }




    /**
     * Setter
     */

    public void setId(Long id) {
        this.id = id;
    }
    public void setBeginningDate(LocalDate beginningDate) {
        this.beginningDate = beginningDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
