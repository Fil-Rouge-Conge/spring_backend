package fr.diginamic.app.repository;

import fr.diginamic.app.model.Departement;
import fr.diginamic.app.model.PersonalDayOff;
import fr.diginamic.app.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalDayOffRepository extends JpaRepository<PersonalDayOff, Long> {
    List<PersonalDayOff> findByEmployee_Id(Long employeeId);
    List<PersonalDayOff> findByEmployee_DepartementAndStatusIn(Departement departement, List<Status> statuses);
}
