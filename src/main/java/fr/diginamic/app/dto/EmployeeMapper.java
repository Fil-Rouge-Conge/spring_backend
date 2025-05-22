package fr.diginamic.app.dto;

import fr.diginamic.app.model.Employee;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setRole(employee.getRole());
        dto.setDepartement(employee.getDepartement());
        dto.setDaysoffBalance(employee.getDaysoffBalance());
        dto.setEmplRttBalance(employee.getEmplRttBalance());
        return dto;
    }

    public static Employee toEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPassword(dto.getPassword());
        employee.setRole(dto.getRole());
        employee.setDepartement(dto.getDepartement());
        employee.setDaysoffBalance(dto.getDaysoffBalance());
        employee.setEmplRttBalance(dto.getEmplRttBalance());
        return employee;
    }
}
