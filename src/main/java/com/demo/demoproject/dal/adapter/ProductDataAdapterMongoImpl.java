package com.demo.demoproject.dal.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.demo.demoproject.dal.model.ProductModel;
import com.demo.demoproject.dal.repository.productMongoRepository;
import com.demo.demoproject.domain.Product;
import com.demo.demoproject.domain.ProductDataAdapter;

public class ProductDataAdapterMongoImpl implements ProductDataAdapter{

    private final productMongoRepository repository;
    private final MongoTemplate mongoTemplate;

   
    public ProductDataAdapterMongoImpl(productMongoRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    

    @Override
    public Product SaveProduct(Product product) {
       ProductModel productModel = new ProductModel();
       productModel.setDescription(product.getDescription());
       productModel.setName(product.getName());
       productModel.setPrice(product.getPrice());
       productModel = repository.save(productModel);
       product.setProductID(productModel.getProductID());
        return product;
    }

    @Override
    public Product UpdateProduct(Product product) {
        ProductModel productModel = mongoTemplate.findAndModify(Query.query(Criteria.where("productID").is(product.getProductID())),
        new Update().set("name", product.getName()).set("description", product.getDescription()).set("price", product.getPrice()), ProductModel.class);

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductModel> productModels = repository.findAll();
        List<Product> products = new ArrayList<>();
        for(ProductModel productModel: productModels){
            Product product = new Product();
            product.setProductID(productModel.getProductID());
            product.setDescription(productModel.getDescription());
            product.setName(productModel.getName());
            product.setPrice(productModel.getPrice());
            products.add(product);
        }
        return products;
    }

    @Override
    public void DeleteProduct(String productID) {
       repository.deleteProductModelByProductID(productID);
        
    }
    
}
