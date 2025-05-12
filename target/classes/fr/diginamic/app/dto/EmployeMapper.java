package com.example.demo.dot;

import com.example.demo.model.Employe;

public class EmployeMapper {

    public static EmployeDto toDto(Employe employe) {
        EmployeDto dto = new EmployeDto();
        dto.setId(employe.getId());
        dto.setFirstName(employe.getFirstName());
        dto.setLastName(employe.getLastName());
        dto.setEmail(employe.getEmail());
        dto.setDaysoffBalance(employe.getDaysoffBalance());
        dto.setEmplRttBalance(employe.getEmplRttBalance());
        return dto;
    }

    public static Employe toEntity(EmployeDto dto) {
        Employe employe = new Employe();
        employe.setId(dto.getId());
        employe.setFirstName(dto.getFirstName());
        employe.setLastName(dto.getLastName());
        employe.setEmail(dto.getEmail());
        employe.setDaysoffBalance(dto.getDaysoffBalance());
        employe.setEmplRttBalance(dto.getEmplRttBalance());
        return employe;
    }
}
