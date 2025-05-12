package com.example.demo.service;

import com.example.demo.model.Employe;

import java.util.List;
import java.util.Optional;

public interface EmployeService {
    Employe save(Employe employe);
    List<Employe> findAll();
    Optional<Employe> findById(Long id);
    void delete(Long id);
}
