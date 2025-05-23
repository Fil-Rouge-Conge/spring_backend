package fr.diginamic.app;

import fr.diginamic.app.model.*;
import fr.diginamic.app.repository.CommonDayOffRepository;
import fr.diginamic.app.repository.DayOffRepository;
import fr.diginamic.app.repository.EmployeeRepository;
import fr.diginamic.app.repository.PersonalDayOffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class AppApplication {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(EmployeeRepository employeeRepository, DayOffRepository dayOffRepository, PersonalDayOffRepository personalDayOffRepository, CommonDayOffRepository commonDayOffRepository) {
		return args -> {
			Employee managerIt = new Employee("Ciel", "Madrigal", "coralieruiz.dev@gmail.com", encoder.encode("p@ssw0rd"), Role.MANAGER, Departement.IT, 7, 0);
			employeeRepository.save(managerIt);
			Employee emplIt1 = new Employee("To Heaven", "Stairway", "stairway@toheaven.gg", encoder.encode("heaven"), Role.EMPLOYEE,Departement.IT , 15, 10);
			employeeRepository.save(emplIt1);
			Employee emplIt2 = new Employee("Moissa", "Matt", "matt@moissa.gg", encoder.encode("moissa"), Role.EMPLOYEE, Departement.IT, 0, 0);
			employeeRepository.save(emplIt2);
			Employee adminHr = new Employee("Rouille", "Pat", "pat@rouille.gg", encoder.encode("Patrouille"), Role.ADMIN, Departement.HR , 5, 7);
			employeeRepository.save(adminHr);

			PersonalDayOff personalDayOff1 = new PersonalDayOff(
					LocalDate.of(2025, 8, 15),LocalDate.of(2025, 8, 15),"Raison perso", Status.INITIAL, PersonalDayOffType.RTT_EMPLOYEE);
			personalDayOff1.setEmployee(emplIt1);
			personalDayOffRepository.save(personalDayOff1);

			PersonalDayOff personalDayOff2 = new PersonalDayOff(
					LocalDate.of(2025, 7, 15),LocalDate.of(2025, 7, 29),"Vacances été", Status.INITIAL, PersonalDayOffType.PAID_DAY_OFF);
			personalDayOff2.setEmployee(emplIt1);
			personalDayOffRepository.save(personalDayOff2);

			PersonalDayOff personalDayOff3 = new PersonalDayOff(
					LocalDate.of(2025, 4, 18),LocalDate.of(2025, 4, 18),"Raison perso", Status.INITIAL, PersonalDayOffType.PAID_DAY_OFF);
			personalDayOff3.setEmployee(emplIt2);
			personalDayOffRepository.save(personalDayOff3);

			PersonalDayOff personalDayOff4 = new PersonalDayOff(
					LocalDate.of(2025, 3, 12),LocalDate.of(2025, 3, 12),"Raison perso", Status.WAITING_FOR_APPROVAL, PersonalDayOffType.PAID_DAY_OFF);
			personalDayOff4.setEmployee(emplIt2);
			personalDayOffRepository.save(personalDayOff4);

			CommonDayOff commonDayOff = new CommonDayOff(
					LocalDate.of(2025, 12, 25),LocalDate.of(2025, 12, 25),"Jour de Noël",Status.APPROVED,"Noël", CommonDayOffType.HOLIDAY);
			commonDayOffRepository.save(commonDayOff);
		};
	}
}


