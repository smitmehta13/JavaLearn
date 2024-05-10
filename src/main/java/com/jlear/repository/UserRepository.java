package com.jlear.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jlear.Model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
        
}
