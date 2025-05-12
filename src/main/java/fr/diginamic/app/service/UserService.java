package fr.diginamic.app.service;

import fr.diginamic.app.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    void delete(Long id);
}
