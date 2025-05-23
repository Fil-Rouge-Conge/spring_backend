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

			Employee user = new Employee("Ciel", "Madrigal", "ciel.m@gmail.com", encoder.encode("p@ssw0rd"), Role.EMPLOYEE);
			employeeRepository.save(user);
			System.out.println("User inserted!");
			Employee empl = new Employee("To Heaven", "Stairway", "stairway@toheaven.gg", encoder.encode("heaven"), Role.EMPLOYEE,Departement.IT , 2, 1);
			employeeRepository.save(empl);
			System.out.println("Employe inserted!");
			Employee mng = new Employee("Moissa", "Matt", "matt@moissa.gg", encoder.encode("moissa"), Role.MANAGER, Departement.IT, 10, 1);
			employeeRepository.save(mng);
			System.out.println("Manager inserted!");
			Employee adm = new Employee("Rouille", "Pat", "pat@rouille.gg", encoder.encode("Patrouille"), Role.ADMIN, Departement.HR , 5, 0);
			employeeRepository.save(adm);

			PersonalDayOff personalDayOff1 = new PersonalDayOff(
					LocalDate.of(2025, 8, 15),LocalDate.of(2025, 8, 15),"Raison perso", Status.INITIAL, PersonalDayOffType.RTT_EMPLOYEE);
			personalDayOff1.setEmployee(empl);
			personalDayOffRepository.save(personalDayOff1);
			System.out.println("PersonalDayOff1 inserted!");

			PersonalDayOff personalDayOff2 = new PersonalDayOff(
					LocalDate.of(2025, 7, 15),LocalDate.of(2025, 7, 29),"Vacances été", Status.WAITING_FOR_APPROVAL, PersonalDayOffType.PAID_DAY_OFF);
			personalDayOff2.setEmployee(empl);
			personalDayOffRepository.save(personalDayOff2);
			System.out.println("PersonalDayOff2 inserted!");

			CommonDayOff commonDayOff = new CommonDayOff(
					LocalDate.of(2025, 12, 25),LocalDate.of(2025, 12, 25),"Jour de Noël",Status.APPROVED,"Noël", CommonDayOffType.HOLIDAY);
			commonDayOffRepository.save(commonDayOff);
			System.out.println("CommonDayOff inserted!");
			System.out.println(employeeRepository.findByEmail("matt@moissa.gg"));
			System.out.println("employee found");

		};
	}
}


