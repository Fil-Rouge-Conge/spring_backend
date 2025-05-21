package fr.diginamic.app.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeTest {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Test
    void testGetEmployeeLastName() {
        Employee employee = new Employee("Moissa", "Matt", "matt@moissa.gg", encoder.encode("moissa"), Role.MANAGER, Departement.IT, 10, 1);

        Employee employeetest = new Employee();
        assertEquals("Moissa", employee.getLastName(), "The name should be Moissa");
    }

    @Test
    void testGetEmployeeFirstName() {
        Employee employee = new Employee("Moissa", "Matt", "matt@moissa.gg", encoder.encode("moissa"), Role.MANAGER, Departement.IT, 10, 1);

        Employee employeetest = new Employee();
        assertEquals("Matt", employee.getFirstName(), "The name should be Matt");
    }
}
