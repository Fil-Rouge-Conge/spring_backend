package com.example.demo.service.impl;

import com.example.demo.model.Employe;
import com.example.demo.repository.EmployeRepository;
import com.example.demo.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeServiceImpl implements EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public Employe save(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public Optional<Employe> findById(Long id) {
        return employeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        employeRepository.deleteById(id);
    }
}
