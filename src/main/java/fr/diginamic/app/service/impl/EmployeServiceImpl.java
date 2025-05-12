//package fr.diginamic.app.service.impl;
//
//import fr.diginamic.app.model.Employe;
//import fr.diginamic.app.repository.EmployeRepository;
//import fr.diginamic.app.service.EmployeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EmployeServiceImpl implements EmployeService {
//
//    @Autowired
//    private EmployeRepository employeRepository;
//
//    @Override
//    public Employe save(Employe employe) {
//        return employeRepository.save(employe);
//    }
//
//    @Override
//    public List<Employe> findAll() {
//        return employeRepository.findAll();
//    }
//
//    @Override
//    public Optional<Employe> findById(Long id) {
//        return employeRepository.findById(id);
//    }
//
//    @Override
//    public void delete(Long id) {
//        employeRepository.deleteById(id);
//    }
//}
