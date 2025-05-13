package fr.diginamic.app.repository;

import fr.diginamic.app.model.DayOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long> {

}
