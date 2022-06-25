package com.demo.demoproject.dal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.demoproject.dal.model.ProductModel;

public interface productMongoRepository extends MongoRepository<ProductModel , String>{
    void deleteProductModelByProductID(String productID);
}
