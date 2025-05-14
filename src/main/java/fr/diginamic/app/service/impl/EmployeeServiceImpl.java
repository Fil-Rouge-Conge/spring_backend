package fr.diginamic.app.service.impl;

import fr.diginamic.app.model.Departement;
import fr.diginamic.app.model.Employee;
import fr.diginamic.app.model.Role;
import fr.diginamic.app.repository.EmployeeRepository;
import fr.diginamic.app.service.EmployeeService;
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

    public List<Employee> findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    public List<Employee> findByLastName(String name){
        return employeeRepository.findByLastName(name);
    }

    public Optional<Employee> findByEmail(String email){
        return employeeRepository.findByEmail(email);
    }

    public List<Employee> findByEmailContaining(String email) {
        return employeeRepository.findByEmailContaining(email);
    }

    public List<Employee> findByDepartement(Departement departement) {
        return employeeRepository.findByDepartement(departement);
    }

    public List<Employee> findByRole(Role role) {
        return employeeRepository.findByRole(role);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
