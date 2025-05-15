package fr.diginamic.app.dto;

import fr.diginamic.app.model.CommonDayOffType;

public class CommonDayOffDto extends DayOffDto {

    private CommonDayOffType commonDayOffType;
    private String caption;

    /**
     * Getter
     */
    public CommonDayOffType getCommonDayOffType() {
        return commonDayOffType;
    }
    public String getCaption() {
        return caption;
    }

    /**
     * Setter
     */
    public void setCommonDayOffType(CommonDayOffType commonDayOffType) {
        this.commonDayOffType = commonDayOffType;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
