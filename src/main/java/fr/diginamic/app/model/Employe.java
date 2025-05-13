package fr.diginamic.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EMPLOYE")
public class Employe extends User {

    @Column(name = "daysoff_balance")
    private float daysoffBalance;

    @Column(name = "empl_rtt_balance")
    private float emplRttBalance;

    public Employe() {
        super();
    }

    public Employe(float daysoffBalance, float emplRttBalance) {}

    public Employe(String lastName, String firstName, String email, String password, Role role, String token, float daysoffBalance, float emplRttBalance){
        super(lastName, firstName, email, password, role, token);
        this.daysoffBalance = daysoffBalance;
        this.emplRttBalance = emplRttBalance;
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
}
