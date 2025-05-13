package fr.diginamic.app;

import fr.diginamic.app.model.*;
import fr.diginamic.app.repository.DayOffRepository;
import fr.diginamic.app.repository.EmployeeRepository;
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
	CommandLineRunner run(EmployeeRepository employeeRepository, DayOffRepository dayOffRepository) {
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

			DayOff dayOff = new DayOff(LocalDate.of(2025,6,10),LocalDate.of(2025,6,12), "Vacances d'été", Type.PAID_DAY_OFF, Status.INITIAL);
			dayOffRepository.save(dayOff);
			System.out.println("DayOff inserted!");
		};
	}
}


