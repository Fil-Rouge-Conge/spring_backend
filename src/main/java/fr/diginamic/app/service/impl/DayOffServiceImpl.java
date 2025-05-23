package fr.diginamic.app.service.impl;

import fr.diginamic.app.model.*;
import fr.diginamic.app.repository.DayOffRepository;
import fr.diginamic.app.repository.EmployeeRepository;
import fr.diginamic.app.service.DayOffService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
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
    public List<DayOff> findByStatusOrderByBeginningDateAsc(Status status) {
        return dayOffRepository.findByStatusOrderByBeginningDateAsc(status);
    }

    public void processCommonDayOff(CommonDayOff commonDayOff, long nbDays) {
        System.out.println("Type : common day off");
        if (CommonDayOffType.RTT_EMPLOYER.equals((commonDayOff).getType())) {
            System.out.println("C'est bien un RTT Employer");
            commonDayOff.setStatus(Status.APPROVED);
            dayOffRepository.save(commonDayOff);
            List<Employee> employees = employeeRepository.findAll();

            for (Employee employee : employees) {
                float current = employee.getEmplRttBalance();
                System.out.println("Email Employé : " + employee.getEmail());
                System.out.println("Current RTT balance BEFORE : " + current);
                employee.setEmplRttBalance(Math.max(0, current - (float) nbDays));
                System.out.println("Current RTT balance AFTER : " + employee.getEmplRttBalance());
            }
            employeeRepository.saveAll(employees);
        }
    }

    public void processPersonalDayOff(PersonalDayOff personalDayOff, long nbDays) {
        System.out.println("Type : " + "Personal Day Off");

        Employee employee = (personalDayOff).getEmployee();

        switch ((personalDayOff).getType()) {
            case RTT_EMPLOYEE -> {
                if (employee.getEmplRttBalance() >= nbDays) {
                    personalDayOff.setStatus(Status.WAITING_FOR_APPROVAL);
                    employee.setEmplRttBalance(Math.max(0, employee.getEmplRttBalance() - (float) nbDays));
                    // TODO : envoyer email manager
                }
            }
            case PAID_DAY_OFF -> {
                if (employee.getDaysoffBalance() >= nbDays) {
                    personalDayOff.setStatus(Status.WAITING_FOR_APPROVAL);
                    employee.setDaysoffBalance(Math.max(0, employee.getDaysoffBalance() - (float) nbDays));
                    // TODO : envoyer email manager
                }
            }
            case UNPAID_DAY_OFF -> {
                personalDayOff.setStatus(Status.WAITING_FOR_APPROVAL);
                employee.setDaysoffBalance(Math.max(0, employee.getDaysoffBalance() - (float) nbDays));
                // TODO : envoyer email manager
            }
            default -> personalDayOff.setStatus(Status.DENIED);
        }
    }

    @Override
    @Transactional
    public List<DayOff> processInitialDaysOff() {
        List<DayOff> daysoff = findByStatusOrderByBeginningDateAsc(Status.INITIAL);

        for (DayOff dayOff : daysoff) {
            long nbDays = ChronoUnit.DAYS.between(
                    dayOff.getBeginningDate(),
                    dayOff.getEndDate()
            ) + 1;
            System.out.println("Nombre de jours à décrémenter : " + nbDays);

            if (dayOff instanceof CommonDayOff) {
                processCommonDayOff((CommonDayOff) dayOff, nbDays);
            } else if (dayOff instanceof PersonalDayOff) {
                processPersonalDayOff((PersonalDayOff) dayOff, nbDays);
            }
        }
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
