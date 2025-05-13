package fr.diginamic.app.dto;

import fr.diginamic.app.model.Departement;
import fr.diginamic.app.model.Employee;
import fr.diginamic.app.model.Role;

public class EmployeeDto {
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private Departement departement;
    private Role role;
    private float daysoffBalance;
    private float emplRttBalance;
}
