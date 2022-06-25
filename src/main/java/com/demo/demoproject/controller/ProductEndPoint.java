package com.demo.demoproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demoproject.domain.Product;
import com.demo.demoproject.dto.productDto;
import com.demo.demoproject.service.ProductApi;

@RestController
@RequestMapping("/product")
public class ProductEndPoint {

    private final ProductApi productApi;

    @Autowired
    public ProductEndPoint(ProductApi productApi) {
        this.productApi = productApi;
    }

    @GetMapping
    public List<Product> getAll(){
        return productApi.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product AddProducts(@RequestBody productDto productDto){
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        return productApi.AddProduct(product);
    }

    @PutMapping("/{productID}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product UpdateProduct(@PathVariable String productID , @RequestBody productDto productDto){
        Product product = new Product();
        product.setProductID(productID);
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        return productApi.EditProduct(product);

    }

    @DeleteMapping("/{productID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteProduct(@PathVariable String productID){
            productApi.DeleteProduct(productID);
    }

    
}
