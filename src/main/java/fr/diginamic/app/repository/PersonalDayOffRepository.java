package fr.diginamic.app.repository;

import fr.diginamic.app.model.Departement;
import fr.diginamic.app.model.PersonalDayOff;
import fr.diginamic.app.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonalDayOffRepository extends JpaRepository<PersonalDayOff, Long> {
    List<PersonalDayOff> findByEmployee_Id(Long employeeId);
    List<PersonalDayOff> findByEmployee_DepartementAndStatusIn(Departement departement, List<Status> statuses);

    @Query("""
        SELECT COUNT(p) FROM PersonalDayOff p
        WHERE p.employee.id = :employeeId
          AND (:excludeId IS NULL OR p.id <> :excludeId)
          AND p.beginningDate <= :endDate
          AND p.endDate >= :beginningDate
    """)
    long countOverlappingAbsences(@Param("employeeId") Long employeeId,
                                  @Param("beginningDate") LocalDate beginningDate,
                                  @Param("endDate") LocalDate endDate,
                                  @Param("excludeId") Long excludeId);
}
