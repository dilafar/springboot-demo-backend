package com.demo.demoproject.domain;

import java.util.List;

public interface ProductDataAdapter {

    Product SaveProduct(Product product);
    Product UpdateProduct(Product product);
    List<Product> getAllProducts();
    void DeleteProduct(String productID);

}
