package com.jlear.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // @GetMapping
    // public List<User> getAllUsers() {
    //     return userService.getAllUsers();
    // }

    @RequestMapping(method=RequestMethod.GET)
    public String getAllUsers(@RequestParam(name = "age", required = false) Integer ageParam, Model model) {
        Optional<Integer> age = Optional.ofNullable(ageParam);
        List<User> users = this.userService.getUserByAge(age);
        users.sort(Comparator.comparing(User::getUsername));
        model.addAttribute("users", users);
        return "users";
    }
    




    @GetMapping("/{id}")
    public List<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @ResponseBody
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