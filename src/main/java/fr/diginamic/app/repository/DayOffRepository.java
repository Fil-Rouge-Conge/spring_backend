package fr.diginamic.app.repository;

import fr.diginamic.app.model.DayOff;
import fr.diginamic.app.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long> {
    List<DayOff> findByStatusOrderByBeginningDateAsc(Status status);
}
