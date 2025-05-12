package com.example.demo.service;

import com.example.demo.model.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    Manager save(Manager manager);
    List<Manager> findAll();
    Optional<Manager> findById(Long id);
    void delete(Long id);
}
