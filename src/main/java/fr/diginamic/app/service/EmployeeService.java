package fr.diginamic.app.service;

import fr.diginamic.app.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee save(Employee user);
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    void delete(Long id);
}
