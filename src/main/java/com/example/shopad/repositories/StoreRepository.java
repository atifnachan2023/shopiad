package com.example.shopad.repositories;

import com.example.shopad.model.StoreInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends MongoRepository<StoreInfo,String> {
}
