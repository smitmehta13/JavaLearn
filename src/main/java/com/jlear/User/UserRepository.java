package com.jlear.User;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'username' : ?0 }")
    List<User> findByUsername(String username);

    List<User> findByAge(Integer integer);
        
}
