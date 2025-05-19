package fr.diginamic.app.service.impl;

import fr.diginamic.app.dto.CommonDayOffDto;
import fr.diginamic.app.model.CommonDayOff;
import fr.diginamic.app.model.Employee;
import fr.diginamic.app.model.Status;
import fr.diginamic.app.repository.CommonDayOffRepository;
import fr.diginamic.app.repository.EmployeeRepository;
import fr.diginamic.app.service.CommonDayOffService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommonDayOffServiceImpl implements CommonDayOffService {

    @Autowired
    private CommonDayOffRepository commonDayOffRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public CommonDayOff save(CommonDayOff commonDayOff) {
        validateBusinessRules(commonDayOff);
        return commonDayOffRepository.save(commonDayOff);
    }

    @Override
    public List<CommonDayOff> findAll() {
        return commonDayOffRepository.findAll();
    }

    @Override
    public Optional<CommonDayOff> findById(Long id) {
        return commonDayOffRepository.findById(id);
    }

    @Override
    public CommonDayOff update (Long id, CommonDayOff commonDayOff) {
        CommonDayOff existing = commonDayOffRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Common Day Off not found"));
        if (existing.getStatus() == Status.APPROVED && existing.getType().name().equals("RTT_EMPLOYER")) {
            throw new IllegalArgumentException("Cannot modify a validated RTT");
        }
        validateBusinessRules(commonDayOff);
        existing.setBeginningDate(commonDayOff.getBeginningDate());
        existing.setEndDate(commonDayOff.getEndDate());
        existing.setReason(commonDayOff.getReason());
        existing.setCaption(commonDayOff.getCaption());
        existing.setType(commonDayOff.getType());
        existing.setStatus(Status.INITIAL);
        return commonDayOffRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        CommonDayOff existing = commonDayOffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Common Day Off not found"));

        if (existing.getBeginningDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot delete a day off in the past");
        }
        if (existing.getType().name().equals("RTT_EMPLOYER") && existing.getStatus() == Status.APPROVED) {
            List<Employee> employees = employeeRepository.findAll();
            for (Employee employee : employees) {
                employee.setEmplRttBalance(employee.getEmplRttBalance() + 1);
                employeeRepository.save(employee);
            }
        }
        commonDayOffRepository.deleteById(id);
    }

    private void validateBusinessRules(CommonDayOff commonDayOff) {
        if (commonDayOff.getBeginningDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot create common day off in the past");
        }

        if (commonDayOffRepository.existsByBeginningDate(commonDayOff.getBeginningDate())) {
            throw new IllegalArgumentException("Another common day off already exists on this date");
        }

        DayOfWeek day = commonDayOff.getBeginningDate().getDayOfWeek();
        if (commonDayOff.getType().name().equals("RTT_EMPLOYER") && (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)) {
            throw new IllegalArgumentException("RTT cannot be set on weekends");
        }
    }

}
