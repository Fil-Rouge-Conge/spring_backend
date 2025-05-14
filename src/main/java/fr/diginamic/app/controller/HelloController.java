package fr.diginamic.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur de démonstration : permet de montrer 2 endpoints sécurisés en
 * fonction d'un rôle utilisateur
 */
@RestController
@RequestMapping("/api/salutations")
public class HelloController {

    /** Endpoint sécurisé pour les profils ADMIN
     * @return {@link ResponseEntity}
     */
    @GetMapping("/hello")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getHello() {
        return ResponseEntity.ok("Hello Admin");
    }

    /** Endpoint sécurisé pour les profils USER
     * @return {@link ResponseEntity}
     */
    @GetMapping("/hi")
//    @Secured("USER")
    @Secured("ROLE_EMPLOYEE")
    public ResponseEntity<?> getHi() {
        return ResponseEntity.ok("Hi Employee");
    }

    @GetMapping("/ave")
    public ResponseEntity<?> getAve() { return ResponseEntity.ok("Ave Cesar"); }
}