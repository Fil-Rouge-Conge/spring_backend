package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("MANAGER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager extends User {
    // Ajouter ici des attributs propres au Manager si besoin.
}
