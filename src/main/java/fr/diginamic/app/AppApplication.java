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

import java.time.LocalDate;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository, DayOffRepository dayOffRepository, PersonalDayOffRepository personalDayOffRepository, CommonDayOffRepository commonDayOffRepository) {
		return args -> {
			Employee user = new Employee("Ciel", "Madrigal", "email", "password", Role.EMPLOYEE, "token");
			employeeRepository.save(user);
			System.out.println("User inserted!");
			Employee empl = new Employee("To Heaven", "Stairway", "stairway@toheaven.gg", "heaven", Role.EMPLOYEE,Departement.IT , "tokenToHeaven", 2, 1);
			employeeRepository.save(empl);
			System.out.println("Employe inserted!");
			Employee mng = new Employee("Moissa", "Matt", "matt@moissa.gg", "moissa", Role.MANAGER, Departement.IT, "MMoaiTSTSa", 10, 1);
			employeeRepository.save(mng);
			System.out.println("Manager inserted!");
			Employee adm = new Employee("Rouille", "Pat", "pat@rouille.gg", "Patrouille", Role.ADMIN, Departement.HR , "PTRLL", 5, 0);
			employeeRepository.save(adm);

			DayOff dayOff = new DayOff(LocalDate.of(2025,6,10),LocalDate.of(2025,6,12), "Vacances d'été", Status.INITIAL);
			dayOffRepository.save(dayOff);
			System.out.println("DayOff inserted!");

			PersonalDayOff personalDayOff = new PersonalDayOff(
					LocalDate.of(2025, 8, 15),LocalDate.of(2025, 8, 15),"Raison perso", Status.INITIAL, PersonalDayOffType.RTT_EMPLOYEE);
			personalDayOffRepository.save(personalDayOff);
			System.out.println("PersonalDayOff inserted!");

			CommonDayOff commonDayOff = new CommonDayOff(
					LocalDate.of(2025, 12, 25),LocalDate.of(2025, 12, 25),"Jour de Noël",Status.APPROVED,"Noël", CommonDayOffType.HOLIDAY);
			commonDayOffRepository.save(commonDayOff);
			System.out.println("CommonDayOff inserted!");
		};
	}
}


