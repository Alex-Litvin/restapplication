package rest.training.dao;

import org.springframework.stereotype.Repository;
import rest.training.entity.Project;
import rest.training.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MockUserDao implements UserDao {
    private static List<User> users = new ArrayList<>();
    private static final AtomicLong userId = new AtomicLong();
    static {
        users.add(new User(userId.incrementAndGet(), "Alex", "Lytvyn", Arrays.asList(new Project(1L, "Project A"), new Project(2L, "Project B"))));
        users.add(new User(userId.incrementAndGet(), "Yaroslav", "Storozhuk", Arrays.asList(new Project(3L, "Project C"), new Project(4L, "Project D"))));
        users.add(new User(userId.incrementAndGet(), "Vlad", "Rubkin", Arrays.asList(new Project(1L, "Project A"), new Project(3L, "Project C"))));
    }

    public User create(User user) {
        user.setId(userId.incrementAndGet());
        users.add(user);
        return user;
    }

    public User update(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
        return user;
    }

    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public Optional<User> findByName(String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }

    public List<User> findAll() {
        return users;
    }
}
