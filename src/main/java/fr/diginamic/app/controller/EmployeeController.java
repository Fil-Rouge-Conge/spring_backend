package fr.diginamic.app.controller;

import fr.diginamic.app.dto.EmployeeDto;
import fr.diginamic.app.dto.EmployeeMapper;
import fr.diginamic.app.model.Departement;
import fr.diginamic.app.model.Employee;
import fr.diginamic.app.model.Role;
import fr.diginamic.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeService;

    @PostMapping
    public EmployeeDto createEmploye(@RequestBody EmployeeDto employeDto) {
        Employee employee = EmployeeMapper.toEntity(employeDto);
        return EmployeeMapper.toDto(employeService.save(employee));
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployes() {
        return employeService.findAll()
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeService.findById(id)
                .map(EmployeeMapper::toDto)
                .orElse(null);
    }

    @GetMapping("/lastname/{lastName}")
    public List<EmployeeDto> getEmployeeByLastName(@PathVariable String lastName) {
        return employeService.findByLastName(lastName)
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/firstname/{firstName}")
    public List<EmployeeDto> getEmployeeByFirstName(@PathVariable String firstName) {
        return employeService.findByFirstName(firstName)
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/email/{email}")
    public EmployeeDto getEmployeeByEmail(@PathVariable String email) {
        return employeService.findByEmail(email)
                .map(EmployeeMapper::toDto)
                .orElse(null);
    }

    @GetMapping("/emaillike/{email}")
    public List<EmployeeDto> getEmployeeByEmailContaining(@PathVariable String email) {
        return employeService.findByEmailContaining(email)
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/role/{role}")
    public List<EmployeeDto> getEmployeeByRole(@PathVariable Role role) {
        return employeService.findByRole(role)
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("departement/{dept}")
    public List<EmployeeDto> getEmployeeByDepartment(@PathVariable Departement dept) {
        return employeService.findByDepartement(dept)
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeService.delete(id);
    }
}
