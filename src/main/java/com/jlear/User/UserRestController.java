package com.jlear.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;






@RestController
@RequestMapping("api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    public UserRestController(UserService userService){
        this.userService = userService;
    }

    // @GetMapping
    // public List<User> getAllUsers() {
    //     return userService.getAllUsers();
    // }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(name = "age", required = false) Integer ageParam) {
        Optional<Integer> age = Optional.ofNullable(ageParam);
        List<User> users = this.userService.getUserByAge(age);
        users.sort(Comparator.comparing(User::getUsername));
        return users;
    }
    




    @GetMapping("/{id}")
    public List<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteUser(@PathVariable String id) {
       return userService.deleteUser(id);
    }

    @GetMapping("/find/{name}")
    public List<User> findUserByName(@PathVariable String name) {
        return userService.findUsersByName(name);
    }
    
}