package fr.diginamic.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "departement")
    private Departement departement;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "daysoff_balance")
    private float daysoffBalance;

    @Column(name = "empl_rtt_balance")
    private float emplRttBalance;

    @Column(name="token", unique=true)
    private String token;


    public Employee(){}

    public Employee(String lastName, String firstName, String email, String password, Role role, String token) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.token = token;
    }

    public Employee(String lastName, String firstName, String email, String password, Role role, Departement departement, String token, float daysoffBalance, float emplRttBalance) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.departement = departement;
        this.token = token;
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

    public String getToken() {
        return token;
    }

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

    public void setToken(String token) {
        this.token = token;
    }

    //TODO
    // Mettre un retour en boolean pour vérifier que tout c'est bien passé ?
    public void add_dayoff_req(){}

    //TODO
    public void check_dayoff_req(){}

    //TODO
    // Mettre un retour en boolean pour vérifier que tout c'est bien passé ?
    public void edit_dayoff_req(){}

    //TODO
    // Mettre un retour en boolean pour vérifier que tout c'est bien passé ?
    public void remove_dayoff_req(){}

    //TODO
    public void check_empl_req(){}

    //TODO
    public void deny_employe_req(){}

    //TODO
    public void get_histogram_by_month_and_dpt(){}

    //TODO
    public void export_mana_view_excel(){}

    //TODO
    public void get_mana_view_by_month_and_dpt(){}

    //TODO
    public void create_common_dayoff(){}

    //TODO
    public void edit_common_dayoff(){}

    //TODO
    public void del_common_dayoff(){}
}
