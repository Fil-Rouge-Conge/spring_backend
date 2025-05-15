package fr.diginamic.app.service.impl;

import fr.diginamic.app.model.Departement;
import fr.diginamic.app.model.Employee;
import fr.diginamic.app.model.Role;
import fr.diginamic.app.repository.EmployeeRepository;
import fr.diginamic.app.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee user) {
        return employeeRepository.save(user);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findByDepartement(Departement departement) {
        return employeeRepository.findByDepartement(departement);
    }

    public List<Employee> findByRole(Role role) {
        return employeeRepository.findByRole(role);
    }

    @Override
    public Employee update(Long id, Employee updatedEmployee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employé introuvable avec l'id : " + id));

        existing.setLastName(updatedEmployee.getLastName());
        existing.setFirstName(updatedEmployee.getFirstName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setPassword(updatedEmployee.getPassword());
        existing.setRole(updatedEmployee.getRole());
        existing.setDepartement(updatedEmployee.getDepartement());
        existing.setDaysoffBalance(updatedEmployee.getDaysoffBalance());
        existing.setEmplRttBalance(updatedEmployee.getEmplRttBalance());

        return employeeRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
