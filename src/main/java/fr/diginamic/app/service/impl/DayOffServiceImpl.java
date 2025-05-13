package fr.diginamic.app.service.impl;

import fr.diginamic.app.model.DayOff;
import fr.diginamic.app.repository.DayOffRepository;
import fr.diginamic.app.repository.EmployeeRepository;
import fr.diginamic.app.service.DayOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DayOffServiceImpl implements DayOffService {
    @Autowired
    private DayOffRepository dayOffRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DayOff save(DayOff dayOff) {
        return dayOffRepository.save(dayOff);
    }

    @Override
    public List<DayOff> findAll() {
        return dayOffRepository.findAll();
    }

    @Override
    public Optional<DayOff> findById(Long id) {
        return dayOffRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

}
