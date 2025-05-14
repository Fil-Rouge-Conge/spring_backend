package fr.diginamic.app.controller;

import fr.diginamic.app.dto.PersonalDayOffDto;
import fr.diginamic.app.dto.PersonalDayOffMapper;
import fr.diginamic.app.model.PersonalDayOff;
import fr.diginamic.app.service.PersonalDayOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal-day-offs")
public class PersonalDayOffController {

    @Autowired
    private PersonalDayOffService service;

    @GetMapping
    public List<PersonalDayOff> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PersonalDayOff getById(@PathVariable Long id){
        return service.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<PersonalDayOffDto> create(@RequestBody PersonalDayOffDto dto) {
        PersonalDayOff entity = PersonalDayOffMapper.toEntity(dto);
        PersonalDayOff saved = service.save(entity);
        return ResponseEntity.ok(PersonalDayOffMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalDayOffDto> update(@PathVariable Long id, @RequestBody PersonalDayOffDto dto) {
        PersonalDayOff entity = PersonalDayOffMapper.toEntity(dto);
        PersonalDayOff updated = service.update(id, entity);
        return ResponseEntity.ok(PersonalDayOffMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public List<PersonalDayOffDto> getByUser(@PathVariable Long userId) {
        return service.findByUserId(userId).stream()
                .map(PersonalDayOffMapper::toDto)
                .toList();
    }
}
