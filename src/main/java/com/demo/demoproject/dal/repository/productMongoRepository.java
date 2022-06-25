package com.demo.demoproject.dal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.demoproject.dal.model.ProductModel;

@Repository
public interface productMongoRepository extends MongoRepository<ProductModel , String>{
    void deleteProductModelByProductID(String productID);
}
