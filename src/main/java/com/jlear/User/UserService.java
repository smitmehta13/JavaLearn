package com.jlear.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepo;


    public UserService(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    public List<User> getAllUsers() {
        // Fetch all users from the database
        return userRepo.findAll();
    }

    public List<User> getUserById(String id) {
        try {
            System.out.println("getting user for id: " + id);
            User requestedUser = userRepo.findById("ID" + id)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));
            return List.of(
                requestedUser
            );
        } catch (NoSuchElementException e) {

            System.err.println("No user found for id: " + id);

            return List.of();
        }
    }

    public User createUser(User user) {
        // Save the user to the database
        user.age = User.calculateAge(user.getDob());
        User savedUser = userRepo.save(user);
        return savedUser;
    }

    public User updateUser(String id, User user) {
        // Find the existing user by ID
        User existingUser = userRepo.findById("ID" + id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        // Update the fields
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        // Save the updated user
        return userRepo.save(existingUser);
    }

    public Map<String, String> deleteUser(String id) {
        Map<String, String> response = new HashMap<>();
        if (id == null || id.isEmpty()) {
            response.put("message", "ID is Null");
        } else {
            Optional<User> existingUser = userRepo.findById("ID" + id);
            if (existingUser.isPresent()) {
                userRepo.deleteById("ID" + id);
                response.put("message", "User Deleted Successfully for ID: " + id);
            } else {
                response.put("message", "User Does Not Exist for ID: " + id);
            }
        }
        return response;
    }

    public List<User> findUsersByName(String name) {
        if (name == null || name.isEmpty()) {
            return List.of(
                new User() 
            );
        } 
        List<User> userFromDb = userRepo.findByUsername(name);
        return userFromDb;
    }
 

    public List<User> getUserByAge(Optional<Integer> age) {
        if (age.isPresent()) {
            System.out.println(age);
            return userRepo.findByAge(age.get());
        } else {
            return userRepo.findAll();
        }

    }
}
