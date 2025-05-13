package fr.diginamic.app.repository;

import fr.diginamic.app.model.PersonalDayOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDayOffRepository extends JpaRepository<PersonalDayOff, Long> {

}
