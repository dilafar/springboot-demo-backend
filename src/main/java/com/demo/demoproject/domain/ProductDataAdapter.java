package com.demo.demoproject.domain;

import java.util.List;
import java.util.Optional;

public interface ProductDataAdapter {

    Product SaveProduct(Product product);
    Product UpdateProduct(Product product);
    List<Product> getAllProducts();
    void DeleteProduct(String productID);
    Product getSingleProduct(String productID);
}
