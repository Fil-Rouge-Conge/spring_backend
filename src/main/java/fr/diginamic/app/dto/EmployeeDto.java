package fr.diginamic.app.dto;

import fr.diginamic.app.model.Departement;
import fr.diginamic.app.model.Role;

public class EmployeeDto {
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private Departement departement;
    private Role role;
    private float daysoffBalance;
    private float emplRttBalance;

    /**
     * Setter
     */
    public void setId(Long id) { this.id = id; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setDepartement(Departement departement) { this.departement = departement; }
    public void setRole(Role role) { this.role = role; }
    public void setDaysoffBalance(float dayoffBalance) { this.daysoffBalance = dayoffBalance; }
    public void setEmplRttBalance(float emplRttBalance) { this.emplRttBalance = emplRttBalance; }

    /**
     * getter
     */
    public Long getId() { return id; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Departement getDepartement() { return departement; }
    public Role getRole() { return role; }
    public float getDaysoffBalance() { return daysoffBalance; }
    public float getEmplRttBalance() { return emplRttBalance; }
}
