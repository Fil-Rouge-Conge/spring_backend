package fr.diginamic.app.controller;

import fr.diginamic.app.dto.EmployeeDto;
import fr.diginamic.app.dto.EmployeeMapper;
import fr.diginamic.app.model.DayOff;
import fr.diginamic.app.model.Departement;
import fr.diginamic.app.model.Employee;
import fr.diginamic.app.model.Role;
import fr.diginamic.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public EmployeeDto createEmploye(@RequestBody EmployeeDto employeDto) {
        Employee employee = EmployeeMapper.toEntity(employeDto);
        return EmployeeMapper.toDto(employeService.save(employee));
    }

    /**
     * Permet de récupérer la liste complet des employé dans la BDD
     * @return Liste des employé
     */
    @GetMapping
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
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeService.findById(id)
                .map(EmployeeMapper::toDto)
                .orElse(null);
    }

    /**
     * Permet de cherché un employé par son email complet
     * @param email adresse mail
     * @return un Employé
     */
    @GetMapping("/email/{email}")
    public EmployeeDto getEmployeeByEmail(@PathVariable String email) {
        return employeService.findByEmail(email)
                .map(EmployeeMapper::toDto)
                .orElse(null);
    }

    /**
     * Permet de cherché un employé par un fragment de son adresse mail
     * @param email fragment adresse mail
     * @return liste d'employé
     */
    @GetMapping("/emaillike/{email}")
    public List<EmployeeDto> getEmployeeByEmailContaining(@PathVariable String email) {
        return employeService.findByEmailContaining(email)
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupère la liste des employés selon leur role
     * @param role nom du role
     * @return list d'employé
     */
    @GetMapping("/role/{role}")
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
    @GetMapping("departement/{dept}")
    public List<EmployeeDto> getEmployeeByDepartment(@PathVariable Departement dept) {
        return employeService.findByDepartement(dept)
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Supprime un employé par son ID
     * @param id id de l'employé à supprimé
     */
    @DeleteMapping("/id/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeService.delete(id);
    }
}
