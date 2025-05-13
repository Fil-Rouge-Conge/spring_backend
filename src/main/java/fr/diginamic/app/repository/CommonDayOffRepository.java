package fr.diginamic.app.repository;

import fr.diginamic.app.model.CommonDayOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonDayOffRepository extends JpaRepository<CommonDayOff, Long> {
}
