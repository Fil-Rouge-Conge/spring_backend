package fr.diginamic.app.service;

import fr.diginamic.app.model.CommonDayOff;
import fr.diginamic.app.model.CommonDayOffType;
import fr.diginamic.app.model.Status;

import java.util.List;
import java.util.Optional;

public interface CommonDayOffService {
    CommonDayOff save(CommonDayOff commonDayOff);

    List<CommonDayOff> findAll();

    List<CommonDayOff> findByStatusAndType(Status status, CommonDayOffType type);

    Optional<CommonDayOff> findById(Long id);

    CommonDayOff update(Long id, CommonDayOff commonDayOff);

    void delete(Long id);
}
