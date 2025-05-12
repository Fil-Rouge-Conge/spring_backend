package fr.diginamic.app;

import fr.diginamic.app.model.Role;
import fr.diginamic.app.model.User;
import fr.diginamic.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository) {
		return args -> {
			userRepository.save(new User("Wonderlands", "Alice", "a.wonderlands@gmail.com", "r@bbit", Role.EMPLOYE));
			System.out.println("User inserted!");
		};
	}

}
