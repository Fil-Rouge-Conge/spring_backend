package fr.diginamic.app.repository;

import fr.diginamic.app.model.Departement;
import fr.diginamic.app.model.Employee;
import fr.diginamic.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByRole(Role role);
    List<Employee> findByDepartement(Departement departement);
}