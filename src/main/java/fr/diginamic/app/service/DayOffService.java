package fr.diginamic.app.service;

import fr.diginamic.app.model.DayOff;

import java.util.List;
import java.util.Optional;

public interface DayOffService {
    DayOff save(DayOff dayOff);
    List<DayOff> findAll();
    Optional<DayOff> findById(Long id);
    void delete(Long id);
}
