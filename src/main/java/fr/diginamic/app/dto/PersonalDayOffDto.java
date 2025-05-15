package fr.diginamic.app.dto;

import fr.diginamic.app.model.PersonalDayOffType;

public class PersonalDayOffDto extends DayOffDto {
    private PersonalDayOffType personalDayOffType;

    /**
     * Getter
     */
    public PersonalDayOffType getPersonalDayOffType() {
        return personalDayOffType;
    }
    /**
     * Setter
     */
    public void setPersonalDayOffType(PersonalDayOffType personalDayOffType) {
        this.personalDayOffType = personalDayOffType;
    }
}
