package fr.diginamic.app.repository;

import fr.diginamic.app.model.CommonDayOff;
import fr.diginamic.app.model.CommonDayOffType;
import fr.diginamic.app.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommonDayOffRepository extends JpaRepository<CommonDayOff, Long> {
    List<CommonDayOff> findAllByBeginningDateBetween(LocalDate start, LocalDate end);
//    List<CommonDayOff> findByStatusAndType(Status status, CommonDayOffType type);
    boolean existsByBeginningDate(LocalDate date);
}
