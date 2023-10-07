package com.example.shopad.repositories;

import com.example.shopad.model.ProductsInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository  extends MongoRepository<ProductsInfo,String> {
}
