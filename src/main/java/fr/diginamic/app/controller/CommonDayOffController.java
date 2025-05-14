package fr.diginamic.app.controller;

import fr.diginamic.app.model.CommonDayOff;
import fr.diginamic.app.service.CommonDayOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/common-day-offs")
public class CommonDayOffController {

    @Autowired
    private CommonDayOffService service;

    @GetMapping
    public List<CommonDayOff> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CommonDayOff getById(@PathVariable Long id){
        return service.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<CommonDayOff> create(@RequestBody CommonDayOff commonDayOff){
        return ResponseEntity.ok(service.save(commonDayOff));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonDayOff> update(@PathVariable Long id, @RequestBody CommonDayOff commonDayOff) {
        return ResponseEntity.ok(service.update(id, commonDayOff));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
