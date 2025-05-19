package fr.diginamic.app.dto;

import fr.diginamic.app.model.PersonalDayOff;

public class PersonalDayOffMapper {
    public static PersonalDayOffDto toDto(PersonalDayOff personalDayOff) {
        PersonalDayOffDto dto = new PersonalDayOffDto();
            dto.setId(personalDayOff.getId());
            dto.setBeginningDate(personalDayOff.getBeginningDate());
            dto.setEndDate(personalDayOff.getEndDate());
            dto.setReason(personalDayOff.getReason());
            dto.setStatus(personalDayOff.getStatus());
            dto.setType(personalDayOff.getType());
            dto.setEmployee(EmployeeMapper.toDto(personalDayOff.getEmployee()));
            return dto;
    }

    public static PersonalDayOff toEntity(PersonalDayOffDto dto) {
        return new PersonalDayOff(
                dto.getBeginningDate(),
                dto.getEndDate(),
                dto.getReason(),
                dto.getStatus(),
                dto.getType()
        );
    }
}
