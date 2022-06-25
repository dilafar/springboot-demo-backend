package com.demo.demoproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.demoproject.domain.Product;
import com.demo.demoproject.domain.ProductDataAdapter;

@Service
public class ProductApi {
    
    private final ProductDataAdapter productDataAdapter;

    @Autowired
    public ProductApi(ProductDataAdapter productDataAdapter) {
        this.productDataAdapter = productDataAdapter;
    }

    public List<Product> getProducts(){
        return productDataAdapter.getAllProducts();
    }

    public Product AddProduct(Product product){
        return productDataAdapter.SaveProduct(product);
    }

    public Product EditProduct(Product product){
        return productDataAdapter.UpdateProduct(product);
    }

    public void DeleteProduct(String productID){
        productDataAdapter.DeleteProduct(productID);
    }



}
