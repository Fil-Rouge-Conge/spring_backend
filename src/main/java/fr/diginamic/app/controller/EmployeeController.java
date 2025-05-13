//package fr.diginamic.app.controller;
//
//import fr.diginamic.app.dto.EmployeDto;
//import fr.diginamic.app.dto.EmployeMapper;
//import fr.diginamic.app.model.Employe;
//import fr.diginamic.app.service.EmployeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/employes")
//public class EmployeController {
//
//    @Autowired
//    private EmployeService employeService;
//
//    @PostMapping
//    public EmployeDto createEmploye(@RequestBody EmployeDto employeDto) {
//        Employe employe = EmployeMapper.toEntity(employeDto);
//        return EmployeMapper.toDto(employeService.save(employe));
//    }
//
//    @GetMapping
//    public List<EmployeDto> getAllEmployes() {
//        return employeService.findAll()
//                .stream()
//                .map(EmployeMapper::toDto)
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/{id}")
//    public EmployeDto getEmployeById(@PathVariable Long id) {
//        return employeService.findById(id)
//                .map(EmployeMapper::toDto)
//                .orElse(null);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteEmploye(@PathVariable Long id) {
//        employeService.delete(id);
//    }
//}
