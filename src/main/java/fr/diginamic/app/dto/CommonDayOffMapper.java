package fr.diginamic.app.dto;

import fr.diginamic.app.model.CommonDayOff;

public class CommonDayOffMapper {
    public static CommonDayOffDto toDto(CommonDayOff commonDayOff) {
        CommonDayOffDto dto = new CommonDayOffDto();
            dto.setId(commonDayOff.getId());
            dto.setBeginningDate(commonDayOff.getBeginningDate());
            dto.setEndDate(commonDayOff.getEndDate());
            dto.setReason(commonDayOff.getReason());
            dto.setStatus(commonDayOff.getStatus());
            dto.setCommonDayOffType(commonDayOff.getType());
            dto.setCaption(commonDayOff.getCaption());
            return dto;
    }

    public static CommonDayOff toEntity(CommonDayOffDto dto) {
        return new CommonDayOff(
                dto.getBeginningDate(),
                dto.getEndDate(),
                dto.getReason(),
                dto.getStatus(),
                dto.getCaption(),
                dto.getCommonDayOffType()
        );
    }
}
