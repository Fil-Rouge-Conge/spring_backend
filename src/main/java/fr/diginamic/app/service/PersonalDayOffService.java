package fr.diginamic.app.service;

import fr.diginamic.app.model.PersonalDayOff;

import java.util.List;
import java.util.Optional;

public interface PersonalDayOffService {
    PersonalDayOff save(PersonalDayOff personalDayOff);

    List<PersonalDayOff> findAll();

    Optional<PersonalDayOff> findById(Long id);

    void delete(Long id);
}
