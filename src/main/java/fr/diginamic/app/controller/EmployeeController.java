package fr.diginamic.app.controller;

import fr.diginamic.app.dto.EmployeeDto;
import fr.diginamic.app.dto.EmployeeMapper;
import fr.diginamic.app.model.DayOff;
import fr.diginamic.app.model.Departement;
import fr.diginamic.app.model.Employee;
import fr.diginamic.app.model.Role;
import fr.diginamic.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service de gestion des employés.
 * Fournit les opérations CRUD de base.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeService;

    /**
     * Crée un nouvel employé à partir des données du body reçues en JSON.
     *
     * @param employeDto L'objet DTO représentant les données de l'employé à créer.
     * @return L'employé créé, converti en DTO.
     */
    @PostMapping
    @Secured("ROLE_ADMIN")
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        employeDto.setPassword(encoder.encode(employeDto.getPassword()));
        Employee employee = EmployeeMapper.toEntity(employeDto);
        return EmployeeMapper.toDto(employeService.save(employee));
    }

    /**
     * Permet de récupérer la liste complet des employé dans la BDD
     * @return Liste des employé
     */
    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public List<EmployeeDto> getAllEmployes() {
        return employeService.findAll()
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupere un employé
     * @param id ID de l'employé recherché dans la BDD
     * @return un Employé
     */
    @GetMapping("/id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeService.findById(id)
                .map(EmployeeMapper::toDto)
                .orElse(null);
    }

    /**
     * Récupère la liste des employés selon leur role
     * @param role nom du role
     * @return list d'employé
     */
    @GetMapping("/role/{role}")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public List<EmployeeDto> getEmployeeByRole(@PathVariable Role role) {
        return employeService.findByRole(role)
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupère la liste des employés selon leur département
     * @param dept om du département
     * @return liste d'employé
     */
    @GetMapping("/departement/{dept}")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public List<EmployeeDto> getEmployeeByDepartment(@PathVariable Departement dept) {
        return employeService.findByDepartement(dept)
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupere le solde des jours de congé payéee
     * @param auth
     * @return
     */
    @GetMapping("/soldeConge")
    @Secured({"ROLE_EMPLOYEE","ROLE_MANAGER","ROLE_ADMIN"})
    public float getSoldeConge(Authentication auth) {
        Employee empl = employeService.findByEmail(auth.getName()).orElseThrow();
        return empl.getDaysoffBalance();
    }

    /**
     * Récupere le solde des RTT
     * @param auth
     * @return
     */
    @GetMapping("/soldeRTT")
    @Secured({"ROLE_EMPLOYEE","ROLE_MANAGER","ROLE_ADMIN"})
    public float getSoldeRTT(Authentication auth) {
        Employee empl = employeService.findByEmail(auth.getName()).orElseThrow();
        return empl.getEmplRttBalance();
    }

    @PutMapping("/id/{id}")
    @Secured("ROLE_ADMIN")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (employeDto.getPassword() != null && !employeDto.getPassword().isBlank()) {
            employeDto.setPassword(encoder.encode(employeDto.getPassword()));
        } else {
            Employee existing = employeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found for id " + id));
            employeDto.setPassword(existing.getPassword());
        }
        Employee updated = employeService.update(id, EmployeeMapper.toEntity(employeDto));
        return EmployeeMapper.toDto(updated);
    }

    /**
     * Supprime un employé par son ID
     * @param id id de l'employé à supprimé
     */
    @DeleteMapping("/id/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteEmployee(@PathVariable Long id) {
        employeService.delete(id);
    }
}
