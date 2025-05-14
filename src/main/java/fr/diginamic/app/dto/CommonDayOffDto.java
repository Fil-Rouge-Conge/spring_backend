package fr.diginamic.app.dto;

import fr.diginamic.app.model.CommonDayOffType;

public class CommonDayOffDto extends DayOffDto {

    private CommonDayOffType commonDayOffType;
    private String caption;

    public CommonDayOffType getCommonDayOffType() {
        return commonDayOffType;
    }

    public void setCommonDayOffType(CommonDayOffType commonDayOffType) {
        this.commonDayOffType = commonDayOffType;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
