package com.demo.demoproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demoproject.domain.Product;
import com.demo.demoproject.dto.productDto;
import com.demo.demoproject.service.ProductApi;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductEndPoint {

    private final ProductApi productApi;

    @Autowired
    public ProductEndPoint(ProductApi productApi) {
        this.productApi = productApi;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
     try{

        
        List<Product> products = productApi.getProducts();
        if(products.size()>0){
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Product Available", HttpStatus.NOT_FOUND);
        }
     }catch(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> AddProducts(@RequestBody productDto productDto){
        try{
            Product product = new Product();
            product.setDescription(productDto.getDescription());
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());

            Product product2 = productApi.AddProduct(product);
            return new ResponseEntity<Product>(product2, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
       

        
    }

    @PutMapping("/{productID}")
   // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> UpdateProduct(@PathVariable String productID , @RequestBody productDto productDto){
        try{
            Product product = new Product();
            product.setProductID(productID);
            product.setDescription(productDto.getDescription());
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            
            Product product2 = productApi.EditProduct(product);
            return new ResponseEntity<Product>(product2, HttpStatus.CREATED);
    
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }

    @DeleteMapping("/{productID}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> DeleteProduct(@PathVariable String productID){
        try{
            productApi.DeleteProduct(productID);
            return new ResponseEntity<>("Product Successfully Deleted", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Product Not Found", HttpStatus.BAD_REQUEST);
        }
           
    }

    @GetMapping("/{productID}")
    public ResponseEntity<?> getProductByID(@PathVariable String productID){
        try{
            Product product = productApi.getSingle(productID);
          
            return new ResponseEntity<Product>(product , HttpStatus.OK);
            
          
            
        }catch(Exception e){
            return new ResponseEntity<>("No Product Available" , HttpStatus.BAD_REQUEST);
        }
       
    }

    
}
