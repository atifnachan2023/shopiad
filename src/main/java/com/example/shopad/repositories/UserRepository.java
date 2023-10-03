package com.example.shopad.repositories;

import com.example.shopad.model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserAccount,Integer> {
}
