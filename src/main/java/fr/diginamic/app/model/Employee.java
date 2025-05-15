package fr.diginamic.app.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * Représente un employé
 * Le nom de sa table en base de données est " employees "
 */
@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "employee_type")
public class Employee {

    /**
     * Identifiant
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom de famille
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Prénom
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /***
     * Role
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    /**
     * Département
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "departement")
    private Departement departement;

    /**
     * Adresse mail
     */
    @Column(name="email", unique = true, nullable = false)
    private String email;

    /**
     * Mot de passe
     */
    @Column(name="password", nullable = false)
    private String password;

    /**
     * Solde de congé payé
     */
    @Column(name = "daysoff_balance")
    private float daysoffBalance;

    /**
     * Solde de RTT
     */
    @Column(name = "empl_rtt_balance")
    private float emplRttBalance;

    /**
     * Liste les jours de congé communs de l'employé
     */
    @ManyToMany(mappedBy = "employees")
    private List<CommonDayOff> commonDayOffList;

    /**
     * Liste les jour Personel de l'employé
     */
    @OneToMany( mappedBy = "employee", orphanRemoval = true)
    private List<PersonalDayOff> personalDayOffList;


    public Employee(){}

    public Employee(String lastName, String firstName, String email, String password, Role role) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Employee(String lastName, String firstName, String email, String password, Role role, Departement departement, float daysoffBalance, float emplRttBalance) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.departement = departement;
        this.daysoffBalance = daysoffBalance;
        this.emplRttBalance = emplRttBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Departement getDepartement() { return departement; }

    public void setDepartement(Departement departement) { this.departement = departement; }

    public float getDaysoffBalance() {
        return daysoffBalance;
    }

    public void setDaysoffBalance(float daysoffBalance) {
        this.daysoffBalance = daysoffBalance;
    }

    public float getEmplRttBalance() {
        return emplRttBalance;
    }

    public void setEmplRttBalance(float emplRttBalance) {
        this.emplRttBalance = emplRttBalance;
    }

    public List<CommonDayOff> getCommonDayOffList() {
        return commonDayOffList;
    }

    public void setCommonDayOffList(List<CommonDayOff> commonDayOffList) {
        this.commonDayOffList = commonDayOffList;
    }

    public List<PersonalDayOff> getPersonalDayOffList() {
        return personalDayOffList;
    }

    public void setPersonalDayOffList(List<PersonalDayOff> personalDayOffList) {
        this.personalDayOffList = personalDayOffList;
    }
}
