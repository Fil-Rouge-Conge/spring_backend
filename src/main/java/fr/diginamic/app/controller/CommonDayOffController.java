package fr.diginamic.app.controller;

import fr.diginamic.app.model.CommonDayOff;
import fr.diginamic.app.service.CommonDayOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Service de gestion des jours de congés communs des employés.
 * Fournit les opérations CRUD de base.
 */
@RestController
@RequestMapping("/api/common-day-offs")
public class CommonDayOffController {

    @Autowired
    private CommonDayOffService commonDayOffService;

    /**
     * Récupère tous les jours de congé communs.
     *
     * @return une liste de CommonDayOff
     */
    @GetMapping
    @Secured({"ROLE_EMPLOYEE","ROLE_MANAGER","ROLE_ADMIN"})
    public List<CommonDayOff> getAll(){
        return commonDayOffService.findAll();
    }

    /**
     * Récupère un jour de congé commun par son identifiant.
     *
     * @param id l'identifiant du jour de congé commun
     * @return l'objet CommonDayOff correspondant, ou null s'il n'existe pas
     */
    @GetMapping("/id/{id}")
    @Secured({"ROLE_MANAGER","ROLE_ADMIN"})
    public CommonDayOff getById(@PathVariable Long id){
        return commonDayOffService.findById(id).orElse(null);
    }

    /**
     * Crée un nouveau jour de congé commun.
     *
     * @param commonDayOff l'objet commonDayOff à créer
     * @return la réponse contenant le jour de congé commun créé
     */
    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<CommonDayOff> create(@RequestBody CommonDayOff commonDayOff){
        return ResponseEntity.ok(commonDayOffService.save(commonDayOff));
    }

    /**
     * Met à jour un jour de congé commun existant.
     *
     * @param id l'identifiant du jour de congé à mettre à jour
     * @param commonDayOff les nouvelles données à appliquer
     * @return la réponse contenant le jour de congé mis à jour
     */
    @PutMapping("/id/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<CommonDayOff> update(@PathVariable Long id, @RequestBody CommonDayOff commonDayOff) {
        return ResponseEntity.ok(commonDayOffService.update(id, commonDayOff));
    }

    /**
     * Supprime un jour de congé commun par son identifiant.
     *
     * @param id l'identifiant du jour de congé à supprimer
     * @return une réponse HTTP sans contenu si la suppression a réussi
     */
    @DeleteMapping("/id/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commonDayOffService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
