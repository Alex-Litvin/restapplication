package rest.training.service;

import rest.training.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);
    User update(User user);
    void deleteById(Long id);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    List<User> findAll();
}
