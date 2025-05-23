package fr.diginamic.app.controller;

import fr.diginamic.app.service.DayOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dayoffs")
public class DayOffController {

    @Autowired
    private  DayOffService dayOffService;

    @PostMapping("/nightime-processing")
    public ResponseEntity<Void> processInitialDayOffs() {
        dayOffService.processInitialDaysOff();
        return ResponseEntity.ok().build(); // ou .noContent() si tu veux
    }
}