package fr.diginamic.app.dto;

import fr.diginamic.app.model.PersonalDayOffType;

public class PersonalDayOffDto extends DayOffDto {
    private PersonalDayOffType type;
    private EmployeeDto employee;

    /**
     * Getter
     */
    public PersonalDayOffType getType() {
        return type;
    }
    /**
     * Setter
     */
    public void setType(PersonalDayOffType type) {
        this.type = type;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }
}
