package fr.diginamic.app;

import fr.diginamic.app.model.*;
import fr.diginamic.app.repository.DayOffRepository;
import fr.diginamic.app.repository.UserRepository;
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
	CommandLineRunner run(UserRepository userRepository, DayOffRepository dayOffRepository) {
		return args -> {
			User user = new User("Ciel", "Madrigal", "email", "password", Role.EMPLOYE, "token");
			userRepository.save(user);
			System.out.println("User inserted!");

			DayOff dayOff = new DayOff(LocalDate.of(2025,6,10),LocalDate.of(2025,6,12), "Vacances d'été", Type.PAID_DAY_OFF, Status.INITIAL);
			dayOffRepository.save(dayOff);
			System.out.println("DayOff inserted!");
		};
	}
}


