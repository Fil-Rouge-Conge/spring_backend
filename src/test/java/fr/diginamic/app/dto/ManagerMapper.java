package com.example.demo.dot;

import com.example.demo.model.Manager;

public class ManagerMapper {

    public static ManagerDto toDto(Manager manager) {
        ManagerDto dto = new ManagerDto();
        dto.setId(manager.getId());
        dto.setFirstName(manager.getFirstName());
        dto.setLastName(manager.getLastName());
        dto.setEmail(manager.getEmail());
        return dto;
    }

    public static Manager toEntity(ManagerDto dto) {
        Manager manager = new Manager();
        manager.setId(dto.getId());
        manager.setFirstName(dto.getFirstName());
        manager.setLastName(dto.getLastName());
        manager.setEmail(dto.getEmail());
        return manager;
    }
}
