package com.example.demo.controller;

import com.example.demo.dot.ManagerDto;
import com.example.demo.dot.ManagerMapper;
import com.example.demo.model.Manager;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping
    public ManagerDto createManager(@RequestBody ManagerDto managerDto) {
        Manager manager = ManagerMapper.toEntity(managerDto);
        return ManagerMapper.toDto(managerService.save(manager));
    }

    @GetMapping
    public List<ManagerDto> getAllManagers() {
        return managerService.findAll()
                .stream()
                .map(ManagerMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ManagerDto getManagerById(@PathVariable Long id) {
        return managerService.findById(id)
                .map(ManagerMapper::toDto)
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteManager(@PathVariable Long id) {
        managerService.delete(id);
    }
}
