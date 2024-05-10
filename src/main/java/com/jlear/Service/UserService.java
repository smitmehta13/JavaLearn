package com.jlear.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jlear.Model.User;
import com.jlear.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepo;

    private ObjectMapper objectMapper;

    public UserService(ObjectMapper objectMapper, UserRepository userRepository) {
        this.userRepo = userRepository;
        this.objectMapper = objectMapper;
    }

    public List<User> getAllUsers() {
        // Fetch all users from the database
        return userRepo.findAll();
    }

    public User getUserById(String id) {
        try {
            System.out.println("getting user for id: " + id);
            User requestedUser = userRepo.findById("ID" + id)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));
            return requestedUser;
        } catch (NoSuchElementException e) {
            // Construct JSON response for no user found
            String jsonResponse = "{\"error\": \"No user found\"}";
            // Log the error if needed
            System.err.println("No user found for id: " + id);
            // Return JSON response
            try {
                return objectMapper.readValue(jsonResponse, User.class);
            } catch (Exception ex) {
                // Handle JSON parsing exception
                ex.printStackTrace();
                return null;
            }
        }
    }

    public User createUser(User user) {
        // Save the user to the database
        return userRepo.save(user);
    }

    public User updateUser(String id, User user) {
        // Find the existing user by ID
        User existingUser = userRepo.findById("ID"+id).orElseThrow(() -> new NoSuchElementException("User not found"));
        // Update the fields
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        // Save the updated user
        return userRepo.save(existingUser);
    }

    public void deleteUser(String id) {
        // Find the user by ID and delete it
        userRepo.deleteById("ID"+id);
    } 

    public Boolean findUsersByName(String name){
        return userRepo.existsById("ID5");
    }
}
