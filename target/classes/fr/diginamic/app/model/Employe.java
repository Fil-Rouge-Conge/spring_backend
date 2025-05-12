package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("EMPLOYE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employe extends User {

    @Column(name = "daysoff_balance")
    private float daysoffBalance;

    @Column(name = "empl_rtt_balance")
    private float emplRttBalance;
}
