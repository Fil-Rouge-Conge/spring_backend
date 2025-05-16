package fr.diginamic.app.controller;

import fr.diginamic.app.dto.PersonalDayOffDto;
import fr.diginamic.app.dto.PersonalDayOffMapper;
import fr.diginamic.app.model.Employee;
import fr.diginamic.app.model.PersonalDayOff;
import fr.diginamic.app.model.Status;
import fr.diginamic.app.service.EmployeeService;
import fr.diginamic.app.service.PersonalDayOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Service de gestion des jours de congés personnels des employés.
 * Fournit les opérations CRUD de base.
 */
@RestController
@RequestMapping("/api/personal-day-offs")
public class PersonalDayOffController {

    @Autowired
    private PersonalDayOffService personalDayOffService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Récupère tous les jours de congé personnels.
     * @return une liste de PersonalDayOff
     */
    @GetMapping
    @Secured({"ROLE_MANAGER","ROLE_ADMIN"})
    public List<PersonalDayOff> getAll(){
        return personalDayOffService.findAll();
    }

    /**
     * Récupère un jour de congé personnel à partir de son identifiant.
     *
     * @param id l'identifiant du jour de congé personnel
     * @return l'objet PersonalDayOff correspondant, ou null s'il n'existe pas
     */
    @GetMapping("/id/{id}")
    @Secured({"ROLE_EMPLOYEE","ROLE_MANAGER","ROLE_ADMIN"})
    public PersonalDayOff getById(@PathVariable Long id){
        return personalDayOffService.findById(id).orElse(null);
    }

    /**
     * Crée un nouveau jour de congé personnel à partir d’un DTO.
     *
     * @param dto le DTO représentant les données du congé personnel
     * @param auth token d'identification de l'utilisateur connecté
     * @return le DTO du jour de congé personnel sauvegardé
     */
    @PostMapping
    @Secured({"ROLE_EMPLOYEE","ROLE_MANAGER","ROLE_ADMIN"})
    public ResponseEntity<PersonalDayOffDto> create(@RequestBody PersonalDayOffDto dto, Authentication auth) {
        PersonalDayOff entity = PersonalDayOffMapper.toEntity(dto);
        entity.setStatus(Status.INITIAL);
        //récupération de l'employé par sa connection
        Employee empl = employeeService.findByEmail(auth.getName()).orElseThrow();
        entity.setEmployee(empl);
        PersonalDayOff saved = personalDayOffService.save(entity);
        return ResponseEntity.ok(PersonalDayOffMapper.toDto(saved));
    }

    /**
     * Récupere les jours de congé de l'utilisateur connecté
     * @param auth token d'identification de l'utilisateur connecté
     * @return
     */
    @GetMapping("/myPersonalDayOffs")
    @Secured({"ROLE_EMPLOYEE","ROLE_MANAGER","ROLE_ADMIN"})
    public List<PersonalDayOffDto> getPersonalDayOffs(Authentication auth) {
        Employee empl = employeeService.findByEmail(auth.getName()).orElseThrow();
        return personalDayOffService.findByEmployeeId(empl.getId()).stream()
                .map(PersonalDayOffMapper::toDto)
                .toList();
    }

    /**
     * Met à jour un jour de congé personnel existant.
     *
     * @param id  l'identifiant du jour de congé à mettre à jour
     * @param dto les nouvelles données à appliquer
     * @return le DTO du jour de congé personnel mis à jour
     */
    @PutMapping("/id/{id}")
    @Secured({"ROLE_EMPLOYEE","ROLE_MANAGER","ROLE_ADMIN"})
    public ResponseEntity<PersonalDayOffDto> update(@PathVariable Long id, @RequestBody PersonalDayOffDto dto) {
        PersonalDayOff entity = PersonalDayOffMapper.toEntity(dto);
        PersonalDayOff pdayoff = personalDayOffService.findById(id).orElseThrow();
        entity.setEmployee(pdayoff.getEmployee());
        PersonalDayOff updated = personalDayOffService.update(id, entity);
        return ResponseEntity.ok(PersonalDayOffMapper.toDto(updated));
    }

    /**
     * Supprime un jour de congé personnel à partir de son identifiant.
     *
     * @param id l'identifiant du jour de congé à supprimer
     * @return une réponse HTTP sans contenu si la suppression a réussi
     */
    @DeleteMapping("/id/{id}")
    @Secured({"ROLE_EMPLOYEE","ROLE_MANAGER","ROLE_ADMIN"})
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personalDayOffService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupère les jours de congé personnels associés à un utilisateur donné.
     *
     * @param employeeId l'identifiant de l'utilisateur
     * @return une liste de PersonalDayOffDto associés à l'utilisateur
     */
    @GetMapping("/employee/{employeeId}")
    @Secured({"ROLE_MANAGER","ROLE_ADMIN"})
    public List<PersonalDayOffDto> getByEmployee(@PathVariable Long employeeId) {
        return personalDayOffService.findByEmployeeId(employeeId).stream()
                .map(PersonalDayOffMapper::toDto)
                .toList();
    }
}
