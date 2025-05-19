package fr.diginamic.app.service.impl;

import fr.diginamic.app.model.PersonalDayOff;
import fr.diginamic.app.model.PersonalDayOffType;
import fr.diginamic.app.model.Status;
import fr.diginamic.app.repository.PersonalDayOffRepository;
import fr.diginamic.app.service.PersonalDayOffService;
import jakarta.persistence.EntityNotFoundException;
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
    public List<PersonalDayOff> findByEmployeeId(Long employeeId) {
        return personalDayOffRepository.findByEmployee_Id(employeeId);
    }

    public PersonalDayOff update(Long id, PersonalDayOff updated){
        PersonalDayOff existing = personalDayOffRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Personal Day Off not found"));
        if (existing.getStatus() != Status.INITIAL && existing.getStatus() != Status.DENIED){
            throw new IllegalStateException("Impossible to update personal day off. Check Status");
        }
        validate(updated);
        return personalDayOffRepository.findById(id).map(existingDayOff -> {
            existingDayOff.setBeginningDate(updated.getBeginningDate());
            existingDayOff.setEndDate(updated.getEndDate());
            existingDayOff.setReason(updated.getReason());
            existingDayOff.setStatus(Status.INITIAL);
            existingDayOff.setType(updated.getType());
            return personalDayOffRepository.save(existingDayOff);
                }).orElseThrow(() -> new EntityNotFoundException("Personal Day Off not found"));
    }

    @Override
    public void delete(Long id) {
        PersonalDayOff existing = personalDayOffRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Personal Day Off not found"));
        // Ajouter la logique pour recréditer le solde des congés d'un employé?
        personalDayOffRepository.deleteById(id);
    }

    private void validate(PersonalDayOff absence) {
        if (absence.getBeginningDate() == null || absence.getEndDate() == null || absence.getType() == null) {
            throw new IllegalArgumentException("Mandatory Information required : Beginning Date, End Date and Type");
        }
        if (absence.getBeginningDate().isAfter(absence.getEndDate())) {
            throw new IllegalArgumentException("End Date must be after the Beginning Date");
        }
        if (absence.getType() == PersonalDayOffType.UNPAID_DAY_OFF && (absence.getReason() == null || absence.getReason().isEmpty())) {
            throw new IllegalArgumentException("Reason is mandatory for Unpaid Day Off");
        }
    }
}
