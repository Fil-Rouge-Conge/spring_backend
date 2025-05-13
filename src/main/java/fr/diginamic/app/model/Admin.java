package fr.diginamic.app.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Employe {

    public Admin() {
        super();
    }

    public Admin(String lastName, String firstName, String email, String password, Role role, String token, float daysoffBalance, float emplRttBalance) {
        super(lastName, firstName, email, password, role, token, daysoffBalance, emplRttBalance);
    }

    //TODO
    public void create_common_dayoff(){}

    //TODO
    public void edit_common_dayoff(){}

    //TODO
    public void del_common_dayoff(){}
}

