package fr.diginamic.app.service.impl;

import fr.diginamic.app.model.PersonalDayOff;
import fr.diginamic.app.repository.PersonalDayOffRepository;
import fr.diginamic.app.service.PersonalDayOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalDayOffServiceImpl implements PersonalDayOffService {
    @Autowired
    private PersonalDayOffRepository personalDayOffRepository;

    @Override
    public PersonalDayOff save(PersonalDayOff personalDayOff) {
        return personalDayOffRepository.save(personalDayOff);
    }

    @Override
    public List<PersonalDayOff> findAll() {
        return personalDayOffRepository.findAll();
    }

    @Override
    public Optional<PersonalDayOff> findById(Long id) {
        return personalDayOffRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        personalDayOffRepository.deleteById(id);
    }
}
