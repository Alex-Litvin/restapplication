package rest.training.dao;

import rest.training.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User create(User user);
    User update(User user);
    void deleteById(Long id);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    List<User> findAll();
}
