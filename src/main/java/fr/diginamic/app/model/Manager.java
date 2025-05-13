package fr.diginamic.app.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends Employe {

    public Manager() {
        super();
    }

    public Manager(String lastName, String firstName, String email, String password, Role role, String token, float daysoffBalance, float emplRttBalance){
        super(lastName, firstName, email, password, role, token, daysoffBalance, emplRttBalance);
    }

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
}