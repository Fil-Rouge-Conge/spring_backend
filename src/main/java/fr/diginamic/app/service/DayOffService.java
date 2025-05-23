package fr.diginamic.app.service;

import fr.diginamic.app.model.DayOff;
import fr.diginamic.app.model.Status;

import java.util.List;
import java.util.Optional;

public interface DayOffService {
    DayOff save(DayOff dayOff);
    List<DayOff> findAll();
    List<DayOff> findByStatusOrderByBeginningDateAsc(Status status);
    List<DayOff> processInitialDaysOff();

    Optional<DayOff> findById(Long id);
    void delete(Long id);
}
