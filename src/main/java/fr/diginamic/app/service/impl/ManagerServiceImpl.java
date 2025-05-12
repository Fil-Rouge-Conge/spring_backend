//package fr.diginamic.app.service.impl;
//
//import fr.diginamic.app.model.Manager;
//import fr.diginamic.app.repository.ManagerRepository;
//import fr.diginamic.app.service.ManagerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ManagerServiceImpl implements ManagerService {
//
//    @Autowired
//    private ManagerRepository managerRepository;
//
//    @Override
//    public Manager save(Manager manager) {
//        return managerRepository.save(manager);
//    }
//
//    @Override
//    public List<Manager> findAll() {
//        return managerRepository.findAll();
//    }
//
//    @Override
//    public Optional<Manager> findById(Long id) {
//        return managerRepository.findById(id);
//    }
//
//    @Override
//    public void delete(Long id) {
//        managerRepository.deleteById(id);
//    }
//}
