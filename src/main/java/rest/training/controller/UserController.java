package rest.training.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rest.training.entity.User;
import rest.training.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    ResponseEntity<User> create(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        Optional<User> currentUser = userService.findById(user.getId());
        if (currentUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.create(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        Optional<User> currentUser = userService.findById(id);
        if (currentUser.isPresent()) {
            User modifiedUser = currentUser.get();
            modifiedUser.setName(user.getName());
            modifiedUser.setSurname(user.getSurname());
            modifiedUser.setProjects(user.getProjects());
            userService.update(modifiedUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    ResponseEntity<User> deleteById(@PathVariable Long id) {
        Optional<User> currentUser = userService.findById(id);
        if (currentUser.isPresent()) {
            userService.deleteById(currentUser.get().getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> currentUser = userService.findById(id);
        return currentUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    ResponseEntity<User> findByName(@RequestParam(value = "name") String name) {
        Optional<User> currentUser = userService.findByName(name);
        return currentUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
