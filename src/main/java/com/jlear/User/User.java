package com.jlear.User;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("students")
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private LocalDate dob = LocalDate.of(999, Month.SEPTEMBER, 9);
    @Transient int age;

    // Constructors
    public User() {
    }

    public User(String id, String username, String password, String email, LocalDate dob) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob == null ? LocalDate.now() : LocalDate.parse(dob);
    }

    public static int calculateAge(LocalDate dob){
        if (dob == null) {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }
    
        LocalDate hundredYearsAgo = LocalDate.now().minusYears(100);
        if (dob.isAfter(hundredYearsAgo)) {
            return Period.between(dob, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

     // Other methods
     @Override
     public String toString() {
         return "User{" +
                 "username='" + username + '\'' +
                 ", password='" + password + '\'' +
                 ", email='" + email + '\'' +
                 ", dob='" + dob + '\'' +
                 ", age='" + age + '\'' +
                 '}';
     }
 
}
