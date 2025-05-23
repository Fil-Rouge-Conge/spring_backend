package fr.diginamic.app.service;

import fr.diginamic.app.model.CommonDayOff;

import java.util.List;
import java.util.Optional;

public interface CommonDayOffService {
    CommonDayOff save(CommonDayOff commonDayOff);

    List<CommonDayOff> findAll();

    Optional<CommonDayOff> findById(Long id);

    CommonDayOff update(Long id, CommonDayOff commonDayOff);

    void delete(Long id);
}
