package fr.diginamic.app.service;

import fr.diginamic.app.model.Departement;
import fr.diginamic.app.model.Employee;
import fr.diginamic.app.model.Role;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee save(Employee user);
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
    Optional<Employee> findByEmail(String email);
    List<Employee> findByEmailContaining(String email);
    List<Employee> findByRole(Role role);
    List<Employee> findByDepartement(Departement department);
    void delete(Long id);
}
